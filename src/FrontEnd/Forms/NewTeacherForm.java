package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewTeacherForm extends Form {

    public NewTeacherForm(Frame frame) {
        super(frame,"Nowy Nauczyciel ");
        addTextField("Imię",null);
        addTextField("Nazwisko:",null);
        addTextField("Wyksztalcenie: ",null);
        addTextField("Id_przedmiotu",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(3).getText()))
            return true;
        else
            return false;
    }
}
