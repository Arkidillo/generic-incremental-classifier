package gui;

import util.ImageLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUIHandler implements ActionListener{
    private OurFrame frame;
    private ImagePane imagePane;

    private boolean nextButtonPressed = false;

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

    public boolean isNextButtonPressed() {
        if (nextButtonPressed) {
            nextButtonPressed = false;
            return true;
        } else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextButtonPressed = true;
    }
}
