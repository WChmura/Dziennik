package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class ChangeClassForm extends Form {

    public ChangeClassForm(Frame frame, String[] classList) {
        super(frame,"Zmiana klasy");
        addComboBox("Przepisz ucznia do:",classList);
        addButtons();
    }
}
