package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewClassForm extends Form {

    public NewClassForm(Frame frame) {
        super(frame,"Nowa klasa");
        addTextField("Nazwa:",null);
        addTextField("Id_nauczyciela:",null);
        addTextField("Id_sali:",null);
        addButtons();
    }
    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(1).getText(),6)&&isNumeric(input.get(2).getText())){
            return true;
        }
        else{
            return wrongValuesMessage();
        }
    }
}
