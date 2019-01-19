package FrontEnd.Views;

import Common.DaysOfWeek;
import Common.UserType;
import Database.pojo.Presence;
import FrontEnd.Colors;
import FrontEnd.Forms.EditAttendanceForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Attendance extends Page {
    public Attendance() {
    }

    public Attendance(String value) {
        super(value);
    }

    private String[] studentNames;
    private int numOfLessons;
    private ArrayList<Presence> sourceList;
    private ArrayList<Presence> attendanceList;
    private String startDate;
    private String endDate;
    private JButton[] attendanceButtons;

    @Override
    public void createGUI() {
        startDate = "2019-01-07";
        endDate = "2019-01-18";
        model = createNewModel();
        if(userType==UserType.teacher||userType==UserType.admin){
            addTopMenu(5);
            addTeacherPanel();
            sourceList = model.getAttendance(startDate,studentNames[0],studentNames[1]);
        }
        else{
            addTopMenu(4);
            sourceList = model.getAttendance(startDate);
        }
        setAttendanceValues();
        attendanceButtons= new JButton[numOfLessons];
        addLabelsPanel();
        for(int i=0;i<2;i++){
            addWeekPanel(i);
         }
        addChangeWeekPanel();
    }

    private JButton configureAttendanceButton(JButton button, int value,int lesson){
        button.setEnabled(true);
        switch (value){
            case 0:
                button.setText("O");
                button.setBackground(Colors.present);
                break;
            case 1:
                button.setText("N");
                button.setBackground(Colors.absent);
                break;
            case 2:
                button.setText("U");
                button.setBackground(Colors.absentJustified);
                break;
            case -1:
                button.setText(null);
                button.setEnabled(false);
                button.setBackground(Colors.main);
                break;
            default:
        }
        if(userType==UserType.student)
            button.setEnabled(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        if(userType==UserType.teacher)
            button.addActionListener(ae -> editAttendance(lesson));
        if(userType==UserType.parent&&value==1)
            button.addActionListener(ae -> justifyAbsention(lesson));
        button.repaint();
        return button;
    }

    private void addTeacherPanel(){
        JPanel teacherPanel = new JPanel();
        String[] s;
        if(userType==UserType.teacher)
             s = model.getNamesOfGroup(model.getFormGroup()).toArray(new String[0]);
        else
            s = model.getAllStudents().toArray(new String[0]);
        final JComboBox<String> comboBox = new JComboBox<>(s);
        if(login==null)
            studentNames = s[0].split(" ");
        else {
            studentNames = login.split(" ");
            comboBox.setSelectedItem(login);
        }
        comboBox.addActionListener(e -> {
            String studentName = (String)comboBox.getSelectedItem();
            studentNames = studentName.split(" ");
            sourceList=model.getAttendance(startDate,studentNames[0],studentNames[1]);
            updateValues();
        });
        teacherPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(teacherPanel,30);
    }

    private void addLabelsPanel(){
        int day=0;
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1,45,5,0));
        for(int i=0;i<5;i++){
            JLabel label = new JLabel(DaysOfWeek.dayName[day++]);
            label.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(label);
            for(int j=0;j<8;j++){
                JLabel label2 = new JLabel(String.valueOf(j+1));
                label2.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add(label2);
            }
        }
        this.addSubPanel(labelPanel,30);
    }

    private void addWeekPanel(int week){
        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(1,numOfLessons,5,0));
        int start = 0;
        int end = numOfLessons/2;
        if(week ==1){
            start=numOfLessons/2;
            end=numOfLessons;
        }
        for(int i=start;i<end;i++){
            JButton button = new JButton();
            if(attendanceList.get(i)!=null)
                button = configureAttendanceButton(button,attendanceList.get(i).getType(),i);
            else
                button = configureAttendanceButton(button,-1,i);
            attendanceButtons[i]=button;
            weekPanel.add(button);
        }
        this.addSubPanel(weekPanel,50);
    }

    private void addChangeWeekPanel(){
        JPanel changeWeekPanel = new JPanel();
        String dateText = "Pokazywany okres od "+startDate+" do "+endDate;
        JLabel label = new JLabel(dateText);
        changeWeekPanel.add(label,BorderLayout.CENTER);
        JTextField textField = new JTextField(startDate);
        changeWeekPanel.add(textField);
        JButton button = new JButton("Pokaz od wybranej daty okres");
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                startDate = textField.getText();
                if(userType==UserType.teacher||userType==UserType.admin)
                    sourceList = model.getAttendance(startDate,studentNames[0],studentNames[1]);
                else
                    sourceList = model.getAttendance(startDate);
                updateValues();
            }
        });
        changeWeekPanel.add(button,BorderLayout.EAST);
        this.addSubPanel(changeWeekPanel,50);
    }

    private void editAttendance(int lesson){
        System.out.println("edit attendance");
        EditAttendanceForm edit = new EditAttendanceForm(null,attendanceList.get(lesson));
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            int value;
            switch(changesInMark[0]) {
                case "Obecny":
                    value = 0;
                    break;
                case "Nieobecny":
                    value = 1;
                    break;
                case "Usprawiedliwiony":
                    value = 2;
                    break;
                default:
                    value = -1;
            }
            if(value>=0){
                attendanceButtons[lesson]=configureAttendanceButton(attendanceButtons[lesson],value,lesson);
                model.changeAttendance(model.getFirstName(),model.getlastName(),attendanceList.get(lesson).getDate(),attendanceList.get(lesson).getLesson_number(),value);
            }
        }
    }

    private void justifyAbsention(int number){
        int n = JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz usprawiedliwic nieobecnosc?",
                "",
                JOptionPane.YES_NO_OPTION);
        if(n==0){
            attendanceButtons[number]=configureAttendanceButton(attendanceButtons[number],2,number);
            model.changeAttendance(model.getFirstName(),model.getlastName(),attendanceList.get(number).getDate(),attendanceList.get(number).getLesson_number(),2);
        }
    }

    private void updateValues(){
        setAttendanceValues();
        for(int i=0;i<numOfLessons;i++){
            if(attendanceList.get(i)!=null)
                attendanceButtons[i] = configureAttendanceButton(attendanceButtons[i],attendanceList.get(i).getType(),i);
            else
                attendanceButtons[i] = configureAttendanceButton(attendanceButtons[i],-1,i);
        }
    }

    private void setAttendanceValues(){
        for(int i=0;i<sourceList.size();i++){
            System.out.println(sourceList.get(i));
        }
        String[][] schedule;
        if(userType==UserType.student||userType==UserType.parent)
            schedule = model.getScheduleOfAccount();
        else
            schedule = model.getScheduleOfGroup(model.getGroupName(studentNames[0],studentNames[1]));
        attendanceList = new ArrayList<>();
        int numOfAttendances=0;
        for(int week=0;week<2;week++) {
            for (int i = 0; i < 5; i++) {
                attendanceList.add(null);
                for (int j = 0; j < 8; j++)
                    if (schedule[i][j] != null) {
                        if (sourceList.size() > numOfAttendances) {
                            System.out.println(sourceList.get(numOfAttendances));
                            attendanceList.add(sourceList.get(numOfAttendances++));
                        }
                        else
                            attendanceList.add(null);
                    }
            }
        }
        numOfLessons=attendanceList.size();
    }
}
