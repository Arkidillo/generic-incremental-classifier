package gui;

import gui.buttons.BackButton;
import gui.buttons.DeleteButton;
import gui.buttons.NextButton;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUIHandler implements ActionListener{
    private OurFrame frame;
    private ImagePane imagePane;

    private boolean nextButtonPressed = false;
    private boolean backButtonPressed = false;
    private boolean deleteButtonPressed = false;

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

    // Check if the button pressed, and toggle off
    public boolean isNextButtonPressed() {
        if (nextButtonPressed) {
            nextButtonPressed = false;
            return true;
        } else return false;
    }

    public boolean isBackButtonPressed() {
        if (backButtonPressed) {
            backButtonPressed = false;
            return true;
        } else return false;
    }

    public boolean isDeletePressed() {
        if (deleteButtonPressed) {
            deleteButtonPressed = false;
            return true;
        } else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NextButton.ACTION_COMMAND)) {
            nextButtonPressed = true;
        } else if (e.getActionCommand().equals(BackButton.ACTION_COMMAND)) {
            backButtonPressed = true;
        } else if (e.getActionCommand().equals(DeleteButton.ACTION_COMMAND)) {
            deleteButtonPressed = true;
        }
    }
}
