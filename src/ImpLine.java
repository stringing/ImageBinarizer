
import java.awt.*;

/**
 * @Description 画线类
 * @Author Stringing
 * @Date 2018/11/24 11:44
 */
public class ImpLine implements NetJavaShape{
    Graphics g;
    int x1, y1,x2, y2;
    Color c;
    float brushSize;

    public ImpLine(Graphics g,int x1,int y1,int x2,int y2,Color c, float brushSize){
        this.g=g;
        this.c=c;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.brushSize = brushSize;
    }
    public void draw() {
        g.setColor(c);
        ((Graphics2D) g).setStroke(new BasicStroke(brushSize));
        g.drawLine(x1, y1, x2, y2);
    }

}

