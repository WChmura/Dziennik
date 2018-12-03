package FrontEnd;

import Common.MockModel;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JApplet {
    protected JPanel mainContent;
    protected JPanel mainPanel;
    protected static MockModel model;
    protected int numOfSubPanels;
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
    protected void addTopMenu(int numOfPanels){
        this.numOfSubPanels = numOfPanels;
        TopPanel topPanel = new TopPanel(this.getAppletContext(), model);
        this.setJMenuBar(topPanel);
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(true);
        mainContent.setBackground(Colors.background);
        setContentPane(mainContent);

        mainPanel = new JPanel();
        mainPanel.setSize(mainContent.getWidth(),mainContent.getHeight());
        mainPanel.setLayout(new GridLayout(numOfSubPanels,1,0,5));
        mainContent.add(mainPanel,BorderLayout.NORTH);
    }
    protected void addSubPanel(JPanel subPanel,int height){
        subPanel.setSize(mainPanel.getWidth(),height);
        mainPanel.add(subPanel);
    }
    public abstract void createGUI();
}