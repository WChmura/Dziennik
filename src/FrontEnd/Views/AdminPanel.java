package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("AdminPanel");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
