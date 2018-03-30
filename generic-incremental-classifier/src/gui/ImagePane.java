package gui;

import com.google.gson.Gson;
import logic.LabelPlaceHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

// A panel that draws the image that is set
public class ImagePane extends JLayeredPane {

    private HashMap<String, ArrayList<Label>> labels = new HashMap<>();
    private BufferedImage image;
    private String currentFileName;

    public ImagePane(Dimension dim) {
        setPreferredSize(dim);
    }

    public HashMap<String, ArrayList<Label>> getLabels() {
        return labels;
    }

    public void setImage(BufferedImage image, String fileName) {
        this.image = image;
        this.currentFileName = fileName;
        // Create a new arrayList for the labels in this picture as soon as the image is set
        if (!labels.containsKey(currentFileName))
            labels.put(currentFileName, new ArrayList<>());
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
        if (!labels.containsKey(currentFileName)) return;
        for (Label label: labels.get(currentFileName)) {
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
        labels.get(currentFileName).add(label);
    }

    public void removeLabel(Label label) {
        labels.get(currentFileName).remove(label);
    }

    // returns the first label that this click is inside of
    // start with the most recently added labels
    public Label getLabelOnClick(Point p) {
        if (!labels.containsKey(currentFileName)) return null;
        for (int i = labels.get(currentFileName).size() - 1; i >= 0; i--) {
            Label label = labels.get(currentFileName).get(i);
            if (label.isClickInside(p)) return label;
        }

        return null;
    }
}
