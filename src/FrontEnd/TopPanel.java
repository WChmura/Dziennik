package FrontEnd;

import Common.MockModel;
import Common.UserType;
import FrontEnd.Forms.LoginForm;

import javax.swing.*;

import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import static Common.UserType.*;

public class TopPanel extends JMenuBar implements ActionListener {
    private MockModel mockModel;
    private AppletContext appletContext;
    private final String marksText = "Oceny";
    private final String scheduleText = "Plan Zajec";
    private final String attendanceText = "Obecnosci";
    private final String settingsText = "Ustawienia";
    private final String groupText = "Klasa";
    private final String lessonText = "Lekcja";
    private final String adminText = "Panel Administratora";
    private final String logInText = "Zaloguj";
    private final String logOutText = "Wyloguj";
    TopPanel(AppletContext appletContext,MockModel mockModel) {
        super();
        this.appletContext = appletContext;
        this.mockModel = mockModel;
        JPanel panel = new JPanel(new GridLayout(1,8));
        panel.setBackground(Color.white);
        JButton marksButton = configureButton(marksText);
        panel.add(marksButton);

        JButton scheduleButton = configureButton(scheduleText);
        panel.add(scheduleButton);

        JButton attendanceButton = configureButton(attendanceText);
        panel.add(attendanceButton);

        JButton settingsButton = configureButton(settingsText);
        panel.add(settingsButton);

        if(MockModel.userType ==teacher|| MockModel.userType ==admin) {
            JButton groupButton = configureButton(groupText);
            panel.add(groupButton);

            if(MockModel.userType ==teacher) {
                JButton lessonButton = configureButton(lessonText);
                panel.add(lessonButton);
            }
            if(MockModel.userType ==admin) {
                JButton adminButton = configureButton(adminText);
                panel.add(adminButton);
            }
        }
        else{
            for(int i=0;i<2;i++)
                panel.add(new JLabel());
        }
        panel.add(new JLabel("    zalogwano jako: "+ mockModel.userName));
        if(MockModel.userType ==notLogged){
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
        if(MockModel.userType !=notLogged) {
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
                case logOutText:
                    if (logOutMessage()) {
                        link += "WelcomePage.html";
                        setUserType(notLogged);
                        goToPage(link);
                    }
                    break;
            }
        }
        else if(e.getActionCommand().equals(logInText)){
            LoginForm sd = new LoginForm(null, "Test test");
            sd.setVisible(true);
            String[] loginData= sd.getData();
            if(loginData!=null) {
                if (mockModel.logIn(loginData)) {
                    String link = "http://localhost:63342/Dziennik/out/production/Dziennik/Marks.html";
                    //setUserType(student);
                    goToPage(link);
                } else {
                    System.out.println("zle dane");
                }
            }
        }
    }
    private void goToPage(String link){
        link+="?_ijt=9920l7o5t4tqun41qmbt182ehk";
        try {
            URL u = new URL(link);
            appletContext.showDocument(u,"_self");
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
    }
    private void setUserType(UserType userType) {
        MockModel.userType = userType;
    }
    private boolean logOutMessage(){
        int n = JOptionPane.showConfirmDialog(
                this,
                "Jestes pewien?",
                "",
                JOptionPane.YES_NO_OPTION);
        if(n==1)
            return false;
        else
            return true;
    }
}
