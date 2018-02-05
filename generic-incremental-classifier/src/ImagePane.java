import javax.swing.*;
import java.awt.*;

public class ImagePane extends JLayeredPane {
    public ImagePane(Dimension dim) {
        setPreferredSize(dim);
    }

    public void addImage(OurImage image) {
        add(image);
    }
}
