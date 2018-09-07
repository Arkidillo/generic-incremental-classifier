package logic;

import gui.Label;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import util.ImageHandler;
import util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SaveImagesHandler {
    private static final String POSITIVE_IMAGES_FOLDER = "./positive_images/";
    private static final String NEGATIVE_IMAGES_FOLDER = "./negative_images/";

    private static final String ANNOTATIONS_FOLDER = "./annotations/";
    private static final String ANNOTATION_CSV_NAME = "annotations.csv";

    public static void saveAllImages(HashMap<String, ArrayList<Label>> allLabels, HashMap<String, Point> imageSizes) {
        // Make sure the folder is cleared
        createFolders();
        //clearFolders();

        FileWriter csvWriter;
        CSVPrinter csvPrinter = null;
        try {
             csvWriter = new FileWriter(ANNOTATIONS_FOLDER + ANNOTATION_CSV_NAME);
             csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT);
        } catch (IOException e) {
            System.out.println("ERROR: Failed to create printwriter to annotations.csv");
        }

        // For each image/ entry in hashmap, save a JSON
        Iterator it = allLabels.entrySet().iterator();
        while (it.hasNext()) {
            // The pair is (imageName, Arraylist of labels)
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Label> labels = (ArrayList<Label>) (pair.getValue());
            String fileName =         (String)pair.getKey();
            Point imageSize =          imageSizes.get(fileName);

            // The image is a negative example if it has no labels
            if (labels.size() == 0) {
                //ImageHandler.saveImage(ImageHandler.loadImage(fileName), fileName, ImageHandler.NEGATIVE_IMAGE);
            }

            // If we don't have the CSV printer up, don't try to save annotations
            if (csvPrinter == null) {
                return;
            }

            // Crop all the labels out of the image
            for (int j = 0; j < labels.size(); j++) {
                Label label = labels.get(j);
                // For each label, save coordinates + filename to csv row
                try {
                    csvPrinter.printRecord(fileName, imageSize.x, imageSize.y, "obj",
                            label.getCartesianLeftX(), label.getCartesianBottomY(imageSize.y),
                            label.getCartesianRightX(), label.getCartesianTopY(imageSize.y));
                } catch (IOException e) {
                    System.out.println("ERROR: Failed to print record to csv: " + e.toString());
                    e.printStackTrace();
                }
            }

            try {
                csvPrinter.flush();
            } catch (IOException e) {
                System.out.println("ERROR: Failed to flush csvPrinter: " + e.toString());
                e.printStackTrace();
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
        File annotationsFolder = new File(ANNOTATIONS_FOLDER);

        positiveImagesFolder.mkdir();
        negativeImagesFolder.mkdir();
        annotationsFolder.mkdir();
    }
}
