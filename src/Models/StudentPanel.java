package Models;

import Database.DbStudent;
import FrontEnd.Views.PersonalData;

public class StudentPanel extends Model {
    public void showPersonalData(int studentID) {
        FrontEnd.Views.PersonalData view = new FrontEnd.Views.PersonalData();

        Database.DbStudent student = Database.DbStudent.fetch(studentID);
        if (student) {
            view.firstName = student.getFirstName();
            view.lastName = student.getSecondName();
            view.groupName = "Nazwa grupy";
        }
        else view.error = "Nie znaleziono ucznia";

        showPage(view);
    }
}
