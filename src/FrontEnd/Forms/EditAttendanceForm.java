package FrontEnd.Forms;

import Database.DbMark;
import Database.DbPresence;
import FrontEnd.Form;

import java.awt.*;

public class EditAttendanceForm extends Form {
    DbPresence attendance;
    public EditAttendanceForm(Frame frame, DbPresence attendance ) {
        super(frame,"Edycja obecnosci");
        this.attendance=attendance;
        addTextField("Status:",String.valueOf(attendance.getType()));
        addButtons();
    }
}
