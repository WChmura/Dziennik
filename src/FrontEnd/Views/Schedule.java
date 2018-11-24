package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Schedule extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Schedule");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
