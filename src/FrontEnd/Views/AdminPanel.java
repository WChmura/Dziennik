package FrontEnd.Views;

import Database.DbAccount;
import FrontEnd.Colors;
import FrontEnd.Forms.NewUserForm;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends Page implements ActionListener {
    private String [] allUsersNames;
    private JList<String> userList;
    @Override
    public void createGUI() {
        allUsersNames = model.getAccountNames();
        addTopMenu(5);
        addUsersPanel();
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

        usersPanel.add(new JLabel(""));
        usersPanel.add(listScroller);
        buttonPanel.add(newUserbutton);
        buttonPanel.add(personalDataButton);
        buttonPanel.add(deleteUserButton);
        usersPanel.add(buttonPanel);
        for(int i=0;i<3;i++)
            usersPanel.add(new JLabel(""));
        this.addSubPanel(usersPanel,1000);
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
                    new DbAccount();
                    //TODO:dopisanie dodawania do bazy
                }
                refreshUserList();
                break;
            case "deleteUser":
                System.out.println(allUsersNames[userList.getSelectedIndex()]);
                refreshUserList();
                //TODO:dopisanie wywalania z bazy
                break;
            case "personalData":
                //TODO:dopisanie otwierania danych
                break;
            default:
        }
    }
    private void refreshUserList(){
        allUsersNames = model.getAccountNames();
        userList = new JList<>(allUsersNames);
        userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        userList.setVisibleRowCount(-1);
    }
}
