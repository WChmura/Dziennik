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
    private static String MARKS_COMMAND = "marks";
    private static String SCHELUDE_COMMAND = "schelude";
    private static String ATTENDANCE_COMMAND = "attendance";
    private static String SETTINGS_COMMAND = "settings";
    private static String GROUP_COMMAND = "group";
    private static UserType userType = student;
    private static AppletContext appletContext;
    JPanel panel;
    public TopPanel(AppletContext appletContext) {
        super();

        this.appletContext = appletContext;

        JButton marksButton = new JButton("Marks");
        marksButton.setActionCommand(MARKS_COMMAND);
        marksButton.addActionListener(this);

        JButton scheludeButton = new JButton("Schelude");
        scheludeButton.setActionCommand(SCHELUDE_COMMAND);
        scheludeButton.addActionListener(this);

        JButton attendanceButton = new JButton("Attendance");
        attendanceButton.setActionCommand(ATTENDANCE_COMMAND);
        attendanceButton.addActionListener(this);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setActionCommand(SETTINGS_COMMAND);
        settingsButton.addActionListener(this);

        JButton groupButton = new JButton("Group");
        groupButton.setActionCommand(GROUP_COMMAND);
        groupButton.addActionListener(this);

        panel = new JPanel(new GridLayout(1,5));
        panel.setBackground(Color.cyan);
        panel.add(marksButton);
        panel.add(scheludeButton);
        panel.add(attendanceButton);
        panel.add(settingsButton);
        panel.add(groupButton);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String link = "http://localhost:63342/Dziennik/out/production/Dziennik/";
        switch(command){
            case "marks":
                link+="Marks.html";
                break;
            case "group":
                link+="Group.html";
                break;
            case "attendance":
                link+="Attendance.html";
                break;
            case "schelude":
                link+="Schelude.html";
                break;
            case "settings":
                link+="Settings.html";
                break;
        }
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
