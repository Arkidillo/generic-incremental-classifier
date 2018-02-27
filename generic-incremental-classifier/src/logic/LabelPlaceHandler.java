package logic;

import gui.Label;
import main.GenericIncrementalClassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelPlaceHandler implements MouseListener {
    private static final byte LEFT = 0;
    private static final byte RIGHT = 1;
    private static final byte TOP = 2;
    private static final byte BOTTOM = 3;

    private byte state = 0;
    private Label newLabel;

    // the last label they have clicked on, if any
    public static Label selectedLabel;

    private GUIHandler gui;
    // we need the insets to correct for JFrame decoration
    private Insets insets;

    private JLabel textBox;

    public LabelPlaceHandler(GUIHandler gui, Insets insets) {
        state = LEFT;
        newLabel = new Label();
        this.gui = gui;
        this.insets = insets;
        showInstructions();
    }

    public void showInstructions() {
        // create new frame and add text box to it.
        JFrame instructionFrame = new JFrame();
        instructionFrame.setSize(450, 75);
        instructionFrame.setLayout(new FlowLayout());
        instructionFrame.setLocation(0, GenericIncrementalClassifier.WINDOW_HEIGHT);

        JLabel text = new JLabel("Click left most point", JLabel.CENTER);

        text.setFont(new Font(text.getFont().getName(), text.getFont().getStyle(), 20));
        textBox = text;

        instructionFrame.getContentPane().add(text);

        instructionFrame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        // adjust x,y b/c of insets
        int adjX = e.getX() - insets.left;
        int adjY = e.getY() - insets.top;

        // if not already creating, check if this click is selecting a label
        if (state == 0) {
            gui.callRepaint();
            selectedLabel = gui.getLabelOnClick(new Point(adjX, adjY));
            // (if so, don't start creating a new one)
            if (selectedLabel != null) return;
        }

        // set the corresponding value based on where we are in the state machine
        switch (state){
            case LEFT:
                newLabel.setLeft(adjX);
                textBox.setText("Click right most point");
                break;
            case RIGHT:
                newLabel.setRight(adjX);
                textBox.setText("Click top most point");
                break;
            case TOP:
                newLabel.setTop(adjY);
                textBox.setText("Click bottom most point");
                break;
            case BOTTOM:
                newLabel.setBottom(adjY);
                textBox.setText("DONE. Click left to start new");

                // add the new label to the pane to display
                gui.addLabel(newLabel);

                // this will be the selected label by default
                selectedLabel = newLabel;

                // create a new label for the next one
                newLabel = new Label();

                break;
        }

        state = (byte)((state + 1) % 4);
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
