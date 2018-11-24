package FrontEnd;

import Common.UserType;

import javax.swing.*;

import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import static Common.UserType.*;

public class TopPanel extends JMenuBar implements ActionListener {
    private UserType userType = teacher;
    AppletContext appletContext;
    private final String marksText = "Oceny";
    private final String scheduleText = "Plan Zajec";
    private final String attendanceText = "Obecnosci";
    private final String settingsText = "Ustawienia";
    private final String groupText = "Klasa";
    private final String lessonText = "Lekcja";
    private final String adminText = "Panel Administratora";
    TopPanel(AppletContext appletContext) {
        super();
        this.appletContext = appletContext;
        JPanel panel = new JPanel(new GridLayout(1,10));

        JButton marksButton = configureButton(marksText);
        panel.add(marksButton);

        JButton scheduleButton = configureButton(scheduleText);
        panel.add(scheduleButton);

        JButton attendanceButton = configureButton(attendanceText);
        panel.add(attendanceButton);

        JButton settingsButton = configureButton(settingsText);
        panel.add(settingsButton);

        if(userType==teacher||userType==admin) {
            JButton groupButton = configureButton(groupText);
            panel.add(groupButton);

            if(userType==teacher) {
                JButton lessonButton = configureButton(lessonText);
                panel.add(lessonButton);
            }
            if(userType==admin) {
                JButton adminButton = configureButton(adminText);
                panel.add(adminButton);
            }
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
        String command = e.getActionCommand();
        String link = "http://localhost:63342/Dziennik/out/production/Dziennik/";
        switch (command){
            case marksText:
                link+="Marks.html";
                break;
            case scheduleText:
                link+="Schedule.html";
                break;
            case settingsText:
                link+="Settings.html";
                break;
            case groupText:
                link+="Group.html";
                break;
            case adminText:
                link+="AdminPanel.html";
                break;
            case attendanceText:
                link+="Attendance.html";
                break;
            case lessonText:
                link+="Lesson.html";
                break;
        }
        link+="?_ijt=7gfbuno64p2b64c3dsfe1q9802";
        goToPage(link);
    }

    public void goToPage(String link){
        try {
            URL u = new URL(link);
            appletContext.showDocument(u,"_self");
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
    }
}
