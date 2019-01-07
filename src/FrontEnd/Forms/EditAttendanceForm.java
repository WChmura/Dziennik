package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class EditAttendanceForm extends Form {
    Database.pojo.Presence attendance;
    public EditAttendanceForm(Frame frame, Database.pojo.Presence attendance ) {
        //TODO:przerobić na comboxa
        super(frame,"Edycja obecnosci");
        this.attendance=attendance;
        addTextField("Status:",String.valueOf(attendance.getType()));
        addButtons();
    }
}
