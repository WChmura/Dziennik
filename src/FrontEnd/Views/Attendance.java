package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Attendance extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Attendance");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
