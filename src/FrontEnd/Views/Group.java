package FrontEnd.Views;

import Common.MockModel;
import Common.UserType;
import Database.DbStudent;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Group extends Page implements ActionListener {
    //to potrzebuje - tylko dla nauczycieli i adminów
    private String students[];//imiona i nazwiska studentów
    private String groupName; //nazwa klasy
    private String allGroupsNames[]; //nazwy wszystkich kls - tylko dla adminów

    //tego juz nie
    int numOfStudents;

    @Override
    public void createGUI() {
        groupName = model.getClassName();
        //students = model.getMockStudents();
        numOfStudents = 3;
        if(MockModel.getUserType()== UserType.admin){
            System.out.println("admin");
            addTopMenu(numOfStudents+3);
            addChooseGroupPanel();
        }
        else {
            addTopMenu(numOfStudents + 2);
        }
        addGroupNamePanel();
        for(int i=0;i<numOfStudents;i++){
            addStudentPanel(i);
        }
    }
    private void addChooseGroupPanel(){
        JPanel chooseGroupPanel = new JPanel();
        String[] s = model.getClassList();
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            String className = (String)comboBox.getSelectedItem();
            System.out.println(className);
            //TODO;dodac zmiane klasy
        });
        chooseGroupPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(chooseGroupPanel,30);
    }
    private void addStudentPanel(int number){
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(students[number]));
        JButton marksButton = new JButton("Pokaz oceny");
        JButton attendanceButton = new JButton("Pokaz obecnosci");
        JButton personalButton = new JButton("Pokaz dane");
        marksButton.addActionListener(this);
        attendanceButton.addActionListener(this);
        personalButton.addActionListener(this);
        marksButton.setActionCommand("marks");
        attendanceButton.setActionCommand("attendances");
        personalButton.setActionCommand("personal info");
        marksButton.setBorderPainted(false);
        marksButton.setMargin(new Insets(0,0,0,0));
        attendanceButton.setBorderPainted(false);
        attendanceButton.setMargin(new Insets(0,0,0,0));
        personalButton.setBorderPainted(false);
        personalButton.setMargin(new Insets(0,0,0,0));
        studentPanel.add(marksButton);
        studentPanel.add(attendanceButton);
        studentPanel.add(personalButton);
        for(int i=0;i<5;i++){
            studentPanel.add(new JLabel(" "));
        }
        this.addSubPanel(studentPanel,50);
    }
    private void addGroupNamePanel(){
        JPanel classPanel = new JPanel(new GridLayout(1,10));
        classPanel.add(new JLabel(" "));
        classPanel.add(new JLabel("Klasa " + groupName));
        for(int i=0;i<8;i++){
            classPanel.add(new JLabel(" "));
        }
        this.addSubPanel(classPanel,30);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String link = "http://localhost:63342/Dziennik/out/production/Dziennik/";
        switch (command) {
            case "marks":
                link += "Marks.html";
                topPanel.goToPage(link);
                break;
            case "attendances":
                link += "Attendance.html";
                topPanel.goToPage(link);
                break;
            case "personal info":
                //
                break;
            default:
        }
    }

}
