package logic;

import com.google.gson.Gson;
import gui.Label;
import util.ImageHandler;
import util.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GsonHandler {
    private static final String LABELS_FOLDER = "./labels/";
    private static final String POSITIVE_IMAGES_FOLDER = "./positive_images/";
    private static final String NEGATIVE_IMAGES_FOLDER = "./negative_images/";
    private static final String FILE_SUFFIX = "_labels.json";

    public GsonHandler(HashMap<String, ArrayList<Label>> allLabels) {
        // Make sure the folder is cleared
        createFolders();
        //clearFolders();

        // For each image/ entry in hashmap, save a JSON
        Iterator it = allLabels.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            // The pair is (imageName, Arraylist of labels)
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Label> labels = (ArrayList<Label>) (pair.getValue());
            String imageName = (String)pair.getKey();

            String imageFolder;
            // If there were no labels for the given image, save it to the negative image folder
            if (labels.size() == 0) imageFolder = NEGATIVE_IMAGES_FOLDER;
            else                    imageFolder = POSITIVE_IMAGES_FOLDER;

            // Copy the image from the original folder to the correct neg./pos. folder
            File sourceImage = new File("./" + ImageHandler.IMAGE_FOLDER.getName() + "/" + imageName);
            File destinationImage = new File(imageFolder + imageName);
            // Create the new image file so java won't complain it doesn't exist
            try {
                destinationImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Utils.copyFile(sourceImage, destinationImage);

            Gson gson = new Gson();
            String jsonString = gson.toJson(labels);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(LABELS_FOLDER + imageName + FILE_SUFFIX));
                writer.write(jsonString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public void clearFolders() {
        File[] files;
        files = new File(LABELS_FOLDER).listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f: files) {
                f.delete();
            }
        }
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

    public void createFolders() {
        File labelsFolder = new File(LABELS_FOLDER);
        File positiveImagesFolder = new File(POSITIVE_IMAGES_FOLDER);
        File negativeImagesFolder = new File(NEGATIVE_IMAGES_FOLDER);

        labelsFolder.mkdir();
        positiveImagesFolder.mkdir();
        negativeImagesFolder.mkdir();
    }
}
