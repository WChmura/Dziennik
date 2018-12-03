package FrontEnd.Views;

import Common.AttendanceValues;
import Common.MockModel;
import Common.UserType;
import Database.DbMark;
import FrontEnd.Colors;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Attendance extends Page {
    private int numOfWeeksShowed=2;
    private AttendanceValues[][] attendanceValues;
    private JButton[][] attendanceButtons;
    private int numOfLessons;
    @Override
    public void createGUI() {
        if(MockModel.getUserType()==UserType.teacher){
            addTopMenu(numOfWeeksShowed+3);
            addTeacherPanel();
        }
        else{
            addTopMenu(numOfWeeksShowed+2);
        }
        attendanceValues = model.getAttendanceValues(numOfWeeksShowed,0);
        numOfLessons=attendanceValues[0].length;
        attendanceButtons = new JButton[numOfWeeksShowed][numOfLessons];
        addLabelsPanel();
        for(int i=0;i<numOfWeeksShowed;i++){
            addWeekPanel(i);
        }
        addChangeWeekPanel();
    }
    private JButton configureAttendanceButton(AttendanceValues value,int week,int lesson){
        JButton button = new JButton("xD");
        switch (value){
            case present:
                button.setText("Ob");
                button.setBackground(Colors.present);
                break;
            case absent:
                button.setText("N");
                button.setBackground(Colors.absent);
                break;
            case absentJustified:
                button.setText("U");
                button.setBackground(Colors.absentJustified);
                break;
                default:
        }
        attendanceButtons[week][lesson]=button;
        System.out.println(button.getText());
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        if(MockModel.userType==UserType.teacher){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent ae) { editAttendance(week,lesson); }
            });
        }
        if(MockModel.userType==UserType.parent&&value==AttendanceValues.absent){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent ae) { justifyAbsention(week,lesson); }
            });
        }
        return button;
    }
    private void addTeacherPanel(){
    }
    private void addLabelsPanel(){
    }
    private void addWeekPanel(int week){
        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(1,numOfLessons,5,0));
        for(int i=0;i<numOfLessons;i++){
            JButton button;
            if(attendanceValues[week][i]!=null)
               button = configureAttendanceButton(attendanceValues[week][i],week,i);
            else
                button = new JButton(" ");
            weekPanel.add(button);
        }
        this.addSubPanel(weekPanel,50);
    }
    private void addChangeWeekPanel(){

    }
    private void editAttendance(int week, int lesson){

    }
    private void justifyAbsention(int week, int lesson){

    }
}
