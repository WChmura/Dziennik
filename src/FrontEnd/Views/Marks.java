package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Marks extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Marks");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
