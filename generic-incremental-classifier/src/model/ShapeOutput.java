package model;

import java.awt.image.BufferedImage;

public class ShapeOutput {
    public int c;
    public int r;
    public int channels;

    public ShapeOutput(BufferedImage image) {
        r = image.getWidth();
        c = image.getHeight();

        // Assuming everything has 3 channels
        channels = 3;
    }
}
