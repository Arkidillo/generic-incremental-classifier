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

        //add buttons
        NextButton nextButton = new NextButton(dim);
        BackButton backButton = new BackButton(dim);
        DeleteButton deleteButton = new DeleteButton(dim);

        pane.add(nextButton, 5);
        pane.add(backButton, 5);
        pane.add(deleteButton, 5);

        nextButton.addActionListener(gui);
        backButton.addActionListener(gui);
        deleteButton.addActionListener(gui);
    }

}
