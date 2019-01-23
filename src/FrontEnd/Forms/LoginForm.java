package FrontEnd.Forms;
import Common.UserType;
import FrontEnd.Form;
import Models.Model;

import java.awt.*;

public class LoginForm extends Form {
    Model model;
    public LoginForm(Frame frame, Model model) {
        super(frame,"Logowanie");
        this.model = model;
        addTextField("receivedValue",null);
        addPasswordField("haslo",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        String loginData[] = new String[2];
        loginData[0]=input.get(0).getText();
        loginData[1]=input.get(1).getText();
        if(model.logIn(loginData)==null){
            return false;
        }
        return true;
    }
}
