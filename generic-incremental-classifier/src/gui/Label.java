package gui;

import java.awt.*;

public class Label {
    private Point topLeft = new Point();
    private int width;
    private int height;

    public void setLeft(int x) {
        topLeft.x = x;
    }
    public void setRight(int x) {
        width = x - topLeft.x;
    }
    public void setTop(int y) {
        topLeft.y = y;
    }
    public void setBottom(int y) {
        height = y - topLeft.y;
    }

    public int getX() {
        return (int)topLeft.getX();
    }
    public int getY() {
        return (int)topLeft.getY();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
