package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Messages extends Page {
    //nie ma jeszcze takich klas co ja bym chciał
    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Settings");
        mainContent.add(button, BorderLayout.SOUTH);
    }
}
