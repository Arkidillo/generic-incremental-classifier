import javax.swing.*;

public class OurImage extends JLabel {
    public OurImage (ImageIcon image) {
        setIcon(image);
        setSize(image.getIconWidth(), image.getIconHeight());
    }
}
