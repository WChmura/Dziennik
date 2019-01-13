package Models;

import Common.UserType;
import Database.dao.AccountDAO;
import Database.dao.MessageDAO;
import Database.dao.StudentDAO;
import Database.dao.TeacherDAO;
import Database.pojo.Account;
import Database.pojo.Message;
import Database.pojo.Presence;
import Database.pojo.Student;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class StudentPanel extends Model {
    public StudentPanel(String login) {
        super(login);
    }

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

    public ArrayList<String> getSentMessages() {
        ArrayList<Message> msgs = MessageDAO.getAllSended(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getMsg());
        }
        return  messages;
    }

    public ArrayList<String> getSentMessagesTitles() {
        ArrayList<Message> msgs = MessageDAO.getAllSended(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getTopic());
        }
        return messages;
    }

    public ArrayList<String> getSentMessagesRecipents() {
        ArrayList<Message> msgs = MessageDAO.getAllSended(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            Account recipentAcc = AccountDAO.getAccount(msg.getId_recipient());
            String recipentName = recipentAcc.getLogin();
            switch(account.getPermission()) { // 0 uczen, 1 rodzic, 2 nauczyciel, 3 admin
                case 0:
                    recipentName = StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName();
                    break;
                case 1:
                    recipentName = "Opiekun " + StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName();
                    break;
                case 2:
                    recipentName = TeacherDAO.getTeacherFromAccount(account).getFirstName() + " " + TeacherDAO.getTeacherFromAccount(account).getSecondName();
                    break;
                case 3:
                    recipentName = "Administrator";
            }
            messages.add(recipentName);
        }
        return messages;
    }

    public ArrayList<String> getReceivedMessages() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getMsg());
        }
        return  messages;
    }

    public ArrayList<String> getReceivedMessagesTitles() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getTopic());
        }
        return messages;
    }

    public ArrayList<String> getReceivedMessagesSenders() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            Account senderAcc = AccountDAO.getAccount(msg.getId_sender());
            String senderName = senderAcc.getLogin();
            switch(account.getPermission()) { // 0 uczen, 1 rodzic, 2 nauczyciel, 3 admin
                case 0:
                    senderName = account.getLogin() + " (" + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + ")";
                    break;
                case 1:
                    senderName = account.getLogin() + " (" + "opiekun " + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + ")";
                    break;
                case 2:
                    senderName = account.getLogin() + " (" + TeacherDAO.getTeacherFromAccount(account).getFirstName() + " " + TeacherDAO.getTeacherFromAccount(account).getSecondName() + ")";
                    break;
                case 3:
                    senderName = account.getLogin() + " (" + "administrator" + ")";
            }
            messages.add(senderName);
        }
        return messages;
    }

    public ArrayList<Boolean> areReceivedMessagesRead() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<Boolean> messages = new ArrayList<Boolean>();
        for (Message msg : msgs) { // 0 nieprzeczytane, 1 przeczytane
            if(msg.getReaded() == 0) messages.add(false);
            else messages.add(true);
        }
        return messages;
    }

    public void sendMessage(String messageTitle, String messageContent, String login) {
        Account receiver = AccountDAO.getAccount(login);
        Message msg = new Message(account.getPersonID(), receiver.getPersonID(), messageTitle, messageContent, 0);
        MessageDAO.insertMessage(msg);
    }

    public void markAsReaded(int numberOfMessage) {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        MessageDAO.markAsReaded(msgs.get(numberOfMessage));
    }

    @Override
    public String getFormGroup() { return null; }

    @Override
    public void addUser(String login, String hash, int permission, String mailAddress, int studentID) { }

    @Override
    public void deleteUser(String accountName) { }

    @Override
    public void addGroup(String name, int teacherID, int classroomID) { }

    @Override
    public void deleteGroup(String name) { }

    @Override
    public void changeStudentGroup(String firstName, String lastName, String groupName) {}

    @Override
    public void changePassword(String accountName, String newPassword) { }

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
