package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class EditAttendanceForm extends Form {
    public EditAttendanceForm(Frame frame, Database.pojo.Presence attendance ) {
        super(frame,"Edycja obecnosci");
        addComboBox("Status",new String[]{"Obecny","Nieobecny","Usprawiedliwiony"});
        addButtons();
    }
}
