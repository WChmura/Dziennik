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
import java.util.ArrayList;

//TODO: Stringi z data musi uzywac
public class Attendance extends Page {
    public Attendance() {
    }

    public Attendance(String value) {
        super(value);
    }

    private String studentNames;
    private int numOfLessons;
    private int[] attendanceValues;//obecności ob/niob/usp
    private ArrayList<Presence> attendancesList;
    //TODO:pobieranie dat z otrzymanych obecnosci
    private String startDate;
    private String endDate;
    private JButton attendanceButtons[];

    @Override
    public void createGUI() {
        startDate = "2019-01-07";
        endDate = "2019-01-18";
        model = createNewModel();
        if(userType==UserType.teacher||userType==UserType.admin){
            addTopMenu(5);
            addTeacherPanel();
        }
        else{
            addTopMenu(4);
        }
        attendancesList = model.getAttendance(startDate);
        numOfLessons = attendancesList.size()+10;
        attendanceButtons= new JButton[numOfLessons];
        setAttendanceValues();
        addLabelsPanel();
        //for(int i=0;i<2;i++){
            addWeekPanel(0);
        //}
        addChangeWeekPanel();
    }

    private JButton configureAttendanceButton(JButton button, int value,int week,int lesson){
        switch (value){
            case 0:
                button.setText("Ob");
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
            default:
        }
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        if(userType==UserType.teacher){
            button.addActionListener(ae -> editAttendance(week,lesson));
        }
        if(userType==UserType.parent&&value==1){
            button.addActionListener(ae -> justifyAbsention(week,lesson));
        }
        button.repaint();
        return button;
    }

    private void addTeacherPanel(){
        JPanel teacherPanel = new JPanel();
        String[] s = model.getNamesOfGroup(model.getFormGroup()).toArray(new String[0]);
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            String studentName = (String)comboBox.getSelectedItem();
            //TODO:załaduj nowe dane
            updateValues();
        });
        teacherPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(teacherPanel,30);
    }

    private void addLabelsPanel(){
        int lesson =1;
        int day =0;
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1,numOfLessons,5,0));
        for(int i=0;i<numOfLessons/2;i++){
            JLabel label;
            if(attendanceValues[i]<0){
                label = new JLabel(DaysOfWeek.dayName[day++]);
                lesson=1;
            }
            else {
                label = new JLabel(String.valueOf(lesson++));
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(label);
        }
        this.addSubPanel(labelPanel,30);
    }

    private void addWeekPanel(int week){
        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(1,numOfLessons,5,0));
        for(int i=0;i<numOfLessons/2;i++){
            JComponent component;
            if(attendanceValues[i]>=0){
                JButton button = new JButton();
                component = configureAttendanceButton(button,attendanceValues[i],week,i);
                attendanceButtons[i]=button;
            }
            else
                component = new JLabel(" ");
            weekPanel.add(component);
        }
        this.addSubPanel(weekPanel,50);
    }

    private void addChangeWeekPanel(){
        /*JPanel changeWeekPanel = new JPanel();
        String dateText = "Pokazywany okres od "+startDate.toString()+" do "+endDate.toString();
        JLabel label = new JLabel(dateText);
        changeWeekPanel.add(label,BorderLayout.CENTER);
        JButton button = new JButton("Pokaz wczesniejszy okres");
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                //TODO:załaduj nowe dane
                updateValues();
            }
        });
        changeWeekPanel.add(button,BorderLayout.EAST);
        this.addSubPanel(changeWeekPanel,50);*/
    }

    private void editAttendance(int week, int lesson){
        /*System.out.println("edit attendance");
        EditAttendanceForm edit = new EditAttendanceForm(null,attendances[lesson]);
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
            if(value>=0)
                attendanceValues[week][lesson]=value;
            //TODO: zmiana w bazie
            updateValues();
        }*/
    }

    private void justifyAbsention(int week, int lesson){
        /*int n = JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz usprawiedliwic nieobecnosc?",
                "",
                JOptionPane.YES_NO_OPTION);
        if(n==0){
            attendanceValues[week][lesson]=2;
            updateValues();
            //DbPresence.updatePresenceType(attendances[week][lesson].getPresenceId(),2);
            //TODO: zmiana w bazie
        }*/
    }

    private void updateValues(){
        /*for(int week=0;week<2;week++){
            for(int i=0;i<numOfLessons;i++){
                if(attendanceValues[week][i]>=0){
                    configureAttendanceButton(attendanceButtons[i],attendanceValues[i],week,i);
                }
            }
        }*/
    }
    private void setAttendanceValues(){
        attendanceValues = new int[numOfLessons];
        int valueIterator = 1;
        attendanceValues[0]=-3;
        for(int i=1;i<numOfLessons-10;i++){
            if(attendancesList.get(i-1).getDate().equals(attendancesList.get(1).getDate()))
                attendanceValues[valueIterator++]=attendancesList.get(i-1).getType();
            else{
                attendanceValues[valueIterator++]=-1;
                attendanceValues[valueIterator++]=attendancesList.get(i-1).getType();
            }
        }
    }
}
