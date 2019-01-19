package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Group extends Page{
    private String[] students;
    private String groupName;
    private String[] allGroupsNames;
    private int numOfStudents;

    @Override
    public void createGUI() {
        model = createNewModel();
        System.out.println("start pobierania");
        if(userType==UserType.teacher)
            groupName = model.getFormGroup();
        else if(userType==UserType.admin) {
            allGroupsNames = model.getAllGroupsNames().toArray(new String[0]);
            groupName = allGroupsNames[0];
        }
        if(groupName==null)
            this.dispose();
        students = model.getNamesOfGroup(groupName).toArray(new String[0]);
        numOfStudents = students.length;
        for(int i=0;i<numOfStudents;i++){
            System.out.println("Pobrano ucznia:" +students[i]);
        }
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
            System.out.println("Dodawanie panelu");
            addSubPanel(StudentPanel(i),50);
        }
    }
    private void addChooseGroupPanel(){
        JPanel chooseGroupPanel = new JPanel();

        final JComboBox<String> comboBox = new JComboBox<>(allGroupsNames);
        comboBox.addActionListener(e -> {
            String className = (String)comboBox.getSelectedItem();
            System.out.println(className);
            for (int i = numOfStudents+1;i>0;i--){
                this.deletePanel(i);
            }
            groupName = className;
            students = model.getNamesOfGroup(groupName).toArray(new String[0]);
            numOfStudents = students.length;
            this.addSubPanel(GroupNamePanel(),30,1);
            for(int i =0;i<numOfStudents;i++){
                this.addSubPanel(StudentPanel(i),50,i+2);
            }
            this.mainContent.repaint();
            this.setSize(this.getWidth()+1,this.getHeight());
        });
        chooseGroupPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(chooseGroupPanel,30);
    }
    private JPanel StudentPanel(int number){
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(students[number]));

        JButton marksButton = new JButton("Pokaz oceny");
        marksButton.setMargin(new Insets(0,0,0,0));
        marksButton.addActionListener(e -> topPanel.goToPage("Oceny",students[number]));
        marksButton.setActionCommand("marks");
        marksButton.setBorderPainted(false);
        marksButton.setMargin(new Insets(0,0,0,0));

        JButton attendanceButton = new JButton("Pokaz obecnosci");
        attendanceButton.setMargin(new Insets(0,0,0,0));
        attendanceButton.addActionListener(e -> topPanel.goToPage("Obecnosci",students[number]));
        attendanceButton.setActionCommand("attendances");
        attendanceButton.setBorderPainted(false);
        attendanceButton.setMargin(new Insets(0,0,0,0));

        JButton personalButton = new JButton("Pokaz dane");
        personalButton.setMargin(new Insets(0,0,0,0));
        personalButton.addActionListener(e -> {
            String[] studentData = students[number].split(" ");
            String login = model.getStudentLogin(studentData[0],studentData[1]);
            topPanel.goToPage("personalData",login);
        });
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
}
