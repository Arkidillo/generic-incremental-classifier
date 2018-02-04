import javax.swing.*;
import java.awt.*;

public class ImagePane extends JPanel{
    public ImagePane(Dimension dim) {
        setPreferredSize(dim);
    }

    public void loadImage(OurImage image) {
        add(image);
    }
}
