package FrontEnd;

import Common.UserType;
import FrontEnd.Forms.LoginForm;
import Models.*;

import javax.swing.*;

import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import static Common.UserType.*;
import static FrontEnd.Page.userName;

public class TopPanel extends JMenuBar implements ActionListener {
    private Model model;
    private UserType userType;
    private AppletContext appletContext;
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

    TopPanel(AppletContext appletContext, Model model,UserType userType) {
        super();
        this.appletContext = appletContext;
        this.model = model;
        this.userType = userType;
        JPanel panel = new JPanel(new GridLayout(1,9));
        panel.setBackground(Colors.topPanelbackground);
        JButton marksButton = configureButton(marksText);
        panel.add(marksButton);

        JButton scheduleButton = configureButton(scheduleText);
        panel.add(scheduleButton);

        JButton attendanceButton = configureButton(attendanceText);
        panel.add(attendanceButton);

        JButton settingsButton = configureButton(settingsText);
        panel.add(settingsButton);

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
        panel.add(new JLabel("    zalogwano jako: "/*+ Model.userName*/));
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
            String link = "http://localhost:63342/Dziennik/out/production/Dziennik/";
            switch (command) {
                case marksText:
                    link += "Marks.html";
                    goToPage(link);
                    break;
                case scheduleText:
                    link += "Schedule.html";
                    goToPage(link);
                    break;
                case settingsText:
                    link += "Settings.html";
                    goToPage(link);
                    break;
                case groupText:
                    link += "Group.html";
                    goToPage(link);
                    break;
                case adminText:
                    link += "AdminPanel.html";
                    goToPage(link);
                    break;
                case attendanceText:
                    link += "Attendance.html";
                    goToPage(link);
                    break;
                case lessonText:
                    link += "Lesson.html";
                    goToPage(link);
                    break;
                case messagesText:
                    link +="Messages.html";
                    goToPage(link);
                    break;
                case logOutText:
                    if (logOutMessage()) {
                        link += "WelcomePage.html";
                        goToPage(link);
                    }
                    break;
            }
        }

        else if(e.getActionCommand().equals(logInText)){
            LoginForm sd = new LoginForm(null);
            sd.setVisible(true);
            String[] loginData= sd.getData();
            if(loginData!=null) {
                userType = model.logIn(loginData);
                userName = loginData[0];
                String link = "http://localhost:63342/Dziennik/out/production/Dziennik/Marks.html";
                goToPage(link);
            }else {
                    System.out.println("zle dane");
                }
            }
    }

    public void goToPage(String link){
        link+="?_ijt=9nrlhq9rllbk5phprk3hm8t6pp";
        try {
            URL u = new URL(link);
            appletContext.showDocument(u,"_self");
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
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
