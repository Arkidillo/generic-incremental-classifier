package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class DeleteButton extends JButton {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 35;
    public static final String ACTION_COMMAND = "Delete";

    public DeleteButton(Dimension dim) {
        super(ACTION_COMMAND);
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.LEADING);
        setMnemonic(KeyEvent.VK_ENTER);
        setSize(WIDTH, HEIGHT);
        setLocation(
                (int)(dim.getWidth()*0.92),
                (int)(dim.getHeight()*0.805)
        );
    }
}
