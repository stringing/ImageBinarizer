import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @Description 重画监听
 * @Author Stringing
 * @Date 2018/11/24 20:42
 */
public class ResetAllListener implements ActionListener {

    private BgPanel jp;
    private DrawListener dl;

    public ResetAllListener(BgPanel jp, DrawListener dl){
        this.jp = jp;
        this.dl = dl;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        jp.paintComponent(jp.getGraphics());
        dl.shapesArray = new ArrayList<>();
    }
}
