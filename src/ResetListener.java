import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 撤销监听
 * @Author Stringing
 * @Date 2018/11/24 21:44
 */
public class ResetListener implements ActionListener {
    private DrawListener dl;
    private BgPanel jp;

    public ResetListener(DrawListener dl, BgPanel jp){
        this.dl = dl;
        this.jp = jp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        jp.paintComponent(jp.getGraphics());
        if(dl.memoryStack.size() >= 2) {
            Graphics2D g = (Graphics2D) jp.getGraphics();
            dl.memoryStack.pop();
            List<NetJavaShape> currentStatus = dl.memoryStack.peek();
            for (NetJavaShape njs : currentStatus) {
                g.setStroke(new BasicStroke(((ImpLine) njs).brushSize));
                g.drawLine(((ImpLine) njs).x1, ((ImpLine) njs).y1, ((ImpLine) njs).x2, ((ImpLine) njs).y2);
            }
            dl.shapesArray = currentStatus;
        }else{
            if(dl.memoryStack.size() == 1)dl.memoryStack.pop();
            dl.shapesArray = new ArrayList<>();
        }
    }

}
