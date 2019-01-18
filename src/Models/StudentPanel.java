package Models;

import Common.UserType;
import Database.dao.*;
import Database.pojo.*;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;

public class StudentPanel extends Model {
    public StudentPanel(String login) {
        super(login);
    }

    public StudentPanel() {

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
        System.out.println("pobrano");
        return  messages;
    }

    public ArrayList<String> getSentMessagesTitles() {
        ArrayList<Message> msgs = MessageDAO.getAllSended(account);
        System.out.println("p");
        ArrayList<String> messages = new ArrayList<String>();
        System.out.println("pp");
        for (Message msg : msgs) {
            messages.add(msg.getTopic());
        }
        System.out.println("pobrano");
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
                    recipentName = StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(recipentAcc.getStudentID()).getSecondName();
                    break;
                case 1:
                    recipentName = "Opiekun " + StudentDAO.getStudent(recipentAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(recipentAcc.getStudentID()).getSecondName();
                    break;
                case 2:
                    recipentName = TeacherDAO.getTeacherFromAccount(account).getFirstName() + " " + TeacherDAO.getTeacherFromAccount(account).getSecondName();
                    break;
                case 3:
                    recipentName = "Administrator";
            }
            messages.add(recipentName);
        }
        System.out.println("pobrano");
        return messages;
    }

    public ArrayList<String> getReceivedMessages() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getMsg());
        }
        System.out.println("pobrano");
        return  messages;
    }

    public ArrayList<String> getReceivedMessagesTitles() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<String> messages = new ArrayList<String>();
        for (Message msg : msgs) {
            messages.add(msg.getTopic());
        }
        System.out.println("pobrano");
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
                    senderName = account.getLogin() + " (" + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(senderAcc.getStudentID()).getSecondName() + ")";
                    break;
                case 1:
                    senderName = account.getLogin() + " (" + "opiekun " + StudentDAO.getStudent(senderAcc.getStudentID()).getFirstName() + " " + StudentDAO.getStudent(senderAcc.getStudentID()).getSecondName() + ")";
                    break;
                case 2:
                    senderName = account.getLogin() + " (" + TeacherDAO.getTeacherFromAccount(account).getFirstName() + " " + TeacherDAO.getTeacherFromAccount(account).getSecondName() + ")";
                    break;
                case 3:
                    senderName = account.getLogin() + " (" + "administrator" + ")";
            }
            messages.add(senderName);
        }
        System.out.println("pobrano");
        return messages;
    }

    public ArrayList<Boolean> areReceivedMessagesRead() {
        ArrayList<Message> msgs = MessageDAO.getAllMesseges(account);
        ArrayList<Boolean> messages = new ArrayList<Boolean>();
        for (Message msg : msgs) { // 0 nieprzeczytane, 1 przeczytane
            if(msg.getReaded() == 0) messages.add(false);
            else messages.add(true);
        }
        System.out.println("pobrano");
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

    public String getFirstName(String login) {
        return StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID()).getFirstName();
    }

    public String getFirstName() {
        return StudentDAO.getStudent(account.getStudentID()).getFirstName();
    }

    @Override
    public String getlastName(String login) {
        return StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID()).getSecondName();
    }

    public String getlastName() {
        return StudentDAO.getStudent(account.getStudentID()).getSecondName();
    }

    public String getAddress(String login) {
        return StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID()).getAdress();
    }

    public String getAddress() {
        return StudentDAO.getStudent(account.getStudentID()).getAdress();
    }

    public String getSex(String login) {
        return StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID()).getSex();
    }

    public String getSex() {
        return StudentDAO.getStudent(account.getStudentID()).getSex();
    }

    public String getPersonalIdentityNumber(String login) {
        return StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID()).getPersonal_identity_number();
    }

    public String getPersonalIdentityNumber() {
        return StudentDAO.getStudent(account.getStudentID()).getPersonal_identity_number();
    }

    @Override
    public ArrayList<String> getAllStudents() {
        return null;
    }

    public void setAddress(String login, String address) {
        Student student = StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID());
        StudentDAO.changeAddress(student, address);
    }

    public void setPersonalIdentityNumber(String login, String personalIdentityNumber) {
        Student student = StudentDAO.getStudent(AccountDAO.getAccount(login).getStudentID());
        StudentDAO.changePersonalIdentityNumber(student, personalIdentityNumber);
    }

    public int getSubjectCountOfStudent(String firstName, String lastName) {
        Student student = StudentDAO.getStudent(firstName, lastName);
        HashSet<Integer> marks = new HashSet<Integer>();
        for (Mark mark : MarkDAO.getMarks(student.getStudentID())) {
            marks.add(mark.getStudentID());
        }
        return marks.size();
    }

    public ArrayList<Integer> getSubjectsOfStudent(String firstName, String lastName) {
        Student student = StudentDAO.getStudent(firstName, lastName);
        HashSet<Integer> marks = new HashSet<Integer>();
        for (Mark mark : MarkDAO.getMarks(student.getStudentID())) {
            marks.add(mark.getStudentID());
        }
        ArrayList<Integer> marksList = new ArrayList<Integer>(marks);
        return marksList;
    }

    public ArrayList<Mark> getMarks(String firstName, String lastName) {
        Student student = StudentDAO.getStudent(firstName, lastName);
        System.out.println(student.getStudentID());
        return MarkDAO.getMarks(student.getStudentID());
    }

    public void updateMark(Mark mark) {
        MarkDAO.changeDescription(mark, mark.getDescription());
        MarkDAO.changeMark(mark, mark.getMark());
        MarkDAO.changeWeight(mark, mark.getWeight());
    }


    public  String[][] getScheduleOfGroup(String groupName) {
        Group grp = GroupDAO.getGroup(groupName);
        ArrayList<Timetable> timetable = TimetableDAO.getScheduleForGroup(grp);
        String[][] schedule = new String[8][5];
        for (Timetable cell : timetable) {
            Subject subject = SubjectDAO.getSubject(cell.getSubjectID());
            Teacher teacher = TeacherDAO.getTeacher(cell.getTeacherID());
            Classroom classroom = ClassroomDAO.getClassroom(cell.getClassroomID());
            String text = subject.getName() + ":" + teacher.getFirstName() + " " + teacher.getSecondName() + ":" + classroom.getName();
            schedule[cell.getDay()][cell.getHour()] = text;
        }
        return schedule;
    }

    public String[][] getScheduleOfAccount() {
        Account acc = account;
        if (acc.getPermission() < 2) {
            Student std = StudentDAO.getStudent(acc.getStudentID());
            Group grp = GroupDAO.getGroup(std.getGroupID());
            return getScheduleOfGroup(grp.getName());
        }
        else if (acc.getPermission() == 2) {
            Teacher tea = TeacherDAO.getTeacherFromAccount(acc);
            ArrayList<Timetable> timetable = TimetableDAO.getScheduleForTeacher(tea);
            String[][] schedule = new String[8][5];
            for (Timetable cell : timetable) {
                Subject subject = SubjectDAO.getSubject(cell.getSubjectID());
                Group group = GroupDAO.getGroup(cell.getGroupID());
                Classroom classroom = ClassroomDAO.getClassroom(cell.getClassroomID());
                String text = subject.getName() + ":" + group.getName() + ":" + classroom.getName();
                schedule[cell.getDay()][cell.getHour()] = text;
            }
            return schedule;
        }
        else return null;
    }

    public int getNumberOfLastGroupLesson(String groupName) {
        Group group = GroupDAO.getGroup(groupName);
        ArrayList<Timetable> timetable = TimetableDAO.getScheduleForGroup(group);
        int lastLesson = 0;
        for (Timetable cell : timetable) {
            if (cell.getHour() > lastLesson) lastLesson = cell.getHour();
        }
        return lastLesson;
    }

    public int getNumberOfLastTeacherLesson(String login) {
        Teacher teacher = TeacherDAO.getTeacherFromAccount(AccountDAO.getAccount(login));
        ArrayList<Timetable> timetable = TimetableDAO.getScheduleForTeacher(teacher);
        int lastLesson = 0;
        for (Timetable cell : timetable) {
            if (cell.getHour() > lastLesson) lastLesson = cell.getHour();
        }
        return lastLesson;
    }

    public int getPermission(String login) {
        return AccountDAO.getAccount(login).getPermission();
    }

    public String getPassword() {
        return account.getHash();
    }

    public String getPassword(String login) {
        return AccountDAO.getAccount(login).getHash();
    }

    public void updateTimetable(int day, int hour, int classroomID, int teacherID, int groupID, int subjectID) {
        TimetableDAO.updateTimetable(day, hour, classroomID, teacherID, groupID, subjectID);
    }

    public boolean doesLessonExistForGroup(int day, int hour, String groupName) {
        Group group = GroupDAO.getGroup(groupName);
        return TimetableDAO.checkGroup(day, hour, group.getGroupID());
    }

    public boolean doesLessonExistForTeacher(int day, int hour, String login) {
        Teacher teacher = TeacherDAO.getTeacherFromAccount(AccountDAO.getAccount(login));
        return TimetableDAO.checkTeacher(day, hour, teacher.getTeacherID());
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String getLastName(String login) {
        return null;
    }

    @Override
    public String getTeacherFirstName(String login) {
        return null;
    }

    @Override
    public String getTeacherFirstName() {
        return null;
    }

    @Override
    public String getTeacherLastName() {
        return null;
    }

    @Override
    public String getDegree() {
        return null;
    }

    @Override
    public String getDegree(String login) {
        return null;
    }

    @Override
    public String getTeacherLastName(String login) {
        return null;
    }

    public String getStudentLogin(String firstName, String lastName) {
        Account acc = AccountDAO.getStudentAccount(firstName, lastName);
        if (acc == null) return null;
        return AccountDAO.getStudentAccount(firstName, lastName).getLogin();
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
    public void addMark(String firstName, String lastName, Date date, int value, int weight, String desc) {

    }

    @Override
    public void insertPresences(Date date, String groupName, int[] marks) {

    }

    @Override
    public boolean doesLessonExist(int groupId, int teacherId, String date) {
        return false;
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
