package FrontEnd.Forms;
import FrontEnd.Form;
import java.awt.*;

public class LoginForm extends Form {
    public LoginForm(Frame frame,String title) {
        super(frame,title);
        addTextField("login");
        addTextField("haslo");
    }
}
