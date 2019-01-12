package Models;

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

    public void changePassword(String accountName, String newPassword) {
        Database.dao.AccountDAO.updatePassword(Database.dao.AccountDAO.getAccount(accountName), newPassword);
    }
}
