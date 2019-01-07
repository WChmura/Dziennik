package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;
import Models.StudentPanel;

import javax.swing.*;
import java.awt.*;

public class PersonalData extends Page {
    // podajesz model który obsługuje ten widok
    private StudentPanel model = new StudentPanel();

    // tutaj podaj pola, które potrzebujesz do tego widoku:
    public String error;
    private String firstName;
    private String secondName;
    private String adress;
    private String sex;
    private String personal_identity_number;
    // w momencie wywołania createGUI będą one zapełnione przez któryś z moich modeli

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
        // co sprawia, że tutaj mogę ich użyć:
        this.addTopMenu(4);
        if(error==null){
            this.addSubPanel( firstNamePanel(),50);
            this.addSubPanel( secondNamePanel(),50);
            this.addSubPanel( adressPanel(),50);
            // przy okazji, jakbyś chciał wywołać funkcje np. showAttendance() - do podejrzenia z diagramu Models na draw.io
            // to robisz to tak:
            // model.showAttendance(studentID);
            // a model StudentPanel załaduje dane, wywoła funkcję createGUI widoku Attendance z zapełnionymi polami
            // i tak się to toczy
        }
        // new Text(error);
    }
    private JPanel firstNamePanel(){
        JPanel score = new JPanel();
        score.add(new JLabel(firstName));
        return score;
    }
    private JPanel secondNamePanel(){
        JPanel score = new JPanel();
        score.add(new JLabel(secondName));
        return score;
    }
    private JPanel adressPanel(){
        JPanel score = new JPanel();
        score.add(new JLabel(adress));
        return score;
    }

}
