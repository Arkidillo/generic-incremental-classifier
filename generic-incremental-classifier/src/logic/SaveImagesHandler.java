package logic;

import gui.Label;
import util.ImageHandler;
import util.Utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static util.ImageHandler.*;

public class SaveImagesHandler {

    public static void saveAllImages(HashMap<String, ArrayList<Label>> allLabels) {
        // Make sure the folder is cleared
        createFolders();
        //clearFolders();


        // For each image/ entry in hashmap, save a JSON
        Iterator it = allLabels.entrySet().iterator();
        while (it.hasNext()) {
            // The pair is (imageName, Arraylist of labels)
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Label> labels = (ArrayList<Label>) (pair.getValue());
            String fileName =         (String)pair.getKey();

            // The image is a negative example if it has no labels
            if (labels.size() == 0) {
                ImageHandler.saveImage(ImageHandler.loadImage(fileName), fileName, ImageHandler.NEGATIVE_IMAGE);
            }

            // Crop all the labels out of the image
            for (int j = 0; j < labels.size(); j++) {
                // Load the original buffered image, for cropping
                BufferedImage originalImage = ImageHandler.loadImage(fileName);
                // Save the cropped image back
                // Add an extra '_i' for whatever label number it is
                // Need to remove the file ext first, add the _i, then add back the ext.
                String ext = Utils.getExtension(fileName);
                String truncatedFilename = fileName.substring(0, fileName.length() - (ext.length() + 1));
                byte correctedClass = labels.get(j).isModelWrong() ? MODEL_WRONG : MODEL_NOT_WRONG;
                String labeledFilename = truncatedFilename + '_' + j + "_" + correctedClass + "." + ext;
                ImageHandler.saveImage(ImageHandler.cropImageToLabel(originalImage, labels.get(j)), labeledFilename, correctedClass);
            }
        }
    }

    public static void clearFolders() {
        System.err.println("DELETING");
        File[] files;
        files = new File(POSITIVE_IMAGES_FOLDER).listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f: files) {
                f.delete();
            }
        }
        files = new File(NEGATIVE_IMAGES_FOLDER).listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f: files) {
                f.delete();
            }
        }
    }

    public static void createFolders() {
        File positiveImagesFolder = new File(POSITIVE_IMAGES_FOLDER);
        File negativeImagesFolder = new File(NEGATIVE_IMAGES_FOLDER);

        positiveImagesFolder.mkdir();
        negativeImagesFolder.mkdir();
    }
}
