package FrontEnd;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JApplet {
    public JPanel mainContent;
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
    public void addPanel(){
        TopPanel topPanel = new TopPanel(this.getAppletContext());
        this.setJMenuBar(topPanel);
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(true);
        mainContent.setBackground(Colors.background);
        setContentPane(mainContent);
    }
    public abstract void createGUI();
}