package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Messages extends Page {

    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Settings");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
