package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class LessonForm extends Form {

    public LessonForm(Frame frame,Database.pojo.Timetable initialValues) {
        super(frame,null);
        if(initialValues==null){
            addTextField("Id_klasy:",null);
            addTextField("Id_nauczyciela:",null);
            addTextField("Id_sali:",null);
            addTextField("Id_przedmiotu:",null);
            addTextField("Dzień:",null);
            addTextField("Godzina:",null);
        }
        else{
            addTextField("Id_klasy:",String.valueOf(initialValues.getGroupID()));
            addTextField("Id_nauczyciela:",String.valueOf(initialValues.getTeacherID()));
            addTextField("Id_sali:",String.valueOf(initialValues.getClassroomID()));
            addTextField("Id_przedmiotu:",String.valueOf(initialValues.getSubjectID()));
            addTextField("Dzień:",String.valueOf(initialValues.getDay()));
            addTextField("Godzina:",String.valueOf(initialValues.getHour()));
        }
        addButtons();
    }
    @Override
    protected boolean checkDataValues() {
        //TODO:dopisac sprawdzenie czy takie cuś isnieteje
        if(
                (isNumeric(input.get(0).getText()))&&
                        (isNumeric(input.get(1).getText()))&&
                        (isNumeric(input.get(2).getText()))&&
                        (isNumeric(input.get(3).getText()))&&
                        (isNumeric(input.get(4).getText()))/*&&
                        (isNumeric(input.get(0).getText()))*/
        ){
            return true;
        }
        else{
            return false;
        }
    }
}
