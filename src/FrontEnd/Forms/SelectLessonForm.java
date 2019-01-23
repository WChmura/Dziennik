package FrontEnd.Forms;

import FrontEnd.Form;
import Models.Model;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;


public class SelectLessonForm extends Form {
    private Model model;
    public SelectLessonForm(Frame frame, String[] classList, Model model) {
        super(frame,"Wybierz klasę i datę");
        this.model = model;
        addComboBox("Klasa",classList);
        Date date = new Date(new Timestamp(System.currentTimeMillis()).getTime());
        addTextField("Data", date.toString());
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        return model.doesLessonExist(comboBoxScores, model.getTeacherId(), input.get(0).getText());
    }
}
