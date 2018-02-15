package main;

import gui.GUIHandler;
import logic.TrainingHandler;

import java.awt.*;

public class GenericIncrementalClassifier {
    public static void main (String [] args) {
        // Start window
        GUIHandler gui = new GUIHandler();
        gui.createWindow(new Dimension(1280, 720));

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
