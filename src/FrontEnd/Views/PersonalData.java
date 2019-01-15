package FrontEnd.Views;

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
    private String login;
    //+metodki do zmiany tego
    private JTextField[] dataFields = new JTextField[5];

    public PersonalData() {
        super();
    }

    public PersonalData(String value)
    {
        super(value);
        login = value;
    }

    @Override
    public void createGUI() {
        model = createNewModel();
        System.out.println("Utworzono model");
        this.addTopMenu(7);
        System.out.println("Dodano pasek");
        getData();
        if(userType!= UserType.student)
            canEdit = true;
        else
            canEdit = false;
        newSubPanel( "Imie",firstName,0,false);
        newSubPanel( "Nazwisko",secondName,1,false);
        newSubPanel( "Adres",adress,2,true);
        newSubPanel( "Płec",sex,3,false);
        newSubPanel( "PESEL",personal_identity_number,4,true);
        if(canEdit)
            this.addSubPanel(buttonPanel(),50);
    }


    private void newSubPanel(String title,String value,int number,boolean editable){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(value);
        if(!canEdit||!editable)
            textField.setEnabled(false);
        dataFields[number]=textField;
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.RIGHT);
        score.add(label);
        score.add(textField);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        this.addSubPanel(score,50);

    }

    private JPanel buttonPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JButton button = new JButton("Zapisz zmiany");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userType==UserType.student||userType==UserType.parent){
                    login = model.getStudentLogin(firstName,secondName);
                }
                model.setAddress(login,dataFields[2].getText());
                model.setPersonalIdentityNumber(login,dataFields[4].getText());
            }
        });
        score.add(emptyLabel());
        score.add(button);
        score.add(emptyLabel());
        score.add(emptyLabel());
        score.add(emptyLabel());
        return score;
    }

    private void getData(){
        if(userType==UserType.student||userType==UserType.parent){
            firstName=model.getFirstName();
            secondName=model.getlastName();
            adress = model.getAddress();
            sex = model.getSex();
            personal_identity_number = model.getPersonalIdentityNumber();
        }
        else{
            firstName = model.getFirstName(login);
            secondName = model.getlastName(login);
            adress = model.getAddress(login);
            sex = model.getSex(login);
            personal_identity_number = model.getPersonalIdentityNumber(login);
        }
    }
    private JLabel emptyLabel(){
        return new JLabel("");
    }

}
