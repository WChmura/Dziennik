package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewUserForm extends Form {

    public NewUserForm(Frame frame) {
        super(frame,"Edycja obecnosci");
        addTextField("Login:",null);
        addTextField("Haslo:",null);
        addTextField("Mail: ",null);
        addComboBox("Typ",new String[]{"Uczen","Rodzic","Nauczyciel","Admin"});
        addButtons();
    }
}
