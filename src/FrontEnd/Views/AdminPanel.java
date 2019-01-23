package FrontEnd.Views;

import Database.pojo.Student;
import FrontEnd.Colors;
import FrontEnd.Forms.*;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel extends Page implements ActionListener {

    private String[] allUsersNames;
    private String[] classNames;
    private ArrayList<ArrayList <Student>> studentsByClasses;
    private JList<String> userList;
    private JList<String> classList;
    private JList<String> studentList;
    private int classSelected;
    @Override
    public void createGUI() {
        model = createNewModel();
        allUsersNames = model.getAllUserNames().toArray(new String[0]);
        classNames = model.getAllGroupsNames().toArray(new String[0]);
        studentsByClasses = model.getStudentsFromAllGroups();
        addTopMenu(3);
        addUsersPanel();
        addClassPanel();
        addEmptyPanel();
    }

    private void addUsersPanel(){
        JPanel usersPanel = new JPanel(new GridLayout(1,6,15,0));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1,0,5));
        buttonPanel.setBackground(Colors.main);

        JButton newUserButton = configureButton("Dodaj nowego użytkownika");
        newUserButton.setActionCommand("addNewUser");

        JButton deleteUserButton = configureButton("Usuń użytkownika");
        deleteUserButton.setActionCommand("deleteUser");

        JButton changePasswordButton = configureButton("Zmien haslo");
        changePasswordButton.setActionCommand("changePassword");

        JButton personalDataButton = new JButton("Pokaz/edytuj dane osobowe");
        personalDataButton.addActionListener(e -> {
            if(model.getPermission(userList.getSelectedValue())==3)
                JOptionPane.showMessageDialog(mainContent, "Podany uzytkownik jest adminem i nie posiada danych osobowych");
            else
                topPanel.goToPage("personalData",userList.getSelectedValue());
        });
        personalDataButton.setBorderPainted(false);
        personalDataButton.setMargin(new Insets(0,0,0,0));

        userList = new JList<>(allUsersNames);
        userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(userList);
        listScroller.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(newUserButton);
        buttonPanel.add(personalDataButton);
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(deleteUserButton);

        usersPanel.add(new JLabel("Lista użytkowników"));
        usersPanel.add(listScroller);
        usersPanel.add(buttonPanel);

        JLabel label = new JLabel("Lista uczniow w klasie");
        label.setHorizontalAlignment(JLabel.RIGHT);
        usersPanel.add(label);
        for(int i=0;i<2;i++)
            usersPanel.add(new JLabel(""));
        this.addSubPanel(usersPanel);
    }

    private void addClassPanel(){
        JPanel usersPanel = new JPanel(new GridLayout(1,4,15,0));
        JPanel buttonPanel = new JPanel(new GridLayout(3,1,0,5));
        buttonPanel.setBackground(Colors.main);

        JButton newClassButton = configureButton("Dodaj nową klasę");
        newClassButton.setActionCommand("newClass");
        buttonPanel.add(newClassButton);

        JButton deleteClassButton = configureButton("Usuń klasę");
        deleteClassButton.setActionCommand("deleteClass");
        buttonPanel.add(deleteClassButton);

        JButton personalDataButton = configureButton("Przenieś ucznia");
        personalDataButton.setActionCommand("changeClass");
        buttonPanel.add(personalDataButton);

        classList = new JList<>(classNames);
        classList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        classList.setLayoutOrientation(JList.VERTICAL);
        classList.setVisibleRowCount(5);
        classList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                classSelected = classList.getSelectedIndex();
                refreshStudentsList();
            }
        });
        JScrollPane classScroller = new JScrollPane(classList);
        classScroller.setPreferredSize(new Dimension(200, 100));

        Student[] array = studentsByClasses.get(classSelected).toArray(new Student[0]);
        String[] namesArray = new String[array.length];
        for(int i=0;i<array.length;i++)
            namesArray[i]=array[i].getFirstName()+" "+array[i].getSecondName();

        studentList = new JList<>(namesArray);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        studentList.setLayoutOrientation(JList.VERTICAL);
        studentList.setVisibleRowCount(5);
        JScrollPane studentScroller = new JScrollPane(studentList);
        studentScroller.setPreferredSize(new Dimension(200, 100));

        JLabel label = new JLabel("Lista Klas");
        label.setHorizontalAlignment(JLabel.RIGHT);
        usersPanel.add(label);
        usersPanel.add(classScroller);
        usersPanel.add(studentScroller);
        usersPanel.add(buttonPanel);
        this.addSubPanel(usersPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "addNewUser":
                addNewUser();
                break;
            case "deleteUser":
                if(confirmationPane()) {
                    model.deleteUser(userList.getSelectedValue());
                    allUsersNames = model.getAllUserNames().toArray(new String[0]);
                    refreshUserList();
                }
                break;
            case "changePassword":
                String oldPassword = model.getPassword(userList.getSelectedValue());
                ChangePasswordForm passwordForm = new ChangePasswordForm(null,oldPassword,true);
                passwordForm.setVisible(true);
                String[] changes = passwordForm.getData();
                if(changes!=null) {
                    model.changePassword(userList.getSelectedValue(),changes[1]);
                }
                break;
            case "newClass":
                NewClassForm newClass = new NewClassForm(null);
                newClass.setVisible(true);
                String[] changesInClass = newClass.getData();
                if(changesInClass!=null) {
                    model.addGroup(changesInClass[0],Integer.parseInt(changesInClass[1]),Integer.parseInt(changesInClass[2]));
                }
                refreshClassList();
                break;
            case "deleteClass":
                if(confirmationPane()) {
                    model.deleteGroup(classList.getSelectedValue());
                    refreshClassList();
                }
                break;
            case "changeClass":
                if(studentList.getSelectedValue()!=null) {
                    ChangeClassForm changeClass = new ChangeClassForm(null, classNames);
                    changeClass.setVisible(true);
                    String[] changesInStudents = changeClass.getData();
                    if (changesInStudents != null) {
                        String[] studentData = studentList.getSelectedValue().split(" ");
                        System.out.print(studentData[1]);
                        System.out.println(" do " + changesInStudents[0]);
                        model.changeStudentGroup(studentData[0], studentData[1], changesInStudents[0]);
                    }
                    studentsByClasses = model.getStudentsFromAllGroups();
                    refreshStudentsList();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nie wybrano ucznia");
                }
                break;
            default:
        }
    }

    private void refreshUserList(){
        userList.setListData(allUsersNames);
    }

    private void refreshClassList(){
        classNames = model.getAllGroupsNames().toArray(new String[0]);
        classList.setListData(classNames);
        refreshStudentsList();
        classSelected = 0;
        studentsByClasses = model.getStudentsFromAllGroups();
    }

    private void refreshStudentsList(){
        System.out.println();
        Student[] array = studentsByClasses.get(classSelected).toArray(new Student[0]);
        String[] namesArray = new String[array.length];
        for(int i=0;i<array.length;i++){
            namesArray[i]=array[i].getFirstName()+" "+array[i].getSecondName();
        }
        studentList.setListData(namesArray);
    }

    private JButton configureButton(String name){
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setMargin(new Insets(0,0,0,0));
        return button;
    }

    private void addNewUser(){
        NewUserForm newUser = new NewUserForm(null);
        newUser.setVisible(true);
        String[] changesInMark = newUser.getData();
        if(changesInMark!=null) {
            switch (changesInMark[4]){
                case "Uczen":
                    NewStudentForm newStudent = new NewStudentForm(null,classNames);
                    newStudent.setVisible(true);
                    String[] changesInStudent = newStudent.getData();
                    if(changesInStudent!=null) {
                        int id = model.addStudent(changesInStudent[0],changesInStudent[1],changesInStudent[4],changesInStudent[2],changesInStudent[3],changesInStudent[5]);
                        model.addUser(changesInMark[0],changesInMark[1],0,changesInMark[2],id);
                    }
                    break;
                case"Rodzic":
                    model.addUser(changesInMark[0],changesInMark[1],1,changesInMark[2],Integer.parseInt(changesInMark[3]));
                    break;
                case "Nauczyciel":
                    NewTeacherForm newTeacher = new NewTeacherForm(null);
                    newTeacher.setVisible(true);
                    String[] changesInTeacher = newTeacher.getData();
                    if(changesInTeacher!=null) {
                        model.addUser(changesInMark[0], changesInMark[1], 2, changesInMark[2], 2000);
                        model.addTeacher(changesInTeacher[0],changesInTeacher[1],changesInTeacher[2],changesInMark[0],Integer.parseInt(changesInTeacher[3]));
                    }
                    break;
                case"Admin":
                    model.addUser(changesInMark[0],changesInMark[1],3,changesInMark[2],2000);
                    break;
            }
        }
        refreshUserList();
    }
}
