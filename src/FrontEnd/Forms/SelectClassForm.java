package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class SelectClassForm extends Form {

    public SelectClassForm(Frame frame, String[] classList) {
        super(frame,"Zmiana klasy");
        addComboBox("Przepisz ucznia do:",classList);
        addButtons();
    }
}
