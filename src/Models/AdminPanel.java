package Models;

import Database.dao.*;
import Database.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Database.dao.StudentDAO.getAllPersonalData;

public class AdminPanel extends TeacherPanel {
    public AdminPanel(String login) {
        super(login);
    }

    public ArrayList<String> getAllUserNames() {
        return AccountDAO.getAllAccounts();
    }

    public void addTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId) {
        Timetable t = new Timetable(groupId, classromId, teacherId, day, hour, subjectId);
        TimetableDAO.insertTimetable(t);
    }

    public void deleteTimetable(int groupId, int day, int hour) {
        TimetableDAO.deleteTimetable(groupId, day, hour);
    }

    public void editTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId) {
        deleteTimetable(groupId, hour, day);
        addTimetable(groupId, classromId, teacherId, day, hour, subjectId);
    }

    public int addStudent(String firstName, String lastName, String address, String sex, String personalIdentityNumber, String groupName) {
        Student std = new Student(GroupDAO.getGroup(groupName).getGroupID(), firstName, lastName, address, sex, personalIdentityNumber);
        StudentDAO.insertStudent(std);
        return std.getStudentID();
    }

    public void addTeacher(String firstName, String lastName, String degree, String login, int subjectId) {
        Teacher tea = new Teacher(firstName, lastName, degree, AccountDAO.getAccount(login).getPersonID(), subjectId);
        TeacherDAO.insertTeacher(tea);
    }


    public ArrayList<ArrayList <Student>>  getStudentsFromAllGroups() {
        ArrayList<ArrayList <Student>> ListOfGroups = new ArrayList<ArrayList <Student>>();
        ArrayList<String> groups = getAllGroupsNames();
        for (String group : groups) {
            ListOfGroups.add(StudentDAO.getStudentsFromGroup(group));
        }
        return ListOfGroups;
    }

    public void addUser(String login, String hash, int permission, String mailAddress, int studentID) {
        Database.pojo.Account acc = new Database.pojo.Account(login, hash, permission, mailAddress, studentID);
        Database.dao.AccountDAO.insertAccount(acc);
    }

    public void deleteUser(String accountName) {
        System.out.println(accountName);
        Database.dao.AccountDAO.deleteAccount(Database.dao.AccountDAO.getAccount(accountName));
    }

    public void addGroup(String name, int teacherID, int classroomID) {
        Database.dao.GroupDAO.insertGroup(new Database.pojo.Group(name, teacherID, classroomID));
    }

    public void deleteGroup(String name) {
        Database.dao.GroupDAO.deleteGroup(Database.dao.GroupDAO.getGroup(name));
    }

    public void changeStudentGroup(String firstName, String lastName, String groupName) {
        Database.dao.StudentDAO.updateGroup(Database.dao.StudentDAO.getStudent(firstName, lastName), Database.dao.GroupDAO.getGroup(groupName));
    }

    public void changePassword(String accountName, String newPassword) {
        Database.dao.AccountDAO.updatePassword(Database.dao.AccountDAO.getAccount(accountName), newPassword);
    }

    public ArrayList<String> getAllStudents() {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Student> students = getAllPersonalData();
        for (Student std : students) list.add(std.getFirstName() + " " + std.getSecondName());
        return list;
    }
    public  Timetable[][] getTimetables(String groupName) {
        Group grp = GroupDAO.getGroup(groupName);
        ArrayList<Timetable> timetable = TimetableDAO.getScheduleForGroup(grp);
        Timetable[][] schedule = new Timetable[5][8];
        for (Timetable cell : timetable) {
            Subject subject = SubjectDAO.getSubject(cell.getSubjectID());
            Teacher teacher = TeacherDAO.getTeacher(cell.getTeacherID());
            Classroom classroom = ClassroomDAO.getClassroom(cell.getClassroomID());
            schedule[cell.getDay()-1][cell.getHour()-1] = cell;
        }
        return schedule;
    }
}