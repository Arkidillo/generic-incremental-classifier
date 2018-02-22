package gui;

import gui.buttons.*;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUIHandler implements ActionListener{
    private OurFrame frame;
    private ImagePane imagePane;

    private boolean[] buttonsPressed = new boolean[4];

    public void createWindow(Dimension dim) {
        // create pane and frame
        imagePane   = new ImagePane(dim);
        frame       = new OurFrame(dim, this, imagePane);
    }

    // add the next image in the folder to the screen (pane)
    public void loadNextImage() {
        // create + set the image
        BufferedImage image = ImageLoader.loadNextImage();
        imagePane.setImage(image);

        // repaint is necessary to show
        frame.repaint();
    }

    public void loadPrevImage() {
        // create + set the image
        BufferedImage image = ImageLoader.loadPrevImage();
        imagePane.setImage(image);

        // repaint is necessary to show
        frame.repaint();
    }

    public void addLabel(Label label) {
        imagePane.addLabel(label);
        frame.repaint();
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
        }
    }
}
