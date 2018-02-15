package logic;

import gui.GUIHandler;

public class TrainingHandler {
    private GUIHandler guiHandler;

    public TrainingHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void train() {
        while(true /* How many time do we want this to train? */) {
            labelThisImage();
            guiHandler.loadNextImage();
        }
    }

    public void labelThisImage() {
        while (!guiHandler.isNextButtonPressed()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // wait
            // draw box
        }
    }
}
