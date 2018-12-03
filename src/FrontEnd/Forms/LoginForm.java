package FrontEnd.Forms;
import FrontEnd.Form;
import java.awt.*;

public class LoginForm extends Form {
    public LoginForm(Frame frame,String title) {
        super(frame,title);
        addTextField("login",null);
        addTextField("haslo",null);
        addButtons();
    }

    @Override
    protected boolean checkDataValues() {
        return super.checkDataValues();
    }
}
