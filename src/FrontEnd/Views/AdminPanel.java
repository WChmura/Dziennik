package FrontEnd.Views;

import FrontEnd.Colors;
import FrontEnd.Forms.ChangeClassForm;
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
    //to potrzebuje - wszystko tylko dla Admina
    private String [] allUsersNames;//loginy wszystkich użytkowników
    private String [] classNames;//nazwy wszystkich klas
    private String[][] studentsByClasses;//uczniowie według klas
    /*metody do:
    - dodawania user do bazy danych
    - zmieniania hasła
    - usuwania user z bazy
    - dodawnia klasy
    - usuwania klasy
    - przenoszenia ucznia do innej klasy
     */

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
        allUsersNames = model.getAccountNames();
        classNames = model.getClassList();
        studentsByClasses = model.getStudentsByClasses();
        addTopMenu(3);
        addUsersPanel();
        addClassPanel();
        addEmptyPanel(30);
    }

    private void addUsersPanel(){
        JPanel usersPanel = new JPanel(new GridLayout(1,6,15,0));
        JPanel buttonPanel = new JPanel(new GridLayout(3,1,0,5));
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

        JButton personalDataButton = new JButton("Pokaz/edytuj dane osobowe");
        personalDataButton.addActionListener(this);
        personalDataButton.setActionCommand("personalData");
        personalDataButton.setBorderPainted(false);
        personalDataButton.setMargin(new Insets(0,0,0,0));

        userList = new JList<>(allUsersNames);
        userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(userList);
        listScroller.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(newUserbutton);
        buttonPanel.add(personalDataButton);
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

        studentList = new JList<>(studentsByClasses[classSelected]);
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
                    //TODO:dopisanie dodawania do bazy
                }
                refreshUserList();
                break;
            case "deleteUser":
                if(confirmationPane()) {
                    //TODO:dopisanie wywalania z bazy
                    refreshUserList();
                }
                break;
            case "personalData":
                System.out.println("personalData");
                //TODO:dopisanie otwierania danych<-jak bedzie server
                break;
            case "newClass":
                NewClassForm newClass = new NewClassForm(null);
                newClass.setVisible(true);
                String[] changesInClass = newClass.getData();
                if(changesInClass!=null) {
                    //TODO:dopisanie dodawania do bazy
                }
                refreshUserList();
                break;
            case "deleteClass":
                if(confirmationPane()) {
                    //TODO:usuwanie klasy z bazy
                    refreshClassList();
                }
                break;
            case "changeClass":
                ChangeClassForm changeClass = new ChangeClassForm(null,classNames);
                changeClass.setVisible(true);
                String[] changesInStudents = changeClass.getData();
                if(changesInStudents!=null) {
                    System.out.println(studentList.getSelectedIndex());
                    //TODO: zmiana w bazie
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
        studentList.setListData(studentsByClasses[classSelected]);
    }

}
