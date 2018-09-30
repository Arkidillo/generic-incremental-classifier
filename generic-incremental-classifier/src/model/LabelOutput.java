package model;

import gui.Label;

public class LabelOutput {
    public BoundingBoxOutput bounding_box;
    public String category;

    public LabelOutput(Label label) {
        bounding_box = new BoundingBoxOutput(label);
        category = "1";
    }
}
