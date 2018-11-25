import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @Description 主程序
 * @Author Stringing
 * @Date 2018/11/24 11:43
 */

public class Draw extends JFrame {
    private DrawListener dl;
    private Graphics2D g;
    private static float brushSize = 5.0f;
    //保存图形对象的集合
    private List<NetJavaShape> shapesArray = new ArrayList();
    // 界面初始化方法
    public void showUI() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        initWindow();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择文件夹
        int flag = chooser.showSaveDialog(this);
        String path = null;
        if (flag == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();
        }else{
            System.exit(0);
        }
        String binarizedPath = path.substring(0, path.lastIndexOf("\\"));
        String oriName = path.substring(path.lastIndexOf("\\") + 1);
        File binDir = new File(binarizedPath + "\\" + oriName + "_bin");
        if(!binDir.exists()){
            binDir.mkdir();
        }
        File dir = new File(path);
        File[] images = dir.listFiles(pathname -> {
            if(pathname.getName().endsWith(".jpg"))
                return true;
            return false;
        });
        int currentIndex = 0;

        JPanel jp1=new JPanel(new GridLayout(15, 2,10,10));//用于保存图形按钮，使用网格布局
        jp1.setPreferredSize(new Dimension(200, 800));

        //实例化DrawListener对象
        dl = new DrawListener();

        BufferedImage bi = ImageIO.read(images[currentIndex]);

        BgPanel jp2 = new BgPanel(bi, images[currentIndex].getName());//画布面板
        jp2.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
        jp2.setBackground(Color.WHITE);

        JLabel label = new JLabel("画笔粗细");
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(true);
        comboBox.addItem("1.0");
        comboBox.addItem("2.0");
        comboBox.addItem("3.0");
        comboBox.addItem("4.0");
        comboBox.addItem("5.0");
        comboBox.addItem("6.0");
        comboBox.setSelectedItem("5.0");

        JButton resetAllButton = new JButton("重新标记当前图像");
        resetAllButton.addActionListener(new ResetAllListener(jp2, dl));

        JButton resetButton = new JButton("撤销");
        resetButton.addActionListener(new ResetListener(dl, jp2));

        JButton saveButton = new JButton("保存");
        SaveListener sl = new SaveListener(jp2, dl, brushSize, images, currentIndex, binDir.getAbsolutePath());
        saveButton.addActionListener(sl);
        jp1.add(label);
        jp1.add(comboBox);
        jp1.add(resetButton);
        jp1.add(resetAllButton);
        jp1.add(saveButton);



        // 定义Color数组，用来存储按钮上要显示的颜色信息
        Color[] colorArray = { Color.BLUE, Color.GREEN, Color.RED, Color.BLACK,Color.ORANGE,Color.PINK,Color.CYAN,Color.MAGENTA,Color.DARK_GRAY,Color.GRAY,Color.LIGHT_GRAY,Color.YELLOW};
        //用于保存颜色按钮的面板
        JPanel jp3=new JPanel(new GridLayout(1,colorArray.length,3,3));
        // 循环遍历colorArray数组，根据数组中的元素来实例化按钮对象
        for (int i = 0; i < colorArray.length; i++) {
            JButton button = new JButton();
            button.setBackground(colorArray[i]);
            button.setPreferredSize(new Dimension(30, 30));
            button.addActionListener(dl);//为按钮添加监听
            jp3.add(button);
        }
        //将面板添加到主窗体
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        //添加按钮，作为当前颜色
        JButton nowColor=new JButton();
        nowColor.setPreferredSize(new Dimension(40,40));
        nowColor.setBackground(Color.BLACK);//默认黑色
        add(nowColor);
        //设置窗体的组件可见，如果为FALSE就看不到任何组件
        setVisible(true);
        //获取画笔对象
        g= (Graphics2D) jp2.getGraphics();
        //g = bi.createGraphics();

        dl.setG(g);
        dl.setNowColor(nowColor);
        dl.setCombobox(comboBox);
        //获取保存的集合
        shapesArray = dl.getShapesArray();
        //为面板添加鼠标监听，用于绘制图形
        jp2.addMouseListener(dl);
        jp2.addMouseMotionListener(dl);
    }

    private void initWindow(){
        setTitle("图像标记");//窗体名称
        setSize(1500, 900);//窗体大小
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);//窗体居中
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);//流式布局左对齐
        setLayout(layout);//窗体使用流式布局管理器
        this.setResizable(false);//窗体大小不变
    }
    @Override
    //重写paint方法
    public void paint(Graphics g) {
        //调用父类的paint方法，绘制界面上的组件
        super.paint(g);
        //foreach遍历集合
        if(!dl.memoryStack.isEmpty()) {
            for (NetJavaShape l : dl.memoryStack.peek()) {
                l.draw();
            }
        }else{
            for (NetJavaShape l : dl.shapesArray) {
                l.draw();
            }
        }
    }

}