package logic;

import gui.buttons.ButtonIDs;
import util.ImageHandler;
import util.Utils;

import java.io.File;

public class TrainingHandler {
    private GUIHandler guiHandler;

    public TrainingHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void train() {
        File imageFolder = new File(ImageHandler.IMAGE_FOLDER);
        guiHandler.loadNextImage(imageFolder);
        while(true /* How many time do we want this to train? */) {
            boolean next = labelThisImage();
            if (next) {
                guiHandler.loadNextImage(imageFolder);
            } else {
                guiHandler.loadPrevImage(imageFolder);
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
            // (this is taken care of by a background listener)

            // return when next or back button is pressed
            if (guiHandler.isButtonPressed(ButtonIDs.NEXT_BUTTON)) {
                return true;
            } else if (guiHandler.isButtonPressed(ButtonIDs.BACK_BUTTON)) {
                return false;
            }
        }
    }
}
