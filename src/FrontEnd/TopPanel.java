package FrontEnd;
import FrontEnd.Forms.LoginForm;
import FrontEnd.Views.*;
import Models.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import static Common.UserType.*;
import static FrontEnd.Page.userName;
import static FrontEnd.Page.userType;

public class TopPanel extends JMenuBar implements ActionListener {
    private Model model;
    private JFrame frame;
    private final String marksText = "Oceny";
    private final String messagesText = "Wiadomosci";
    private final String scheduleText = "Plan Zajec";
    private final String attendanceText = "Obecnosci";
    private final String settingsText = "Ustawienia";
    private final String groupText = "Klasa";
    private final String lessonText = "Lekcja";
    private final String adminText = "Panel Administratora";
    private final String logInText = "Zaloguj";
    private final String logOutText = "Wyloguj";

    TopPanel( JFrame frame,Model model) {
        super();
        this.model = model;
        this.frame = frame;

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(8*d.width/9, d.height/10);
        JPanel panel = new JPanel(new GridLayout(1,9));
        panel.setBackground(Colors.topPanelbackground);

        panel.add(configureButton(marksText));
        panel.add(configureButton(scheduleText));
        panel.add(configureButton(attendanceText));
        if(userType!=admin)
            panel.add(configureButton(settingsText));
        panel.add(configureButton(messagesText));

        if(userType ==teacher || userType ==admin) {
            panel.add(configureButton(groupText));
            if(userType ==teacher)
                panel.add(configureButton(lessonText));
            else
                panel.add(configureButton(adminText));
        }

        if(userType ==notLogged)
            panel.add(configureButton(logInText));
        else{
            panel.add(configureButton(logOutText));
            panel.add(new JLabel("zalogwano jako: "+ userName));
        }
        add(panel);
    }

    private JButton configureButton(String text){
        JButton button = new JButton(text);
        button.setActionCommand(text);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(userType !=notLogged) {
            if(command.equals(logOutText)&&logOutMessage()){
                if(logOutMessage()) {
                    userType = notLogged;
                    userName = null;
                    goToPage("startowa");
                }
            }
            else
                goToPage(command);
        }

        else if(command.equals(logInText)){
            LoginForm sd = new LoginForm(null,model);

            String[] loginData= sd.getData();
            if(loginData!=null) {
                userType = model.logIn(loginData);
                userName = loginData[0];
                if(userType!=admin)
                    goToPage("personalData");
                else
                    goToPage(adminText);
            }
            else
                JOptionPane.showMessageDialog(frame, "Zle haslo lub receivedValue");
        }
    }

    public void goToPage(String sth){
        Page newWindow;
        switch(sth){
            case marksText:
                newWindow = new Marks();
                break;
            case messagesText:
                newWindow = new Messages();
                break;
            case scheduleText:
                newWindow = new Schedule();
                break;
            case attendanceText:
                newWindow = new Attendance();
                break;
            case settingsText:
                newWindow = new Settings();
                break;
            case groupText:
                newWindow = new Group();
                break;
            case lessonText:
                newWindow = new Lesson();
                break;
            case adminText:
                newWindow = new AdminPanel();
                break;
            case "startowa":
                newWindow = new WelcomePage();
                break;
            case "personalData":
                newWindow = new PersonalData();
                break;
                default:
                    newWindow = new WelcomePage();
        }
        newWindow.setVisible(true);
        frame.dispose();
    }

    public void goToPage(String sth,String login){
        Page newWindow;
        switch(sth){
            case marksText:
                newWindow = new Marks(login);
                break;
            case attendanceText:
                newWindow = new Attendance(login);
                break;
            case "personalData":
                newWindow = new PersonalData(login);
                break;
            default:
                newWindow = new WelcomePage();
        }
        newWindow.setVisible(true);
        frame.dispose();
    }

    private boolean logOutMessage(){
        int n = JOptionPane.showConfirmDialog(this, "Jestes pewien?", null, JOptionPane.YES_NO_OPTION);
        return n != 1;
    }
}
