package Models;

import Common.UserType;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.util.ArrayList;

public abstract class Model {
    Database.pojo.Account account;



    public Model () {

    }

    public Model(String login) {
        account = Database.dao.AccountDAO.getAccount(login);
    }

    abstract public UserType logIn(String[] loginData);

    abstract public ArrayList<String> getAllUserNames();

    abstract public ArrayList<String> getAllGroupsNames();

    abstract public ArrayList<ArrayList <Student>> getStudentsFromAllGroups();

    abstract public ArrayList<String> getNamesOfGroup(String groupName);

    abstract public ArrayList<Presence> getAttendance(String date);

    abstract public String getFormGroup();

    abstract public void addUser(String login, String hash, int permission, String mailAddress, int studentID);

    abstract public void deleteUser(String accountName);

    abstract public void addGroup(String name, int teacherID, int classroomID);

    abstract public void deleteGroup(String name);

    abstract public void changeStudentGroup(String firstName, String lastName, String groupName);

    abstract public void changePassword(String accountName, String newPassword);

}
