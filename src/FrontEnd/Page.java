package FrontEnd;

import Common.UserType;
import Models.*;

import javax.swing.*;
import java.awt.*;


public abstract class Page extends JFrame {
    protected JPanel mainContent;
    private JPanel mainPanel;
    private JPanel[] subPanels;
    private int numOfSubPanels=0;
    //protected static String userName = "qazxsw123";
    //protected static UserType userType=UserType.teacher;
    static String userName = " ";
    protected static UserType userType = UserType.notLogged;
    protected Model model;
    protected TopPanel topPanel;

    public Page(){
        super("Hello World");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize(d.width,2*d.height/3);
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Page(String value){
        super("Hello World");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize(d.width,2*d.height/3);
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void addTopMenu(int numOfPanels){
        topPanel = new TopPanel(this,model);
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
        System.out.println("Create new model");
        System.out.println(userType);
        switch (userType) {
            case admin:
                return new AdminPanel(userName);
            case teacher:
                return new TeacherPanel(userName);
            case parent:
                return new ParentPanel(userName);
            case student:
                return new StudentPanel(userName);
            default:
                return new Authentication();
        }
    }
}