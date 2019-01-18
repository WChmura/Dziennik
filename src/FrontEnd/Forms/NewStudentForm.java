package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewStudentForm extends Form {

    public NewStudentForm(Frame frame) {
        super(frame,"Nowy Student ");
        addTextField("Imię",null);
        addTextField("Nazwisko:",null);
        addTextField("Płeć: ",null);
        addTextField("Pesel",null);
        addTextField("Adres",null);
        addComboBox("Typ",new String[]{"Uczen","Rodzic","Nauczyciel","Admin"});
        addButtons();
    }
}
