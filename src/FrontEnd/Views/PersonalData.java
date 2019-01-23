package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;

public class PersonalData extends Page {

    private boolean canEdit;
    private String firstName;
    private String secondName;
    private String address;
    private String sex;
    private String degree;
    private String personal_identity_number;
    private String studentLogin;
    private String teacherLogin;
    private JTextField[] dataFields;

    public PersonalData() {
        super();
    }

    public PersonalData(String value)
    {
        super(value);
    }

    @Override
    public void createGUI() {
        model = createNewModel();
        if(receivedValue !=null){
            if(model.getPermission(receivedValue)<2)
                studentLogin= receivedValue;
            else if(model.getPermission(receivedValue)==2)
                teacherLogin = receivedValue;
        }
        dataFields = new JTextField[5];
        this.addTopMenu(7);
        getData();
        canEdit = userType != UserType.student;

        if((userType==UserType.teacher&&studentLogin==null)||teacherLogin!=null){
            if(userType==UserType.teacher)
                canEdit=false;
            newSubPanel( "Imie",firstName,0,true);
            newSubPanel( "Nazwisko",secondName,1,true);
            newSubPanel( "Stopien",degree,2,true);
        }

        else{
            newSubPanel( "Imie",firstName,0,false);
            newSubPanel( "Nazwisko",secondName,1,false);
            newSubPanel( "Adres", address,2,true);
            newSubPanel( "Płec",sex,3,false);
            newSubPanel( "PESEL",personal_identity_number,4,true);
        }

        if(canEdit)
            this.addSubPanel(buttonPanel());
    }

    private void newSubPanel(String title,String value,int number,boolean editable){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JTextField textField = new JTextField(value);
        dataFields[number]=textField;
        if(!canEdit||!editable)
            textField.setEditable(false);
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.RIGHT);

        score.add(label);
        score.add(textField);
        for(int i=0;i<3;i++)
            score.add(emptyLabel());
        this.addSubPanel(score);

    }

    private JPanel buttonPanel(){
        JPanel score = new JPanel(new GridLayout(1,5,10,10));
        JButton button = new JButton("Zapisz zmiany");
        button.addActionListener(e -> {
            if(teacherLogin!=null){
                model.setTeacherFirstName(teacherLogin,dataFields[0].getText());
                model.setTeacherLastName(teacherLogin,dataFields[1].getText());
                model.setTeacherDegree(teacherLogin,dataFields[2].getText());
            }else {
                if (userType == UserType.student || userType == UserType.parent)
                    studentLogin = model.getStudentLogin(firstName, secondName);
                model.setAddress(studentLogin, dataFields[2].getText());
                model.setPersonalIdentityNumber(studentLogin, dataFields[4].getText());
            }
        });

        score.add(emptyLabel());
        score.add(button);
        for(int i=0;i<3;i++)
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
        else if(studentLogin!=null) {
            firstName = model.getFirstName(studentLogin);
            secondName = model.getlastName(studentLogin);
            address = model.getAddress(studentLogin);
            sex = model.getSex(studentLogin);
            personal_identity_number = model.getPersonalIdentityNumber(studentLogin);
        }
        else if(userType==UserType.teacher) {
            firstName = model.getTeacherFirstName();
            secondName = model.getTeacherLastName();
            degree = model.getDegree();
        }
        else{
            firstName = model.getTeacherFirstName(teacherLogin);
            secondName = model.getTeacherLastName(teacherLogin);
            degree = model.getDegree(teacherLogin);
        }
    }

    private JLabel emptyLabel(){
        return new JLabel("");
    }
}
