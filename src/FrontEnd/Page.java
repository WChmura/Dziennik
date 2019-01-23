package FrontEnd;
import Common.UserType;
import Models.*;
import javax.swing.*;
import java.awt.*;

public abstract class Page extends JFrame {
    protected JPanel mainContent;
    private JPanel mainPanel;
    static String userName = " ";
    protected static UserType userType = UserType.notLogged;
    protected Model model;
    protected TopPanel topPanel;
    protected String receivedValue;

    public Page(){
        super("Dziennik elektroniczny");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize(8*d.width/9,8*d.height/9);
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Page(String value){
        super("Dziennik elektroniczny");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize(8*d.width/9,8*d.height/9);
        receivedValue =value;
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void addTopMenu(int numOfPanels){
        topPanel = new TopPanel(this,model);
        this.setJMenuBar(topPanel);
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(true);
        mainContent.setBackground(Colors.background);
        setContentPane(mainContent);

        mainPanel = new JPanel();
        mainPanel.setBackground(Colors.main);
        mainPanel.setLayout(new GridLayout(numOfPanels,1,0,5));
        mainContent.add(mainPanel,BorderLayout.NORTH);
    }

    protected void addSubPanel(JPanel subPanel){
        subPanel.setBackground(Colors.main);
        mainPanel.add(subPanel);
    }

    protected void addEmptyPanel(){
        this.addSubPanel(new JPanel());
    }

    protected void addSubPanel(JPanel subPanel,int num){
        subPanel.setBackground(Colors.main);
        mainPanel.add(subPanel,num);
    }

    protected void deletePanel(int num){
        mainPanel.remove(num);
    }

    protected boolean confirmationPane(){
        int n = JOptionPane.showConfirmDialog(this, "Na pewno?", "", JOptionPane.YES_NO_OPTION);
        return n != 1;
    }

    public abstract void createGUI();

    protected Model createNewModel(){
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