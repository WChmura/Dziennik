package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class NewMessageForm extends Form {

    public NewMessageForm(Frame frame) {
        super(frame,"Nowa wiadomosc");
        addTextField("Odbiorca:",null);
        addTextField("Tytul:",null);
        addBigTextField();
        addButtons();
    }
}
