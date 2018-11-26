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

    public void showPersonalData() {
        // TODO: complete
        if (this.account.getPermission() == "STUDENT") {
            // showPersonalData(this.account.getStudentID());
        }
    }

    public void showMarks(int studentID) {
        // TODO: create overloaded method
        FrontEnd.Views.Marks view = new FrontEnd.Views.Marks();

        Database.DbStudent student = Database.DbStudent.fetch(studentID);
        if (student) {
            view.firstName = student.getFirstName();
            view.lastName = student.getSecondName();
            view.marks = student.getMarks();
        }
        else view.error = "Nie znaleziono ucznia";

        showPage(view);
    }

    public void showAttendance(int studentID) {
        // TODO: create overloaded method
        FrontEnd.Views.Attendance view = new FrontEnd.Views.Attendance();

        Database.DbStudent student = Database.DbStudent.fetch(studentID);
        if (student) {
            view.firstName = student.getFirstName();
            view.lastName = student.getSecondName();
            view.attendance = student.getAttendance();
        }
        else view.error = "Nie znaleziono ucznia";

        showPage(view);
    }

    public void changePassword(int personID) { // TODO: cmplete method code
        FrontEnd.Views.Settings view = new FrontEnd.Views.Settings();

        Database.DbAccount account = Database.DbAccount.fetch(studentID);
        if (account) {
            view.login = account.getLogin();
            view.mail = account.getMailAddress();
            // TODO: add student name here
        }
        else view.error = "Nie znaleziono konta";

        showPage(view);
    }
}
