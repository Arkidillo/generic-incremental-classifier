package logic;

import gui.GUIHandler;
import gui.ImagePane;
import gui.Label;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelPlaceHandler implements MouseListener {
    private static final byte LEFT = 0;
    private static final byte RIGHT = 1;
    private static final byte TOP = 2;
    private static final byte BOTTOM = 3;

    private byte state;
    private Label newLabel;

    private GUIHandler gui;

    public LabelPlaceHandler(GUIHandler gui) {
        state = LEFT;
        newLabel = new Label();
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (state){
            case LEFT:
                newLabel.setLeft(e.getX());
                System.out.println("left most placed");
                System.out.println("Click:");
                System.out.println("right most point");
                break;
            case RIGHT:
                newLabel.setRight(e.getX());
                System.out.println("top most point");
                break;
            case TOP:
                newLabel.setTop(e.getY());
                System.out.println("bottom most point");
                break;
            case BOTTOM:
                newLabel.setBottom(e.getY());
                System.out.println("DONE PLACING");

                // add the new label to the pane to display
                gui.addLabel(newLabel);

                // create a new label for the next one
                newLabel = new Label();

                break;
        }

        state = (byte)((state + 1) % 4);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
