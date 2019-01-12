package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Forms.ChangePasswordForm;
import FrontEnd.Page;
import FrontEnd.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends Page {
    //to potrzebuje
    //metodka na zmiane hasła (własnego) = void changePassword(String accountName, String newPassword)
    @Override
    public void createGUI() {
        addTopMenu(3);
        addSubPanel(new JPanel(),30);
        addButtonsPanel();
    }
    private void addButtonsPanel(){
        JPanel panel = new JPanel(new GridLayout(1,5,10,10));

        panel.add(new JLabel(""));
        JButton personalData = new JButton("Pokaz dane osobowe");
        personalData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: przejdz do strony z danymi
            }
        });
        panel.add(personalData);

        JButton changePassword = new JButton("Zmien haslo");
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswordForm edit = new ChangePasswordForm(null,null,false);
                edit.setVisible(true);
                String[] changes = edit.getData();
                if(changes!=null) {
                    //TODO;zmiana hasla
                }
            }
        });
        panel.add(changePassword);

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        this.addSubPanel(panel,50);
    }
}
