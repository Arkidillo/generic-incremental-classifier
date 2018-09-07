package gui;

import java.awt.*;

public class Label {
    public static final float THICKNESS = 3;
    public static final Color COLOR = Color.BLACK;

    private Point topLeft = new Point();
    private int width;
    private int height;

    public void setLeft(int x) {
        topLeft.x = x;
    }
    public void setRight(int x) {
        if (x < topLeft.x) {
            width = topLeft.x - x;
            topLeft.x = x;
        } else {
            width = x - topLeft.x;
        }
    }
    public void setTop(int y) {
        topLeft.y = y;
    }
    public void setBottom(int y) {
        if (y < topLeft.y) {
            height = topLeft.y - y;
            topLeft.y = y;
        } else {
            height = y - topLeft.y;
        }
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

    // is this Point inside this Label?
    public boolean isClickInside(Point p) {
        double x = p.getX();
        double y = p.getY();

        return  x > topLeft.getX() &&
                x < topLeft.getX() + width &&
                y > topLeft.getY() &&
                y < topLeft.getY() + height;
    }

    public int getCartesianLeftX() {
        return getX();
    }
    public int getCartesianRightX() {
        return getX() + getWidth();
    }
    public int getCartesianBottomY(int imageHeight) {
        return imageHeight - (getY() + getHeight());
    }
    public int getCartesianTopY(int imageHeight) {
        return imageHeight - getY();
    }
}
