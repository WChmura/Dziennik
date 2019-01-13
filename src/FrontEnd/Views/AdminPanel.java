package FrontEnd.Views;

import Database.pojo.Student;
import FrontEnd.Colors;
import FrontEnd.Forms.ChangeClassForm;
import FrontEnd.Forms.ChangePasswordForm;
import FrontEnd.Forms.NewClassForm;
import FrontEnd.Forms.NewUserForm;
import FrontEnd.Page;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel extends Page implements ActionListener {

    private String[] allUsersNames;
    private String[] classNames;
    private ArrayList<ArrayList <Student>> studentsByClasses;

    /*+ jeszcze, takie mniej ważne:
     - czy dana nazwa klasy istnieje
     - czy istneieje teacher o danym id
     - czy istnieje sala o danym numerze
     */
    private JList<String> userList;
    private JList<String> classList;
    private JList<String> studentList;
    private int classSelected;
    private int studentSelected;
    @Override
    public void createGUI() {
        model = createNewModel();
        allUsersNames = model.getAllUserNames().toArray(new String[0]);
        classNames = model.getAllGroupsNames().toArray(new String[0]);
        studentsByClasses = model.getStudentsFromAllGroups();
        addTopMenu(3);
        addUsersPanel();
        addClassPanel();
        addEmptyPanel(30);
    }

    private void addUsersPanel(){
        JPanel usersPanel = new JPanel(new GridLayout(1,6,15,0));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1,0,5));
        buttonPanel.setBackground(Colors.main);
        JButton newUserbutton = new JButton("Dodaj nowego użytkownika");
        newUserbutton.addActionListener(this);
        newUserbutton.setActionCommand("addNewUser");
        newUserbutton.setBorderPainted(false);
        newUserbutton.setMargin(new Insets(0,0,0,0));

        JButton deleteUserButton = new JButton("Usuń użytkownika");
        deleteUserButton.addActionListener(this);
        deleteUserButton.setActionCommand("deleteUser");
        deleteUserButton.setBorderPainted(false);
        deleteUserButton.setMargin(new Insets(0,0,0,0));

        JButton changePasswordButton = new JButton("Zmien haslo");
        changePasswordButton.addActionListener(this);
        changePasswordButton.setActionCommand("changePassword");
        changePasswordButton.setBorderPainted(false);
        changePasswordButton.setMargin(new Insets(0,0,0,0));

        JButton personalDataButton = new JButton("Pokaz/edytuj dane osobowe");
        personalDataButton.addActionListener(this);
        personalDataButton.setActionCommand("personalData");
        personalDataButton.setBorderPainted(false);
        personalDataButton.setMargin(new Insets(0,0,0,0));

        userList = new JList<String>(allUsersNames);
        userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(userList);
        listScroller.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(newUserbutton);
        buttonPanel.add(personalDataButton);
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(deleteUserButton);
        JLabel label = new JLabel("Lista uczniow w klasie");
        label.setHorizontalAlignment(JLabel.RIGHT);

        usersPanel.add(new JLabel("Lista użytkowników"));
        usersPanel.add(listScroller);
        usersPanel.add(buttonPanel);
        usersPanel.add(label);
        for(int i=0;i<2;i++)
            usersPanel.add(new JLabel(""));
        this.addSubPanel(usersPanel,300);
    }

    private void addClassPanel(){
        JPanel usersPanel = new JPanel(new GridLayout(1,4,15,0));
        JPanel buttonPanel = new JPanel(new GridLayout(3,1,0,5));
        buttonPanel.setBackground(Colors.main);

        JButton newClassbutton = new JButton("Dodaj nową klasę");
        newClassbutton.addActionListener(this);
        newClassbutton.setActionCommand("newClass");
        newClassbutton.setBorderPainted(false);
        newClassbutton.setMargin(new Insets(0,0,0,0));
        buttonPanel.add(newClassbutton);

        JButton deleteClassButton = new JButton("Usuń klasę");
        deleteClassButton.addActionListener(this);
        deleteClassButton.setActionCommand("deleteClass");
        deleteClassButton.setBorderPainted(false);
        deleteClassButton.setMargin(new Insets(0,0,0,0));
        buttonPanel.add(deleteClassButton);

        JButton personalDataButton = new JButton("Przenieś ucznia");
        personalDataButton.addActionListener(this);
        personalDataButton.setActionCommand("changeClass");
        personalDataButton.setBorderPainted(false);
        personalDataButton.setMargin(new Insets(0,0,0,0));
        buttonPanel.add(personalDataButton);

        classList = new JList<>(classNames);
        classList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        classList.setLayoutOrientation(JList.VERTICAL);
        classList.setVisibleRowCount(5);
        classList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println("Zmiana klasy");
                    classSelected = classList.getSelectedIndex();
                    refreshStudentsList();
                }
            }
        });
        JScrollPane classScroller = new JScrollPane(classList);
        classScroller.setPreferredSize(new Dimension(200, 100));

        Student[] array = studentsByClasses.get(classSelected).toArray(new Student[0]);
        String[] namesArray = new String[array.length];
        for(int i=0;i<array.length;i++){
            namesArray[i]=array[i].getFirstName()+" "+array[i].getSecondName();
        }
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
        this.addSubPanel(usersPanel,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "addNewUser":
                NewUserForm newUser = new NewUserForm(null);
                newUser.setVisible(true);
                String[] changesInMark = newUser.getData();
                if(changesInMark!=null) {
                    int permission=0;
                    switch (changesInMark[2]){
                        case "Uczen":
                            break;
                        case"Rodzic":
                            break;
                        case "Nauczyciel":
                            break;
                        case"Admin":
                            break;
                    }
                    model.addUser(changesInMark[0],changesInMark[1],permission,changesInMark[3],Integer.parseInt(changesInMark[4]));
                    //TODO:dopisanie dodawania do bazy
                }
                refreshUserList();
                break;
            case "deleteUser":
                if(confirmationPane()) {
                    model.deleteUser(userList.getSelectedValue());
                    refreshUserList();
                }
                break;
            case "personalData":
                System.out.println("personalData");
                topPanel.goToPage("http://localhost:63342/Dziennik/out/production/Dziennik/PersonalData.html");
                break;
            case "changePassword":
                ChangePasswordForm passwordForm = new ChangePasswordForm(null,"password",true);
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
                refreshUserList();
                break;
            case "deleteClass":
                if(confirmationPane()) {
                    model.deleteGroup(classList.getSelectedValue());
                    refreshClassList();
                }
                break;
            case "changeClass":
                ChangeClassForm changeClass = new ChangeClassForm(null,classNames);
                changeClass.setVisible(true);
                String[] changesInStudents = changeClass.getData();
                if(changesInStudents!=null) {
                    String[] studentData = studentList.getSelectedValue().split(" ");
                    model.changeStudentGroup(studentData[0],studentData[1],classList.getSelectedValue());
                }
                refreshStudentsList();
                break;
            default:
        }
    }
    private void refreshUserList(){
        userList.setListData(allUsersNames);
    }

    private void refreshClassList(){
        classList.setListData(classNames);
    }

    private void refreshStudentsList(){
        Student[] array = studentsByClasses.get(classSelected).toArray(new Student[0]);
        String[] namesArray = new String[array.length];
        for(int i=0;i<array.length;i++){
            namesArray[i]=array[i].getFirstName()+" "+array[i].getSecondName();
        }
        studentList.setListData(namesArray);
    }

}
