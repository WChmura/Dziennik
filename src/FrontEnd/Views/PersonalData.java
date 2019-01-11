package FrontEnd.Views;

import Common.MockModel;
import Common.UserType;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalData extends Page {

    private boolean canEdit;
    //To potrzebuje
    private String firstName;
    private String secondName;
    private String adress;
    private String sex;
    private String personal_identity_number;
    //+metodki do zmiany tego


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPersonal_identity_number(String personal_identity_number) {
        this.personal_identity_number = personal_identity_number;
    }

    @Override
    public void createGUI() {
        this.addTopMenu(7);
        if(MockModel.getUserType()!= UserType.student)
            canEdit = true;
        else
            canEdit = false;
        this.addSubPanel( firstNamePanel(),50);
        this.addSubPanel( secondNamePanel(),50);
        this.addSubPanel( adressPanel(),50);
        this.addSubPanel( genderPanel(),50);
        this.addSubPanel( personalNumberPanel(),50);
        if(canEdit)
            this.addSubPanel(buttonPanel(),50);
    }

    private JPanel firstNamePanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(firstName);
        if(!canEdit)
            textField.setEnabled(false);
        JLabel label = new JLabel("Imie:");
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JPanel secondNamePanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(firstName);
        if(!canEdit)
            textField.setEnabled(false);
        JLabel label = new JLabel("Nazwisko:");
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JPanel adressPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(firstName);
        if(!canEdit)
            textField.setEnabled(false);
        JLabel label = new JLabel("Adres:");
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JPanel genderPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(firstName);
        if(!canEdit)
            textField.setEnabled(false);
        JLabel label = new JLabel("Płeć:");
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JPanel personalNumberPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(firstName);
        if(!canEdit)
            textField.setEnabled(false);
        JLabel label = new JLabel("PESEL:");
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JPanel buttonPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JButton button = new JButton("Zapisz zmiany");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: wyslij zmiany
            }
        });
        score.add(emptyLabel());
        score.add(button);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private JLabel emptyLabel(){
        return new JLabel("");
    }

}
