package FrontEnd.Forms;

import Database.DbMark;
import FrontEnd.Form;
import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.*;

public class EditMarkForm extends Form {
    DbMark mark;
    public EditMarkForm(Frame frame,DbMark mark) {
        super(frame,"Edycja oceny");
        this.mark=mark;
        addTextField("Ocena:",String.valueOf(mark.getMark()));
        addTextField("Waga:",String.valueOf(mark.getWeight()));
        addTextField("Typ:",mark.getType());
        addTextField("Opis:,",mark.getDescription());
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(0).getText(),6)&&isNumeric(input.get(1).getText())){
            return true;
        }
        else{
            return wrongValuesMessage();
        }
    }
}
