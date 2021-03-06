package FrontEnd.Forms;

import FrontEnd.Form;

import java.awt.*;

public class ChangePasswordForm extends Form {
    private String oldPassword;
    public ChangePasswordForm(Frame frame,String oldPassword,boolean admin) {
        super(frame,"Zmiana hasła");
        this.oldPassword = oldPassword;
        if(admin)
            addPasswordField("Stare hasło",oldPassword);
        else
            addPasswordField("Stare hasło",null);
        addPasswordField("Nowe hasło",null);
        addPasswordField("Powtórz nowe hasło",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        return input.get(1).getText().equals(input.get(2).getText()) && input.get(0).getText().equals(oldPassword);
    }
}
