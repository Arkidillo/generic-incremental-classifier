import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// A panel that draws the image that is set
public class ImagePane extends JLayeredPane {

    private BufferedImage image;

    public ImagePane(Dimension dim) {
        setPreferredSize(dim);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
