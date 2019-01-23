package FrontEnd.Views;

import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;

public class WelcomePage extends Page {

    @Override
    public void createGUI() {
        model = createNewModel();
        addTopMenu(1);
        mainContent.add(new JLabel("Zaloguj sie aby otrzymac dostep do serwisu"),BorderLayout.NORTH);
    }
}
