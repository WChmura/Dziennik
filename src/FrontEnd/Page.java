package FrontEnd;

import Common.UserType;
import Models.*;

import javax.swing.*;
import java.awt.*;

import static Common.UserType.student;

public abstract class Page extends JApplet {
    protected JPanel mainContent;
    private JPanel mainPanel;
    private JPanel subPanels[];
    private int numOfSubPanels=0;
    protected static String userName;
    protected static UserType userType=UserType.teacher;
    protected static Model model;
    protected TopPanel topPanel;

    public void init() {
        if(model==null) {
            model = new TeacherPanel();
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    System.out.println("Tworzenie GUI");
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
    protected void addTopMenu(int numOfPanels){
        topPanel = new TopPanel(this.getAppletContext(), model,userType);
        this.setJMenuBar(topPanel);
        this.setBackground(Colors.background);
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

    protected void addSubPanel(JPanel subPanel, int height, int num){
        subPanel.setSize(mainPanel.getWidth(),height);
        subPanel.setBackground(Colors.main);
        mainPanel.add(subPanel,num);
        System.out.println("Teoretycznie dodano");
        //TODO:jakis repaint? Bo ma laga
    }
    protected void deletePanel(int num){
        mainPanel.remove(num);
    }
    protected boolean confirmationPane(){
        int n = JOptionPane.showConfirmDialog(
                this,
                "Na pewno?",
                "",
                JOptionPane.YES_NO_OPTION);
        return n != 1;
    }
    public abstract void createGUI();

    protected Model createNewModel(){
        switch (userType) {
            case admin:
                return new AdminPanel();
            case teacher:
                return new TeacherPanel(userName);
            case parent:
                return new ParentPanel();
            case student:
                return new StudentPanel();
        }
        return new Authentication();
    }
}