package main;

import gui.GUIHandler;
import logic.TrainingHandler;

import java.awt.*;

public class GenericIncrementalClassifier {
    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;


    public static void main (String [] args) {
        // Start window
        GUIHandler gui = new GUIHandler();
        gui.createWindow(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        TrainingHandler trainer = new TrainingHandler(gui);
        gui.loadNextImage();
        trainer.train();
        // User training
        // Loop:
            // ML training (Done in python)
            // Test image
            // User correction
    }
}
