package util;

import gui.Label;
import main.GenericIncrementalClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    //public static final String TESTING_EXTRA_PATH = "generic-incremental-classifier/";
    public static final String TESTING_EXTRA_PATH = "";

    // folder that our images will be stored in
    public static final String IMAGE_FOLDER = "./" + TESTING_EXTRA_PATH + "initial_train_images/";
    public static final File CURR_TEST_BATCH_FOLDER = new File("./" + TESTING_EXTRA_PATH + "test_images");

    public static final String POSITIVE_IMAGES_FOLDER = "./" + TESTING_EXTRA_PATH + "positive_images/";
    public static final String NEGATIVE_IMAGES_FOLDER = "./" + TESTING_EXTRA_PATH + "negative_images/";

    // macro determining whether the image is a positive or negative example
    public static final byte NEGATIVE_IMAGE = 0;
    public static final byte NO_MODEL_LABEL = 1;
    public static final byte MODEL_CORRECT_LABEL = 2;

    // keeps track of the index of the image we are on
    // (index into the folder.listFiles() array)
    private static int imageIndex = -1;
    private static String currFileName;

    public static String getCurrentFileName() {
        return currFileName;
    }

    // take filename of image in 'images' folder and return ImageIcon object
    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(IMAGE_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    // read the BufferedImage from a File
    public static BufferedImage loadImage(File file){
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            System.err.println("ERROR: Could not call loadImage on : " + file.getName());
            return null;
        }

        int defaultWidth = GenericIncrementalClassifier.WINDOW_WIDTH;
        int defaultHeight = GenericIncrementalClassifier.WINDOW_HEIGHT;

        Integer newWidth = image.getWidth();
        Integer newHeight = image.getHeight();
        if (image.getWidth() > defaultWidth){
            newWidth = defaultWidth;
        }
        if (image.getHeight() > defaultHeight){
            newHeight = defaultHeight;
        }

        if (newWidth != image.getWidth() || newHeight != image.getHeight()) {
            System.out.println("Resized to (" + newWidth + ", " + newHeight + ")");
            image = resizeImage(image, newWidth, newHeight);
        }

        return image;
    }

    // save the given image to an image file (as whatever image type it started as)
    public static void saveImage(BufferedImage image, String imageName, byte positiveOrNegative) {
        String filePath;
        if (positiveOrNegative == NO_MODEL_LABEL) {
            filePath = POSITIVE_IMAGES_FOLDER;
        } else {
            filePath = NEGATIVE_IMAGES_FOLDER;
        }

        filePath += imageName;

        File outputfile = new File(filePath);
        try {
            outputfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (!ImageIO.write(image, Utils.getExtension(filePath), outputfile)) {
                System.err.println("Image write error!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage resizeImage(Image image, int width, int height){
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // draw Image onto the BufferedImage
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bi;
    }

    // given the rectangular label, crop the image to just the label
    public static BufferedImage cropImageToLabel(BufferedImage image, Label label) {
        return image.getSubimage(label.getX(), label.getY(), label.getWidth(), label.getHeight());
    }

    // return next image in the given folder.
    public static BufferedImage loadNextImage(File folder) {
        File[] listOfFiles = folder.listFiles();

        // ensure the folder exists
        if (listOfFiles == null || listOfFiles.length == 0) {
            System.err.println("ERROR: Folder is empty or cannot be found. Path: " + folder.getAbsolutePath());
            return null;
        }

        // read the next image and increment the index, unless we have reached the list image
        if (imageIndex < listOfFiles.length - 1) {
            File currFile = listOfFiles[++imageIndex];
            currFileName = currFile.getName();
            return loadImage(currFile);
        } else {
            File currFile = listOfFiles[listOfFiles.length - 1];
            currFileName = currFile.getName();
            return loadImage(currFile);
        }
    }

    // return next image in the 'images' folder.
    public static BufferedImage loadPrevImage(File folder) {
        File[] listOfFiles = folder.listFiles();

        // ensure the folder exists
        if (listOfFiles == null) {
            System.err.println("ERROR: Folder is empty or cannot be found. Path: " + folder.getAbsolutePath());
        }

        // read the next image and increment the index, unless we have reached the list image
        if (imageIndex > 0) {
            File currFile = listOfFiles[--imageIndex];
            currFileName = currFile.getName();
            return loadImage(currFile);
        } else {
            File currFile = listOfFiles[0];
            currFileName = currFile.getName();
            return loadImage(currFile);
        }
    }
}