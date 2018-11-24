package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends Page {

    @Override
    public void createGUI() {
        //super.changeUserType(UserType.notLogged);
        addPanel();
        mainContent.add(new JLabel("Zaloguj sie aby otrzymac dostep do serwisu"),BorderLayout.NORTH);
    }
}
