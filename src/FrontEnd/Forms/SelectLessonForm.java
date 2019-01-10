package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class SelectLessonForm extends Form {

    public SelectLessonForm(Frame frame, String[] classList) {
        super(frame,"Wybierz klasę i datę");
        addComboBox("Klasa",classList);
        addButtons();
    }
}
