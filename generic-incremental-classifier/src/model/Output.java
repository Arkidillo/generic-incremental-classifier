package model;

import gui.Label;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Output {
    public ImageOutput image;
    public List<LabelOutput> objects;

    public Output(BufferedImage im, String fileType, String pathname, List<Label> labels) {
        // Setup image
        image = new ImageOutput(im, fileType, pathname);

        // Setup objects
        objects = new ArrayList<>();

        for (int i = 0; i < labels.size(); i++) {
            objects.add(new LabelOutput(labels.get(i)));
        }
    }
}
