package FrontEnd.Forms;

import FrontEnd.Form;
import Models.Model;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;


public class SelectLessonForm extends Form {
    Model model;
    public SelectLessonForm(Frame frame, String[] classList, Model model) {
        super(frame,"Wybierz klasę i datę");
        this.model = model;
        addComboBox("Klasa",classList);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        addTextField("Data", date.toString());
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        if(model.doesLessonExist(comboBoxScores,model.getTeacherId(),input.get(0).getText()))
            return true;
        else
            return false;
    }
}
