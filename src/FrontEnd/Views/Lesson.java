package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Lesson extends Page {

    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Lesson");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
