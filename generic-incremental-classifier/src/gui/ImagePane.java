package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// A panel that draws the image that is set
public class ImagePane extends JLayeredPane {

    private ArrayList<Label> labels = new ArrayList<>();
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
        // draw our picture
        g.drawImage(image, 0, 0, this);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(Label.THICKNESS));
        g2d.setColor(Label.COLOR);
        // draw the labels
        for (Label label: labels) {
            g2d.drawRect(label.getX(), label.getY(), label.getWidth(), label.getHeight());
        }
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

}
