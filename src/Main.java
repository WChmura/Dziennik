import FrontEnd.Views.Group;
import FrontEnd.Views.Settings;
import FrontEnd.Views.WelcomePage;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Settings();
            }
        });
    }
}