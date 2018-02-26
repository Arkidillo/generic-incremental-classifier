package gui;

import gui.buttons.*;
import logic.LabelPlaceHandler;
import util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUIHandler implements ActionListener{
    private OurFrame frame;
    private ImagePane imagePane;

    private boolean[] buttonsPressed = new boolean[4];

    public GUIHandler(Dimension dim) {
        createWindow(dim);
    }

    public void createWindow(Dimension dim) {
        // create pane and frame
        imagePane   = new ImagePane(dim);
        frame       = new OurFrame(dim, this, imagePane);
    }

    // add the next image in the folder to the screen (pane)
    public void loadNextImage() {
        // create + set the image
        BufferedImage image = ImageLoader.loadNextImage();
        imagePane.setImage(image, ImageLoader.getImageIndex());

        // repaint is necessary to show
        frame.repaint();
    }

    public void callRepaint() {
        frame.repaint();
    }

    public void loadPrevImage() {
        // create + set the image
        BufferedImage image = ImageLoader.loadPrevImage();
        imagePane.setImage(image, ImageLoader.getImageIndex());

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
        }
    }
}
