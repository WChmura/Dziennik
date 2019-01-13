package Models;

import Common.UserType;
import Database.dao.StudentDAO;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class StudentPanel extends Model {

    /*public String[] showPersonalData(int studentID) {
        FrontEnd.Views.PersonalData view = new FrontEnd.Views.PersonalData();
        Database.dao.StudentDAO studentDAO;
        Database.pojo.Student student = studentDAO.getStudent(studentID);
        if (student!=null) {
            String info[] = {student.getFirstName(), student.getSecondName(), student.getAdress()};
        }
    }*/

    /*public void showPersonalData() {
        // TODO: complete
        if (this.account.getPermission() == "STUDENT") {
            //showPersonalData(this.account.getStudentID());
        }
    }*/

    /*public void showMarks(int studentID) {
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
    }*/

    /*public void showAttendance(int studentID) {
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
    }*/

    public void changePassword(String newPassword) {
        Database.dao.AccountDAO.updatePassword(account, newPassword);
    }

    public ArrayList<Presence> getAttendance(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
        LocalDate lastMonday = LocalDate.parse(date, formatter).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate nextMonday = lastMonday.plusWeeks(2);
        return Database.dao.PresenceDAO.getAttendance(StudentDAO.getStudent(account.getStudentID()), lastMonday.format(formatter), nextMonday.format(formatter));
    }

    @Override
    public String getFormGroup() {
        return null;
    }

    @Override
    public UserType logIn(String[] loginData) {
        return null;
    }

    @Override
    public ArrayList<String> getAllUserNames() {
        return null;
    }

    @Override
    public ArrayList<String> getAllGroupsNames() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Student>> getStudentsFromAllGroups() {
        return null;
    }

    @Override
    public ArrayList<String> getNamesOfGroup(String groupName) {
        return null;
    }

    //public void changeAttendance
}
