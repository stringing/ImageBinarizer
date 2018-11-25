import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @Description 画图监听
 * @Author Stringing
 * @Date 2018/11/24 11:44
 */
public class DrawListener implements ActionListener, MouseListener,
        MouseMotionListener {
    private Color color=Color.BLACK;//颜色属性,初始值为黑色
    private Graphics g;//画笔属性
    private String str;//保存按钮上的字符串，区分不同的按钮
    private JComboBox<String> comboBox;
    private int x1 = -1,y1 = -1;//(x1,y1),(x2,y2)分别为鼠标的按下和释放时的坐标
    private JButton nowColor;//当前颜色按钮
    //保存图形对象的集合
    protected List<NetJavaShape> shapesArray = new ArrayList();
    protected Stack<List<NetJavaShape>> memoryStack = new Stack<>();
    //图形
    private NetJavaShape shape;
    //在draw类中获取集合
    public List<NetJavaShape> getShapesArray() {
        return shapesArray;
    }
    //获取Draw类的画笔对象
    public void setG(Graphics g) {
        this.g = g;
    }
    //获取当前颜色按钮
    public void setNowColor(JButton nowColor) {
        this.nowColor = nowColor;
    }


    @Override
    //鼠标拖动的方法
    public void mouseDragged(MouseEvent e) {
        //画曲线的方法
            int x, y;
            x = e.getX();
            y = e.getY();
            if(x1 < 0 && y1 < 0){
                shape=new ImpLine(g,x,y,x,y,color, Float.parseFloat((String)comboBox.getSelectedItem()));
            }else{
                shape=new ImpLine(g,x1,y1,x,y,color, Float.parseFloat((String)comboBox.getSelectedItem()));
            }
            x1 = x;
            y1 = y;
            //调用画图方法
            shape.draw();
            //将图形存入集合中
            shapesArray.add(shape);
    }

    @Override
    //鼠标移动方法
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    //鼠标单击方法
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    //鼠标按下方法
    public void mousePressed(MouseEvent e) {
    }

    @Override
    //鼠标释放方法
    public void mouseReleased(MouseEvent e) {
        x1 = -1;
        y1 = -1;
        List<NetJavaShape> tmp = new ArrayList<>();
        tmp.addAll(shapesArray);
        memoryStack.push(tmp);
    }

    @Override
    //鼠标进入方法
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    //鼠标退出方法
    public void mouseExited(MouseEvent e) {

    }

    @Override
    //处理按钮上的鼠标点击动作
    public void actionPerformed(ActionEvent e) {

        if ("".equals(e.getActionCommand())) {
            JButton jb = (JButton) e.getSource();
            color = jb.getBackground();
            nowColor.setBackground(color);//处理当前颜色
        } else {
            str = e.getActionCommand();
        }
    }

    public void setCombobox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
}
