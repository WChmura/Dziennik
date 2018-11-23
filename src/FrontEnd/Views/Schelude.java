package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Schelude extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Schelude");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
