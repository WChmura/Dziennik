package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewMarkForm extends Form {

    public NewMarkForm(Frame frame, String studentLogin) {
        super(frame,"Nowa ocena");
        addNonEditableTextField("Login studenta:",studentLogin);
        addTextField("Ocena:",null);
        addTextField("Waga:",null);
        addTextField("Opis:",null);
        addButtons();
    }
    @Override
    protected boolean checkDataValues() {
        return isNumeric(input.get(1).getText(), 6) && isNumeric(input.get(2).getText());
    }
}
