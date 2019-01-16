package FrontEnd;

import Common.UserType;
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
        setSize(d.width, d.height/10);
        JPanel panel = new JPanel(new GridLayout(1,9));
        panel.setBackground(Colors.topPanelbackground);
        JButton marksButton = configureButton(marksText);
        panel.add(marksButton);

        JButton scheduleButton = configureButton(scheduleText);
        panel.add(scheduleButton);

        JButton attendanceButton = configureButton(attendanceText);
        panel.add(attendanceButton);

        if(userType!=admin) {
            JButton settingsButton = configureButton(settingsText);
            panel.add(settingsButton);
        }

        JButton messagesButton = configureButton(messagesText);
        panel.add(messagesButton);

        if(userType ==teacher|| userType ==admin) {
            JButton groupButton = configureButton(groupText);
            panel.add(groupButton);

            if(userType ==teacher) {
                JButton lessonButton = configureButton(lessonText);
                panel.add(lessonButton);
            }
            if(userType ==admin) {
                JButton adminButton = configureButton(adminText);
                panel.add(adminButton);
            }
        }
        else{
            for(int i=0;i<2;i++)
                panel.add(new JLabel());
        }
        panel.add(new JLabel("    zalogwano jako: "+ userName));
        if(userType ==notLogged){
            JButton logInButton = configureButton(logInText);
            panel.add(logInButton);
        }
        else{
            JButton logOutButton = configureButton(logOutText);
            panel.add(logOutButton);
        }
        add(panel);
    }

    private JButton configureButton(String text){
        JButton button = new JButton(text);
        button.setActionCommand(text);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBackground(Colors.background);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(userType !=notLogged) {
            String command = e.getActionCommand();
            System.out.println(command);
            if(command.equals(logOutText)){
                if(logOutMessage()) {
                    userType = notLogged;
                    userName = null;
                    goToPage("startowa");
                }
            }
            else
                goToPage(command);
        }

        else if(e.getActionCommand().equals(logInText)){
            LoginForm sd = new LoginForm(null);
            sd.setVisible(true);
            String[] loginData= sd.getData();
            if(loginData!=null) {
                userType = model.logIn(loginData);
                userName = loginData[0];
                if(userType!=admin){
                    goToPage("personalData");
                }
                else{
                    goToPage(adminText);
                }

            }else {
                    System.out.println("zle dane");
                }
            }
    }

    public void goToPage(String sth){

        frame.dispose();//To close the current window
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
    }

    public void goToPage(String sth,String login){

        frame.dispose();//To close the current window
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
    }

    private boolean logOutMessage(){
        int n = JOptionPane.showConfirmDialog(
                this,
                "Jestes pewien?",
                "",
                JOptionPane.YES_NO_OPTION);
        return n != 1;
    }
}
