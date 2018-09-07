package gui;

import gui.buttons.BackButton;
import gui.buttons.DeleteButton;
import gui.buttons.DoneButton;
import gui.buttons.NextButton;
import logic.GUIHandler;
import logic.LabelPlaceHandler;

import javax.swing.*;
import java.awt.*;

public class OurFrame extends JFrame {

    private ImagePane imagePane;

    public OurFrame(Dimension dim, GUIHandler gui, ImagePane imagePane) {
        // set-up frame
        setSize(dim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        setVisible(true);

        // add imagePane
        this.imagePane = imagePane;
        add(imagePane);

        // add buttons
        NextButton nextButton = new NextButton(dim);
        BackButton backButton = new BackButton(dim);
        DoneButton doneButton = new DoneButton(dim);
        DeleteButton deleteButton = new DeleteButton(dim);

        imagePane.add(nextButton, 5);
        imagePane.add(backButton, 5);
        imagePane.add(doneButton, 5);
        imagePane.add(deleteButton, 5);

        // the GUIHandler is listening for these button presses
        nextButton.addActionListener(gui);
        backButton.addActionListener(gui);
        deleteButton.addActionListener(gui);
        doneButton.addActionListener(gui);

        // add mouselistener/ labelhandler
        addMouseListener(new LabelPlaceHandler(gui, getInsets()));
    }
}
