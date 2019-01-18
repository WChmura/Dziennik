package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class EditMarkForm extends Form {
    Database.pojo.Mark mark;
    public EditMarkForm(Frame frame,Database.pojo.Mark mark) {
        super(frame,"Edycja oceny");
        this.mark=mark;
        addTextField("Ocena:",String.valueOf(mark.getMark()));
        addTextField("Waga:",String.valueOf(mark.getWeight()));
        addTextField("Opis:,",mark.getDescription());
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(0).getText(),7)&&isNumeric(input.get(1).getText())){
            return true;
        }
        else{
            return false;
        }
    }
}
