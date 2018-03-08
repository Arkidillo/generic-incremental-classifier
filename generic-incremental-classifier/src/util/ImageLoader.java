package util;

import main.GenericIncrementalClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    // folder that our images will be stored in
    public static final File IMAGE_FOLDER = new File("./images/");

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
            image = ImageIO.read(new File("./images/" + fileName));
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

    public static BufferedImage resizeImage(Image image, int width, int height){
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // draw Image onto the BufferedImage
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bi;
    }

    // return next image in the 'images' folder.
    public static BufferedImage loadNextImage() {
        File[] listOfFiles = IMAGE_FOLDER.listFiles();

        // ensure the folder exists
        if (listOfFiles == null) {
            System.err.println("ERROR: Folder is empty or cannot be found");
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
    public static BufferedImage loadPrevImage() {
        File[] listOfFiles = IMAGE_FOLDER.listFiles();

        // ensure the folder exists
        if (listOfFiles == null) {
            System.err.println("ERROR: Folder is empty or cannot be found");
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