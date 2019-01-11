package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Schedule extends Page {
    //to potrzebuje
    private String subjects[][];//format np "nazwaPrzedmiotu:sala", w tabeli dzien godzina
    private String groupName; //nazwa klasy
    private String allGroupsNames[]; //nazwy wszystkich klas - tylko dla adminów
    private int maxNumOfLesson; //maksymalna ilosc godzin w dniu
    //+ metodki
    // edycja - tylko dla adminów
    // taka c
    @Override
    public void createGUI() {
        maxNumOfLesson =7;
        addTopMenu(1);

    }

}
