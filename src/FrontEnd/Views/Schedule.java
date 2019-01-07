package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Schedule extends Page {
    //to potrzebuje
    private String subjects[][];//format np "nazwaPrzedmiotu:sala", w tabeli dzien godzina
    private String hours[];// godziny rozpoczecia zajec
    private String groupName; //nazwa klasy
    private String allGroupsNames[]; //nazwy wszystkich klas - tylko dla adminów
    //+ metodki
    // edycja - tylko dla adminów
    // taka c
    @Override
    public void createGUI() {
        addTopMenu(1);
        JButton button = new JButton("Schedule");
        mainContent.add(button, BorderLayout.NORTH);
    }
}
