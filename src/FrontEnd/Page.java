package FrontEnd;

import Common.MockModel;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JApplet {
    protected JPanel mainContent;
    private JPanel mainPanel;
    private JPanel subPanels[];
    private int numOfSubPanels=0;
    protected static MockModel model;
    protected TopPanel topPanel;

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
        topPanel = new TopPanel(this.getAppletContext(), model);
        this.setJMenuBar(topPanel);
        this.setBackground(Colors.background);
        //this.setForeground(Colors.background);
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(true);
        mainContent.setBackground(Colors.background);
        setContentPane(mainContent);

        mainPanel = new JPanel();
        mainPanel.setBackground(Colors.main);
        mainPanel.setSize(mainContent.getWidth(),mainContent.getHeight());
        mainPanel.setLayout(new GridLayout(numOfPanels,1,0,5));
        mainContent.add(mainPanel,BorderLayout.NORTH);
        subPanels = new JPanel[numOfPanels];
    }
    protected void addSubPanel(JPanel subPanel,int height){
        subPanel.setSize(mainPanel.getWidth(),height);
        subPanel.setBackground(Colors.main);
        mainPanel.add(subPanel);
        subPanels[numOfSubPanels++]=subPanel;
    }
    protected void addEmptyPanel(int height){
        JPanel panel = new JPanel();
        this.addSubPanel(panel,height);
    }
    protected void replacePanel(JPanel subPanel, int height, int num){
        System.out.println("Trying to replace");
        mainPanel.remove(num);
        subPanel.setSize(mainPanel.getWidth(),height);
        subPanel.setBackground(Colors.main);
        mainPanel.add(subPanel,num);
    }
    public abstract void createGUI();
}