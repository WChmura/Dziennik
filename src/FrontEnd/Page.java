package FrontEnd;

import Common.MockModel;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JApplet {
    protected JPanel mainContent;
    private TopPanel topPanel;
    static MockModel model;
    public void init() {
        if(model==null) {
            model = new MockModel();
        }
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
    protected void addPanel(){
        topPanel = new TopPanel(this.getAppletContext(), model);
        this.setJMenuBar(topPanel);
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(true);
        mainContent.setBackground(Colors.background);
        setContentPane(mainContent);
    }
    public abstract void createGUI();
}