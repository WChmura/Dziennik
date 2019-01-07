package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Settings extends Page {
    //to potrzebuje
    //metodka na zmiane hasła (własnego)
    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Settings");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
