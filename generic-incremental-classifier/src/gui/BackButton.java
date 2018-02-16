package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BackButton extends JButton {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 35;
    public static final String ACTION_COMMAND = "<-Back";

    public BackButton(Dimension dim) {
        super(ACTION_COMMAND);
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.LEADING);
        setMnemonic(KeyEvent.VK_ENTER);
        setSize(WIDTH, HEIGHT);
        setLocation(
                (int)(dim.getWidth()*0.85),
                (int)(dim.getHeight()*0.87)
        );
    }
}
