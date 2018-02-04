import javax.swing.*;
import java.awt.*;

public class OurFrame extends JFrame {
    public OurFrame(Dimension dim, ImagePane pane) {
        setPreferredSize(dim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);

        setVisible(true);

        add(pane);
        pack();
    }
}
