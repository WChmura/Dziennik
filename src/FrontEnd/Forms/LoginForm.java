package FrontEnd.Forms;
import FrontEnd.Form;
import java.awt.*;

public class LoginForm extends Form {
    public LoginForm(Frame frame) {
        super(frame,"Logowanie");
        addTextField("login",null);
        addPasswordField("haslo",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        return super.checkDataValues();
    }
}
