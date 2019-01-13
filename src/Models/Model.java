package Models;

import Common.UserType;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.util.ArrayList;

public abstract class Model {
    Database.pojo.Account account;

    public void Model () {

    }

    public void Model(String login) {
        account = Database.dao.AccountDAO.getAccount(login);
    }

    abstract public UserType logIn(String[] loginData);

    abstract public ArrayList<String> getAllUserNames();

    abstract public ArrayList<String> getAllGroupsNames();

    abstract public ArrayList<ArrayList <Student>> getStudentsFromAllGroups();

    abstract public ArrayList<String> getNamesOfGroup(String groupName);

    abstract public ArrayList<Presence> getAttendance(String date);

    abstract public String getFormGroup();

}
