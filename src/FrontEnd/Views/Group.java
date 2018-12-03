package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Group extends Page {

    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Group");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
