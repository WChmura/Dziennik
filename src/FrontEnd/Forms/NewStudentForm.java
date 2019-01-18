package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewStudentForm extends Form {

    public NewStudentForm(Frame frame, String[] classList) {
        super(frame,"Nowy Student ");
        addTextField("Imię",null);
        addTextField("Nazwisko:",null);
        addTextField("Płeć: ",null);
        addTextField("Pesel",null);
        addTextField("Adres",null);
        addComboBox("Klasa",classList);
        addButtons();
    }

}
