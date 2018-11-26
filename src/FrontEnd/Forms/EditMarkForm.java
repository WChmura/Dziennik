package FrontEnd.Forms;

import Database.DbMark;
import FrontEnd.Form;

import java.awt.*;

public class EditMarkForm extends Form {
    DbMark mark;
    public EditMarkForm(Frame frame,DbMark mark) {
        super(frame,"Edycja oceny");
        this.mark=mark;
        addTextField("Ocena:",String.valueOf(mark.getMark()));
        addTextField("Waga:",String.valueOf(mark.getWeight()));
        addTextField("Typ:",String.valueOf(mark.getType()));
        addTextField("Opis:,",mark.getDescription());
        addButtons();
    }
}
