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
    private String address;
    private String sex;
    private String degree;
    private String personal_identity_number;
    private String studentLogin;
    //+metodki do zmiany tego
    private JTextField[] dataFields = new JTextField[5];

    public PersonalData() {
        super();
    }

    public PersonalData(String value)
    {
        super(value);
        studentLogin = value;
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
        if((userType==UserType.teacher||userType==UserType.admin)&&studentLogin==null){
            newSubPanel( "Imie",firstName,0,false);
            newSubPanel( "Nazwisko",secondName,1,false);
            newSubPanel( "Stopien",degree,2,false);
        }
        else{
            newSubPanel( "Imie",firstName,0,false);
            newSubPanel( "Nazwisko",secondName,1,false);
            newSubPanel( "Adres", address,2,true);
            newSubPanel( "Płec",sex,3,false);
            newSubPanel( "PESEL",personal_identity_number,4,true);
        }

        if(canEdit)
            this.addSubPanel(buttonPanel(),50);
    }


    private void newSubPanel(String title,String value,int number,boolean editable){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(value);
        if(!canEdit||!editable)
            textField.setEnabled(false);
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
                if(userType==UserType.teacher&&studentLogin==null){
                    //TODO:wysłanie danych nauczyciela
                }else {
                    if (userType == UserType.student || userType == UserType.parent) {
                        studentLogin = model.getStudentLogin(firstName, secondName);
                    }
                    model.setAddress(studentLogin, dataFields[2].getText());
                    model.setPersonalIdentityNumber(studentLogin, dataFields[4].getText());
                }
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
            address = model.getAddress();
            sex = model.getSex();
            personal_identity_number = model.getPersonalIdentityNumber();
        }
        else{
            if(studentLogin!=null) {
                firstName = model.getFirstName(studentLogin);
                secondName = model.getlastName(studentLogin);
                address = model.getAddress(studentLogin);
                sex = model.getSex(studentLogin);
                personal_identity_number = model.getPersonalIdentityNumber(studentLogin);
            }
            else{
                //TODO:pobieranie danych nauczyciela
                firstName=model.getFirstName();
                secondName=model.getLastName();
                degree=model.getDegreee();
            }
        }
    }
    private JLabel emptyLabel(){
        return new JLabel("");
    }

}
