package Models;

import Common.UserType;
import Database.dao.AccountDAO;
import Database.pojo.Mark;
import Database.pojo.Presence;
import Database.pojo.Student;
import Database.pojo.Timetable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class Authentication extends Model {

    public Authentication() { super(); }

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

    public UserType logIn(String[] loginData) {
        account = AccountDAO.getAccount(loginData[0], loginData[1]);
        if(account!=null) {
            if (account.getPermission() == 3) {
                return UserType.admin;
            } else if (account.getPermission() == 2) {
                return UserType.teacher;
            } else if (account.getPermission() == 1) {
                return UserType.parent;
            } else if (account.getPermission() == 0) {
                return UserType.student;
            }
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

    @Override
    public boolean doesLessonExist(String groupName, int teacherId, String date) {
        return false;
    }

    @Override
    public int getSubjectCountOfStudent(String firstName, String lastName) {
        return 0;
    }

    @Override
    public ArrayList<Integer> getSubjectsOfStudent(String firstName, String lastName) {
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

    @Override
    public ArrayList<String> getAllStudents() {
        return null;
    }

    @Override
    public ArrayList<String> getSentMessages() {
        return null;
    }

    @Override
    public ArrayList<String> getSentMessagesTitles() {
        return null;
    }

    @Override
    public ArrayList<String> getSentMessagesRecipents() {
        return null;
    }

    @Override
    public ArrayList<String> getReceivedMessages() {
        return null;
    }

    @Override
    public ArrayList<String> getReceivedMessagesTitles() {
        return null;
    }

    @Override
    public ArrayList<String> getReceivedMessagesSenders() {
        return null;
    }

    @Override
    public ArrayList<Boolean> areReceivedMessagesRead() {
        return null;
    }

    @Override
    public void sendMessage(String messageTitle, String messageContent, String login) {

    }

    @Override
    public void markAsReaded(int numberOfMessage) {

    }

    @Override
    public void setAddress(String login, String address) {
        
    }

    @Override
    public void setPersonalIdentityNumber(String login, String personalIdentityNumber) {

    }

    @Override
    public String getStudentLogin(String firstName, String lastName) {
        return null;
    }

    @Override
    public String[][] getScheduleOfAccount() {
        return new String[0][];
    }

    @Override
    public int getNumberOfLastGroupLesson(String groupName) {
        return 0;
    }

    @Override
    public int getNumberOfLastTeacherLesson(String login) {
        return 0;
    }

    @Override
    public void addTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId) {

    }

    @Override
    public void deleteTimetable(int groupId, int day, int hour) {

    }

    @Override
    public void editTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId) {

    }

    @Override
    public boolean doesLessonExistForGroup(int day, int hour, int groupId) {
        return false;
    }

    @Override
    public boolean doesLessonExistForTeacher(int day, int hour, int teacherId) {
        return false;
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

    @Override
    public int getPermission(String login) {
        return 0;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getPassword(String login) {
        return null;
    }

    @Override
    public void changePassword(String newPassword) { }

    @Override
    public String[][] getScheduleOfGroup(String groupName) {
        return new String[0][];
    }

    @Override
    public int getTeacherId() {
        return 0;
    }

    @Override
    public String getSubjectName(int subjectId) {
        return null;
    }

    @Override
    public void setTeacherFirstName(String login, String firstName) { }

    @Override
    public void setTeacherLastName(String login, String lastName) { }

    @Override
    public void setTeacherDegree(String login, String degree) { }

    @Override
    public int addStudent(String firstName, String lastName, String address, String sex, String personalIdentityNumber, String groupName) {
        return 0;
    }

    @Override
    public void addTeacher(String firstName, String lastName, String degree, String login, int subjectId) {

    }

    @Override
    public void changeAttendance(String firstName, String lastName, Date date, int numberOfLesson, int newValue) {

    }

    @Override
    public ArrayList<Presence> getAttendance(String date, String firstName, String lastName) {
        return null;
    }

    @Override
    public String getGroupName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Timetable[][] getTimetables(String groupName) {
        return new Timetable[0][];
    }
}
