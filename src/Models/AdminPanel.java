package Models;

import Database.dao.AccountDAO;
import Database.dao.GroupDAO;
import Database.dao.StudentDAO;
import Database.dao.TeacherDAO;
import Database.pojo.Student;
import Database.pojo.Teacher;

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

    public ArrayList<ArrayList <Student>>  getStudentsFromAllGroups() {
        ArrayList<ArrayList <Student>> ListOfGroups = new ArrayList<ArrayList <Student>>();
        ArrayList<String> groups = getAllGroupsNames();
        for (String group : groups) {
            ListOfGroups.add(StudentDAO.getStudentsFromGroup(group));
        }
        return ListOfGroups;
    }

    public void addUser(String login, String hash, int permission, String mailAddress, int studentID) {
        System.out.println(login);
        System.out.println(hash);
        System.out.println(permission);
        System.out.println(mailAddress);
        System.out.println(studentID);
        Database.pojo.Account acc = new Database.pojo.Account(login, hash, permission, mailAddress, studentID);
        Database.dao.AccountDAO.insertAccount(acc);
    }

    public void addStudent(String firstName, String lastName, String address, String sex, String personalIdentityNumber, String groupName) {
        Student std = new Student(GroupDAO.getGroup(groupName).getGroupID(), firstName, lastName, address, sex, personalIdentityNumber);
        StudentDAO.insertStudent(std);
    }

    public void addTeacher(String firstName, String lastName, String degree, String login, int subjectId) {
        Teacher tea = new Teacher(firstName, lastName, degree, AccountDAO.getAccount(login).getPersonID(), subjectId);
        TeacherDAO.insertTeacher(tea);
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
        try {
            ResultSet rs = getAllPersonalData();
            while(rs.next())
            {
                list.add(rs.getString("Imie")+" "+rs.getString("Nazwisko"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}