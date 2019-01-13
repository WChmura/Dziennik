package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewUserForm extends Form {

    public NewUserForm(Frame frame) {
        super(frame,"Nowy Użytkownik ");
        addTextField("Login:",null);
        addTextField("Haslo:",null);
        addTextField("Mail: ",null);
        addComboBox("Typ",new String[]{"Uczen","Rodzic","Nauczyciel","Admin"});
        addTextField("Student_Id( jesli rodzic):",null);
        addButtons();
    }
}
