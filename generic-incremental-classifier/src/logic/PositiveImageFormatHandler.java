package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.Label;
import model.Output;
import util.Utils;

import java.awt.image.BufferedImage;
import java.util.List;

import static util.ImageHandler.POSITIVE_IMAGES_FOLDER;

public class PositiveImageFormatHandler {
    public static void saveLabels(BufferedImage image, String fileType, String pathname, List<Label> labels) {
        String outputJson = generateJson(image, fileType, pathname, labels);

        // Get the starting name of the image
        String truncatedFilename = pathname.substring(0, pathname.length() - (fileType.length() + 1));
        Utils.saveStrToFile(outputJson, POSITIVE_IMAGES_FOLDER + truncatedFilename + ".json");
    }

    public static String generateJson(BufferedImage image, String fileType, String pathname, List<Label> labels) {
        GsonBuilder builder = new GsonBuilder();
        builder.setVersion(2.0);

        Gson gson = builder.create();
        Output output = new Output(image, fileType, pathname, labels);
        return gson.toJson(output);
    }
}
