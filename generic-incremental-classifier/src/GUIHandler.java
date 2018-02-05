import java.awt.*;

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
        OurImage image = ImageLoader.loadFirstImage();
        imagePane.addImage(image);

        image.setLocation(540, 960);
        image.repaint();
        imagePane.repaint();
        frame.repaint();
    }

}
