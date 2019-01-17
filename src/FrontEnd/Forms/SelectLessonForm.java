package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;


public class SelectLessonForm extends Form {

    public SelectLessonForm(Frame frame, String[] classList) {
        super(frame,"Wybierz klasę i datę");
        addComboBox("Klasa",classList);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        addTextField("Data", date.toString());
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        return true;
    }
}
