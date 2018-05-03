package main;

import logic.GUIHandler;
import logic.TrainingHandler;
import logic.UserCorrectionHandler;

import java.awt.*;

public class GenericIncrementalClassifier {
    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;


    public static void main (String[] args) {
        // Start window
        GUIHandler gui = new GUIHandler(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // If there were no arguments, this is the first time we are running, so do the first training
        /*if (args.length == 0) {
            // User training
            TrainingHandler trainer = new TrainingHandler(gui);
            trainer.train();
            // Pass back to the shell to let the python train
            System.exit(0);
            // ML training (Done in python)
        }*/


        // User correction
        UserCorrectionHandler userCorrection = new UserCorrectionHandler(gui);
        userCorrection.correct();
        // Test image
        // User correction
    }
}
