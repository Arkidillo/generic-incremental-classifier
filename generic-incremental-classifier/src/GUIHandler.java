import javax.swing.*;
import java.awt.*;

public class GUIHandler {
    private OurFrame frame;
    private ImagePane imagePane;

    public void createWindow(Dimension dim) {
        imagePane   = new ImagePane(dim);
        frame       = new OurFrame(dim, imagePane);

        frame.add(imagePane);
        frame.pack();

        OurImage image = ImageLoader.loadFirstImage();
        imagePane.loadImage(image);
    }


}
