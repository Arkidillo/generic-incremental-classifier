package logic;

import gui.GUIHandler;
import util.Utils;

public class TrainingHandler {
    private GUIHandler guiHandler;

    public TrainingHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void train() {
        while(true /* How many time do we want this to train? */) {
            boolean next = labelThisImage();
            if (next) {
                guiHandler.loadNextImage();
            } else {
                guiHandler.loadPrevImage();
            }
        }
    }

    // allow user to label image,
    // return true when next is pressed
    // return false when back is pressed
    public boolean labelThisImage() {
        while (true) {
            Utils.sleep(1);
            // wait for input
            // draw box

            // return when next or back button is pressed
            if (guiHandler.isNextButtonPressed()) {
                return true;
            } else if (guiHandler.isBackButtonPressed()) {
                return false;
            }
        }
    }
}
