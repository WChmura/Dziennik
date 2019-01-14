package Models;

import Common.UserType;
import Database.dao.AccountDAO;
import Database.pojo.Mark;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

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
    public UserType logIn(String[] loginData) {
        account = AccountDAO.getAccount(loginData[0], loginData[1]);
        if (account.getPermission() == 3) {
            return UserType.admin;
        }
        else if (account.getPermission() == 2) {
            return UserType.teacher;
        }
        else if (account.getPermission() == 1) {
            return UserType.parent;
        }
        else if (account.getPermission() == 0) {
            return UserType.student;
        }
        if (account != null) System.out.println(account.getMailAddress());
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

    @Override
    public boolean doesLessonExist(int groupId, int teacherId, String date) {
        return false;
    }

    @Override
    public int getSubjectCountOfStudent(String firstName, String lastName) {
        return 0;
    }

    @Override
    public HashSet<Integer> getSubjectsOfStudent(String firstName, String lastName) {
        return null;
    }

    @Override
    public ArrayList<Mark> getMarks(String firstName, String lastName) {
        return null;
    }

    @Override
    public void updateMark(Mark mark) {

    }

    @Override
    public String getFirstName(String login) {
        return null;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getlastName(String login) {
        return null;
    }

    @Override
    public String getlastName() {
        return null;
    }

    @Override
    public String getAddress(String login) {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getSex(String login) {
        return null;
    }

    @Override
    public String getSex() {
        return null;
    }

    @Override
    public String getPersonalIdentityNumber(String login) {
        return null;
    }

    @Override
    public String getPersonalIdentityNumber() {
        return null;
    }
}
