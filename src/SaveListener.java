import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

/**
 * @Description 保存监听
 * @Author Stringing
 * @Date 2018/11/24 13:27
 */
public class SaveListener implements ActionListener, MouseListener {
    private BgPanel jp;
    private DrawListener dl;
    private float brushSize;
    private File[] images;
    private int currentIndex;
    private String path;

    public SaveListener(BgPanel jp, DrawListener dl, float brushSize, File[] images, int currentIndex, String path){
        this.jp = jp;
        this.dl = dl;
        this.brushSize = brushSize;
        this.images = images;
        this.currentIndex = currentIndex;
        this.path = path;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //printImageRgb("C:\\Users\\lenovo\\Desktop\\ori.txt");
            Graphics2D g = (Graphics2D) jp.image.getGraphics();
            for(NetJavaShape njs : dl.getShapesArray()){
                g.setStroke(new BasicStroke(((ImpLine) njs).brushSize));
                g.drawLine(((ImpLine) njs).x1, ((ImpLine) njs).y1, ((ImpLine) njs).x2, ((ImpLine) njs).y2);
            }
            //printImageRgb("C:\\Users\\lenovo\\Desktop\\des.txt");
            imageBinarize();
            ImageIO.write(jp.image, "JPG", new File(path + "\\" + jp.imageName));
            if(currentIndex < images.length - 1){
                jp.image = ImageIO.read(images[++currentIndex]);
                jp.imageName = images[currentIndex].getName();
                jp.paintComponent(jp.getGraphics());
            }else{
                JOptionPane.showMessageDialog(jp, "已标记完所有图！", "消息",JOptionPane.INFORMATION_MESSAGE);
            }
            dl.shapesArray = new ArrayList<>();
            dl.memoryStack.clear();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void imageBinarize() {
        for(int i = 0; i < jp.image.getHeight(); i++){
            for(int j = 0; j < jp.image.getWidth(); j++){
                if(jp.image.getRGB(j, i) != -1){
                    jp.image.setRGB(j, i, -16777216);
                }
            }
        }
    }

    private void printImageRgb(String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        for(int i = 0; i < jp.image.getHeight(); i++){
            for(int j = 0; j < jp.image.getWidth(); j++){
                bw.write(" "  + jp.image.getRGB(j, i));
            }
            bw.write("\n");
            bw.flush();
        }
        bw.close();
    }

}
