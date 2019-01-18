package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class DeleteLesson extends Form {
    public DeleteLesson(Frame frame) {
        super(frame,"Usul Lekcje");
        addTextField("Wpisz dzien",null);
        addTextField("Wpisz godzine",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        if(isNumeric(input.get(0).getText(),5)&&isNumeric(input.get(1).getText())){
            return true;
        }
        else{
            return false;
        }
    }
}
