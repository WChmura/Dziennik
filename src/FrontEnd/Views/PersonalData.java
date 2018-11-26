package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Page;
import Models.StudentPanel;

import javax.swing.*;
import java.awt.*;

public class PersonalData extends Page {
    // podajesz model który obsługuje ten widok
    private StudentPanel model = new StudentPanel();

    // tutaj podaj pola, które potrzebujesz do tego widoku:
    public String error;
    public String firstName;
    public String lastName;
    public String groupName;
    // w momencie wywołania createGUI będą one zapełnione przez któryś z moich modeli

    @Override
    public void createGUI() {
        // co sprawia, że tutaj mogę ich użyć:
        addPanel();
        JButton button = new JButton("Attendance");
        if (!error) {
            // new Text(firstName);
            // new Text(lastName);
            // new Text(groupName);
            // z tym że nie znam tych funkcji więc daję jakieś durne placeholdery, ale wiesz ocb xD

            // przy okazji, jakbyś chciał wywołać funkcje np. showAttendance() - do podejrzenia z diagramu Models na draw.io
            // to robisz to tak:
            // model.showAttendance(studentID);
            // a model StudentPanel załaduje dane, wywoła funkcję createGUI widoku Attendance z zapełnionymi polami
            // i tak się to toczy
        }
        else // new Text(error);
        mainContent.add(button, BorderLayout.NORTH);
    }
}
