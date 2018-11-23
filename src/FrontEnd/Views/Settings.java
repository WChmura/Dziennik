package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Settings extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Settings");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
