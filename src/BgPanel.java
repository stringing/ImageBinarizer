import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Description 背景图Panel
 * @Author Stringing
 * @Date 2018/11/24 13:14
 */
public class BgPanel extends JPanel {

    BufferedImage image;
    String imageName;

    public BgPanel(BufferedImage image, String imageName) {
        //image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\lenovo\\Desktop\\img.jpg");
        this.image = image;
        this.imageName = imageName;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imWidth=image.getWidth(this);
        int imHeight=image.getHeight(this); //定义图片的宽度、高度
        int FWidth=getWidth();
        int FHeight=getHeight();//定义窗口的宽度、高度
        int x=(FWidth-imWidth)/2;
        int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间
        g.drawImage(image,x,y,null);//绘制图片
    }


}
