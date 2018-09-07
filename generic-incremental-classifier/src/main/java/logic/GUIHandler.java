package logic;

import gui.ImagePane;
import gui.Label;
import gui.OurFrame;
import gui.buttons.*;
import util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUIHandler implements ActionListener{
    private OurFrame frame;
    private ImagePane imagePane;

    private boolean[] buttonsPressed = new boolean[4];

    public GUIHandler(Dimension dim) {
        createWindow(dim);
    }

    public ImagePane getImagePane() {
        return imagePane;
    }

    public void createWindow(Dimension dim) {
        // create pane and frame
        imagePane   = new ImagePane(dim);
        frame       = new OurFrame(dim, this, imagePane);
    }

    // add the next image in the folder to the screen (pane)
    public void loadNextImage(File folder) {
        // create + set the image
        BufferedImage image = ImageHandler.loadNextImage(folder);
        imagePane.setImage(image, ImageHandler.getCurrentFileName());

        // repaint is necessary to show
        frame.repaint();
    }

    public void callRepaint() {
        frame.repaint();
    }

    public void loadPrevImage(File folder) {
        // create + set the image
        BufferedImage image = ImageHandler.loadPrevImage(folder);
        imagePane.setImage(image, ImageHandler.getCurrentFileName());

        // repaint is necessary to show
        frame.repaint();
    }

    public void addTextBox(JLabel textBox) {
        imagePane.add(textBox, 4);
        imagePane.repaint();
        imagePane.validate();
        frame.repaint();
    }

    public void addLabel(Label label) {
        imagePane.addLabel(label);
        frame.repaint();
    }

    public void removeLabel(Label label) {
        imagePane.removeLabel(label);
        frame.repaint();
    }

    public Label getLabelOnClick(Point p) {
        return imagePane.getLabelOnClick(p);
    }

    // Check if the button pressed, and toggle off
    public boolean isButtonPressed(short buttonID) {
        if (buttonsPressed[buttonID]) {
            buttonsPressed[buttonID] = false;
            return true;
        } else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NextButton.ACTION_COMMAND)) {
            buttonsPressed[ButtonIDs.NEXT_BUTTON] = true;
        } else if (e.getActionCommand().equals(BackButton.ACTION_COMMAND)) {
            buttonsPressed[ButtonIDs.BACK_BUTTON] = true;
        } else if (e.getActionCommand().equals(DeleteButton.ACTION_COMMAND)) {
            buttonsPressed[ButtonIDs.DELETE_BUTTON] = true;
            removeLabel(LabelPlaceHandler.selectedLabel);
        } else if (e.getActionCommand().equals(DoneButton.ACTION_COMMAND)) {
            // Call gson handler
            //new GsonHandler(imagePane.getLabels());

            // Crop all images to their labels, and save.
            SaveImagesHandler.saveAllImages(imagePane.getLabels(), imagePane.getImageSizes());
        }
    }
}
