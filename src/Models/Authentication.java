package Models;

import Common.UserType;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.sql.Date;
import java.util.ArrayList;

public class Authentication extends Model {

    public Authentication() {}

    public Authentication(String login) {
        super(login);
    }

    private String hash(String password) {
        // TODO: implement a hashing function
        return password;
    }

    public void logOut() {
        this.account = null;
    }

    //Do zastapienia taka sprawdzajaca w bazie
    public UserType logIn(String[] loginData){
        if (loginData[0].equals("admin") && loginData[1].equals("admin")){
            return UserType.admin;
        }else if(loginData[0].equals("tw") && loginData[1].equals("tw")){
            return UserType.teacher;
        }
        else if(loginData[0].equals("s") && loginData[1].equals("s")){
            return UserType.student;
        }
        else if(loginData[0].equals("r") && loginData[1].equals("r")){
            return UserType.parent;
        }
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

    @Override
    public ArrayList<Presence> getAttendance(String date) {
        return null;
    }

    @Override
    public String getFormGroup() {
        return null;
    }

    @Override
    public void addUser(String login, String hash, int permission, String mailAddress, int studentID) {
    }

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
}
