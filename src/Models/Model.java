package Models;

import Common.UserType;
import Database.dao.*;
import Database.pojo.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

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

    abstract public void addMark(String firstName, String lastName, Date date, int value, int weight, String desc);

    abstract public void insertPresences(Date date, String groupName, int[] marks);

    abstract public boolean doesLessonExist(String groupName, int teacherId, String date);

    abstract public int getSubjectCountOfStudent(String firstName, String lastName);

    abstract public ArrayList<Integer> getSubjectsOfStudent(String firstName, String lastName);

    abstract public ArrayList<Mark> getMarks(String firstName, String lastName);

    abstract public void updateMark(Mark mark);

    abstract public String getFirstName(String login);

    abstract public String getFirstName();

    abstract public String getlastName(String login);

    abstract public String getlastName();

    abstract public String getAddress(String login);

    abstract public String getAddress();

    abstract public String getSex(String login);

    abstract public String getSex();

    abstract public String getPersonalIdentityNumber(String login);

    abstract public String getPersonalIdentityNumber();

    abstract public ArrayList<String> getAllStudents();

    abstract public ArrayList<String> getSentMessages();

    abstract public ArrayList<String> getSentMessagesTitles();

    abstract public ArrayList<String> getSentMessagesRecipents();

    abstract public ArrayList<String> getReceivedMessages();

    abstract public ArrayList<String> getReceivedMessagesTitles();

    abstract public ArrayList<String> getReceivedMessagesSenders();

    abstract public ArrayList<Boolean> areReceivedMessagesRead();

    abstract public void sendMessage(String messageTitle, String messageContent, String login);

    abstract public void markAsReaded(int numberOfMessage);

    abstract public void setAddress(String login, String address);

    abstract public void setPersonalIdentityNumber(String login, String personalIdentityNumber);

    abstract public String getStudentLogin(String firstName, String lastName);

    abstract public String[][] getScheduleOfAccount();

    abstract public int getNumberOfLastGroupLesson(String groupName);

    abstract public int getNumberOfLastTeacherLesson(String login);

    abstract public void updateTimetable(int day, int hour, int classroomID, int teacherID, int groupID, int subjectID);

    abstract public boolean doesLessonExistForGroup(int day, int hour, String groupName);

    abstract public boolean doesLessonExistForTeacher(int day, int hour, String login);

    abstract public String getLastName();

    abstract public String getLastName(String login);

    abstract public String getTeacherFirstName(String login);

    abstract public String getTeacherFirstName();

    abstract public String getTeacherLastName();

    abstract public String getDegree();

    abstract public String getDegree(String login);

    abstract public String getTeacherLastName(String login);

    abstract public int getPermission(String login);

    abstract public String getPassword();

    abstract public String getPassword(String login);

    abstract public void changePassword(String newPassword);

    abstract public  String[][] getScheduleOfGroup(String groupName);

    abstract public int getTeacherId();

    abstract public String getSubjectName(int subjectId);
}
