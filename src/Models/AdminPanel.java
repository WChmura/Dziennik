package Models;

import Database.dao.AccountDAO;
import Database.dao.GroupDAO;
import Database.dao.StudentDAO;
import Database.pojo.Student;

import java.util.ArrayList;

public class AdminPanel extends TeacherPanel {
    public ArrayList<String> getAllUserNames() {
        return AccountDAO.getAllAccounts();
    }

    public ArrayList<String> getAllGroupsNames() {
        return GroupDAO.getAllGroups();
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
}