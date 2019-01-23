package FrontEnd.Forms;

import FrontEnd.Form;
import Models.Model;
import java.awt.*;

public class LoginForm extends Form {
    private Model model;
    public LoginForm(Frame frame, Model model) {
        super(frame,"Logowanie");
        this.model = model;
        addTextField("receivedValue",null);
        addPasswordField("haslo",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        String[] loginData = new String[2];
        loginData[0]=input.get(0).getText();
        loginData[1]=input.get(1).getText();
        return model.logIn(loginData) != null;
    }
}
