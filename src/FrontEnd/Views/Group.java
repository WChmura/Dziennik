package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;

public class Group extends Page{
    private String[] students;
    private String groupName;
    private String[] allGroupsNames;
    private int numOfStudents;

    @Override
    public void createGUI() {
        model = createNewModel();
        if(userType==UserType.teacher)
            groupName = model.getFormGroup();
        else{
            allGroupsNames = model.getAllGroupsNames().toArray(new String[0]);
            groupName = allGroupsNames[0];
        }
        students = model.getNamesOfGroup(groupName).toArray(new String[0]);
        numOfStudents = students.length;
        addTopMenu(numOfStudents + 2);
        if(userType== UserType.admin)
            addChooseGroupPanel();
        else
            this.addSubPanel(GroupNamePanel());
        for(int i=0;i<numOfStudents;i++)
            addSubPanel(StudentPanel(i));
    }

    private void addChooseGroupPanel(){
        JPanel chooseGroupPanel = new JPanel();
        final JComboBox<String> comboBox = new JComboBox<>(allGroupsNames);
        comboBox.addActionListener(e -> {
            groupName = (String)comboBox.getSelectedItem();
            for (int i = numOfStudents;i>0;i--)
                this.deletePanel(i);
            students = model.getNamesOfGroup(groupName).toArray(new String[0]);
            numOfStudents = students.length;
            for(int i =0;i<numOfStudents;i++)
                this.addSubPanel(StudentPanel(i),i+1);
            this.mainContent.repaint();
            this.setSize(this.getWidth()+1,this.getHeight());
        });
        chooseGroupPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(chooseGroupPanel);
    }

    private JPanel StudentPanel(int number){
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(students[number]));

        JButton marksButton = configureButton("Pokaz oceny");
        marksButton.addActionListener(e -> topPanel.goToPage("Oceny",students[number]));

        JButton attendanceButton = configureButton("Pokaz obecnosci");
        attendanceButton.addActionListener(e -> topPanel.goToPage("Obecnosci",students[number]));

        JButton personalButton = configureButton("Pokaz dane");
        personalButton.addActionListener(e -> {
            String[] studentData = students[number].split(" ");
            String login = model.getStudentLogin(studentData[0],studentData[1]);
            topPanel.goToPage("personalData",login);
        });

        studentPanel.add(marksButton);
        studentPanel.add(attendanceButton);
        studentPanel.add(personalButton);
        for(int i=0;i<5;i++)
            studentPanel.add(new JLabel(" "));
        return studentPanel;
    }
    private JButton configureButton(String title){
        JButton button = new JButton(title);
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        return button;
    }

    private JPanel GroupNamePanel(){
        JPanel classPanel = new JPanel(new GridLayout(1,10));
        classPanel.add(new JLabel(" "));
        classPanel.add(new JLabel("Klasa " + groupName));
        for(int i=0;i<8;i++)
            classPanel.add(new JLabel(" "));
        return classPanel;

    }
}
