package FrontEnd.Forms;

import Database.DbMark;
import Database.DbPresence;
import FrontEnd.Form;

import java.awt.*;

public class NewMarkForm extends Form {

    public NewMarkForm(Frame frame, String studentId) {
        super(frame,"Edycja obecnosci");
        addTextField("Student ID:",studentId);
        addTextField("Ocena:",null);
        addTextField("Waga:",null);
        addTextField("Opis:",null);
        addButtons();
    }
    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(1).getText(),6)&&isNumeric(input.get(2).getText())){
            return true;
        }
        else{
            return wrongValuesMessage();
        }
    }
}
