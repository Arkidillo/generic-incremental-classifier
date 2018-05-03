package logic;

import gui.Label;

import java.util.ArrayList;
import java.util.HashMap;

public class UserCorrectionHandler {
    private GUIHandler guiHandler;

    public UserCorrectionHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void correct() {
        // Read all labels generated by the python
        HashMap<String, ArrayList<Label>> labelsForFile = LabelsReader.readLabels();

        guiHandler.loadNextImage();
        while(true /* How many time do we want this to train? */) {
            boolean next = labelThisImage();
            if (next) {
                guiHandler.loadNextImage();
            } else {
                guiHandler.loadPrevImage();
            }
        }
    }

}
