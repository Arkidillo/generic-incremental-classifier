package model;

import gui.Label;

public class BoundingBoxOutput {
    public MinimumBoundingBoxOutput minimum;
    public MaximumBoundingBoxOutput maximum;

    public BoundingBoxOutput(Label label) {
        minimum = new MinimumBoundingBoxOutput();
        maximum = new MaximumBoundingBoxOutput();

        minimum.r = label.getX();
        minimum.c = label.getY();

        maximum.r = label.getX() + label.getWidth();
        maximum.c = label.getY() + label.getHeight();
    }
}
