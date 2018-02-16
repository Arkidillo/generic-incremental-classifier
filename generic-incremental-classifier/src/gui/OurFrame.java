package gui;

import gui.buttons.BackButton;
import gui.buttons.CreateButton;
import gui.buttons.DeleteButton;
import gui.buttons.NextButton;

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
        CreateButton createButton = new CreateButton(dim);

        pane.add(nextButton, 5);
        pane.add(backButton, 5);
        pane.add(deleteButton, 5);
        pane.add(createButton, 5);

        nextButton.addActionListener(gui);
        backButton.addActionListener(gui);
        deleteButton.addActionListener(gui);
        createButton.addActionListener(gui);
    }

}
