import java.awt.*;
import java.awt.image.BufferedImage;

public class GUIHandler {
    private OurFrame frame;
    private ImagePane imagePane;

    public void createWindow(Dimension dim) {
        // create pane and frame
        imagePane   = new ImagePane(dim);
        frame       = new OurFrame(dim, imagePane);
}

    // add the first image in the folder to the screen (pane)
    public void loadFirstImage() {
        BufferedImage image = ImageLoader.loadFirstImage();
        imagePane.setImage(image);

        imagePane.repaint();
        frame.repaint();
    }

}
