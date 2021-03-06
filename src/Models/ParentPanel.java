package Models;

import Database.dao.PresenceDAO;
import Database.dao.StudentDAO;

public class ParentPanel extends  StudentPanel implements Model {

    public ParentPanel(String login) {
        super(login);

    }

    public void changeAttendance(String firstName, String lastName, java.sql.Date date, int numberOfLesson, int newValue) {
        PresenceDAO.changeAttendance(StudentDAO.getStudent(firstName, lastName), date, numberOfLesson, newValue);
    }
}
