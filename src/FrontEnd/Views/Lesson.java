package FrontEnd.Views;

import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Lesson extends Page {

    @Override
    public void createGUI() {
        addPanel();
        JButton button = new JButton("Lesson");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
