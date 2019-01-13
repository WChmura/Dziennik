package FrontEnd.Views;

import Common.UserType;
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
        model = createNewModel();
        System.out.println("start pobierania");
        groupName = model.getFormGroup();
        students = model.getNamesOfGroup(groupName).toArray(new String[0]);
        numOfStudents = students.length;
        System.out.println("koniec pobierania");
        if(userType== UserType.admin){
            System.out.println("admin");
            addTopMenu(numOfStudents+3);
            addChooseGroupPanel();
        }
        else {
            addTopMenu(numOfStudents + 2);
        }
        this.addSubPanel(GroupNamePanel(),30);
        for(int i=0;i<numOfStudents;i++){
            addSubPanel(StudentPanel(i),50);
        }
    }
    private void addChooseGroupPanel(){
        JPanel chooseGroupPanel = new JPanel();
        String[] s = model.getAllGroupsNames().toArray(new String[0]);
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            String className = (String)comboBox.getSelectedItem();
            System.out.println(className);
            for (int i = numOfStudents+1;i>0;i--){
                this.deletePanel(i);
            }
            students = model.getNamesOfGroup(groupName).toArray(new String[0]);;//TODO:pobranie nowych danych
            groupName = className;
            this.addSubPanel(GroupNamePanel(),30,1);
            for(int i =0;i<numOfStudents;i++){
                this.addSubPanel(StudentPanel(i),50,i+2);
            }
            this.mainContent.repaint();
        });
        chooseGroupPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(chooseGroupPanel,30);
    }
    private JPanel StudentPanel(int number){
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(students[number]));

        JButton marksButton = new JButton("Pokaz oceny");
        marksButton.addActionListener(this);
        marksButton.setActionCommand("marks");
        marksButton.setBorderPainted(false);
        marksButton.setMargin(new Insets(0,0,0,0));

        JButton attendanceButton = new JButton("Pokaz obecnosci");
        attendanceButton.addActionListener(this);
        attendanceButton.setActionCommand("attendances");
        attendanceButton.setBorderPainted(false);
        attendanceButton.setMargin(new Insets(0,0,0,0));

        JButton personalButton = new JButton("Pokaz dane");
        personalButton.addActionListener(this);
        personalButton.setActionCommand("personal info");
        personalButton.setBorderPainted(false);
        personalButton.setMargin(new Insets(0,0,0,0));

        studentPanel.add(marksButton);
        studentPanel.add(attendanceButton);
        studentPanel.add(personalButton);
        for(int i=0;i<5;i++){
            studentPanel.add(new JLabel(" "));
        }
        return studentPanel;
    }
    private JPanel GroupNamePanel(){
        JPanel classPanel = new JPanel(new GridLayout(1,10));
        classPanel.add(new JLabel(" "));
        classPanel.add(new JLabel("Klasa " + groupName));
        for(int i=0;i<8;i++){
            classPanel.add(new JLabel(" "));
        }
        return classPanel;

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
                link += "PersonalData.html";
                topPanel.goToPage(link);
                break;
            default:
        }
    }

}
