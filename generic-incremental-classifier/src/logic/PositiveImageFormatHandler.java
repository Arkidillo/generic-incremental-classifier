package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.Label;
import model.Output;
import util.ImageHandler;
import util.Utils;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import static util.ImageHandler.POSITIVE_IMAGES_FOLDER;

public class PositiveImageFormatHandler {
    private static StringBuilder fullCsv = new StringBuilder();
    public static void saveLabels(String pathname, List<Label> labels) {
        labels.forEach(l -> fullCsv.append(generateCsv(pathname, l)));
    }

    public static void outputFullCsv() {
        Utils.saveStrToFile(fullCsv.toString(), POSITIVE_IMAGES_FOLDER + "labeled_data.csv");
    }

    public static String generateJson(BufferedImage image, String fileType, String pathname, List<Label> labels) {
        GsonBuilder builder = new GsonBuilder();
        builder.setVersion(2.0);

        Gson gson = builder.create();
        Output output = new Output(image, fileType, pathname, labels);
        return gson.toJson(output);
    }

    public static String generateCsv(String pathname, Label label) {
        List<String> rowStrs = Arrays.asList(pathname, Integer.toString(label.getX()), Integer.toString(label.getY()),
                Integer.toString(label.getX() + label.getWidth()), Integer.toString(label.getY() + label.getHeight()),
                Integer.toString(ImageHandler.POSITIVE_IMAGE));
        return addRowFormatting(rowStrs);
    }

    private static String addRowFormatting(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.size() - 1; i++) {
            sb.append(strs.get(i));
            sb.append(",");
        }
        sb.append(strs.get(strs.size() - 1));
        sb.append('\n');
        return sb.toString();
    }
}
