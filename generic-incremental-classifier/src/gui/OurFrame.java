package gui;

import javax.swing.*;
import java.awt.*;

public class OurFrame extends JFrame {

    private ImagePane imagePane;

    public OurFrame(Dimension dim, GUIHandler gui, ImagePane pane) {
        // set-up frame
        setSize(dim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        setVisible(true);

        // add imagePane
        this.imagePane = pane;
        add(pane);

        //add button
        NextButton nextButton = new NextButton(dim);
        pane.add(nextButton, 5);
        nextButton.addActionListener(gui);
    }

}
