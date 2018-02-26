package gui;

import logic.LabelPlaceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

// A panel that draws the image that is set
public class ImagePane extends JLayeredPane {

    private HashMap<Integer, ArrayList<Label>> labels = new HashMap<>();
    private BufferedImage image;
    private int currentImageIndex = 0;

    public ImagePane(Dimension dim) {
        setPreferredSize(dim);
    }

    public void setImage(BufferedImage image, int imageIndex) {
        this.image = image;
        this.currentImageIndex = imageIndex;
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
        if (!labels.containsKey(currentImageIndex)) return;
        for (Label label: labels.get(currentImageIndex)) {
            if (label == LabelPlaceHandler.selectedLabel) {
                g2d.setColor(Color.CYAN);
            }
            g2d.drawRect(label.getX(), label.getY(), label.getWidth(), label.getHeight());
            if (label == LabelPlaceHandler.selectedLabel) {
                g2d.setColor(Color.BLACK);
            }
        }
    }

    public void addLabel(Label label) {
        if (!labels.containsKey(currentImageIndex)) {
            labels.put(currentImageIndex, new ArrayList<>());
        }
        labels.get(currentImageIndex).add(label);
    }

    public void removeLabel(Label label) {
        labels.get(currentImageIndex).remove(label);
    }

    // returns the first label that this click is inside of
    // start with the most recently added labels
    public Label getLabelOnClick(Point p) {
        if (!labels.containsKey(currentImageIndex)) return null;
        for (int i = labels.get(currentImageIndex).size() - 1; i >= 0; i--) {
            Label label = labels.get(currentImageIndex).get(i);
            if (label.isClickInside(p)) return label;
        }

        return null;
    }
}
