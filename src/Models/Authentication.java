package Models;

import Common.UserType;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.util.ArrayList;

public class Authentication extends Model {

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
}
