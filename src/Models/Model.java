package Models;

import Common.UserType;
import Database.pojo.Mark;
import Database.pojo.Presence;
import Database.pojo.Student;
import Database.pojo.Timetable;

import java.sql.Date;
import java.util.ArrayList;

public interface Model {
    UserType logIn(String[] loginData);

    ArrayList<String> getAllUserNames();

    ArrayList<String> getAllGroupsNames();

    ArrayList<ArrayList <Student>> getStudentsFromAllGroups();

    ArrayList<String> getNamesOfGroup(String groupName);

    ArrayList<Presence> getAttendance(String date);

    String getFormGroup();

    void addUser(String login, String hash, int permission, String mailAddress, int studentID);

    void deleteUser(String accountName);

    void addGroup(String name, int teacherID, int classroomID);

    void deleteGroup(String name);

    void changeStudentGroup(String firstName, String lastName, String groupName);

    void changePassword(String accountName, String newPassword);

    void addMark(String firstName, String lastName, Date date, int value, int weight, String desc);

    void insertPresences(Date date, String groupName, int[] marks);

    boolean doesLessonExist(String groupName, int teacherId, String date);

    int getSubjectCountOfStudent(String firstName, String lastName);

    ArrayList<Integer> getSubjectsOfStudent(String firstName, String lastName);

    ArrayList<Mark> getMarks(String firstName, String lastName);

    void updateMark(Mark mark);

    String getFirstName(String login);

    String getFirstName();

    String getlastName(String login);

    String getlastName();

    String getAddress(String login);

    String getAddress();

    String getSex(String login);

    String getSex();

    String getPersonalIdentityNumber(String login);

    String getPersonalIdentityNumber();

    ArrayList<String> getAllStudents();

    ArrayList<String> getSentMessages();

    ArrayList<String> getSentMessagesTitles();

    ArrayList<String> getSentMessagesRecipents();

    ArrayList<String> getReceivedMessages();

    ArrayList<String> getReceivedMessagesTitles();

    ArrayList<String> getReceivedMessagesSenders();

    ArrayList<Boolean> areReceivedMessagesRead();

    void sendMessage(String messageTitle, String messageContent, String login);

    void markAsReaded(int numberOfMessage);

    void setAddress(String login, String address);

    void setPersonalIdentityNumber(String login, String personalIdentityNumber);

    String getStudentLogin(String firstName, String lastName);

    String[][] getScheduleOfAccount();

    int getNumberOfLastGroupLesson(String groupName);

    int getNumberOfLastTeacherLesson(String login);

    void addTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId);

    void deleteTimetable(int groupId, int day, int hour);

    void editTimetable(int groupId, int classromId, int teacherId, int day, int hour, int subjectId);

    boolean doesLessonExistForGroup(int day, int hour, int groupId);

    boolean doesLessonExistForTeacher(int day, int hour, int teacherId);

    String getLastName();

    String getLastName(String login);

    String getTeacherFirstName(String login);

    String getTeacherFirstName();

    String getTeacherLastName();

    String getDegree();

    String getDegree(String login);

    String getTeacherLastName(String login);

    int getPermission(String login);

    String getPassword();

    String getPassword(String login);

    void changePassword(String newPassword);

    String[][] getScheduleOfGroup(String groupName);

    int getTeacherId();

    String getSubjectName(int subjectId);

    void setTeacherFirstName(String login, String firstName);

    void setTeacherLastName(String login, String lastName);

    void setTeacherDegree(String login, String degree);

    int addStudent(String firstName, String lastName, String address, String sex, String personalIdentityNumber, String groupName);

    void addTeacher(String firstName, String lastName, String degree, String login, int subjectId);

    void changeAttendance(String firstName, String lastName, Date date, int numberOfLesson, int newValue);

    ArrayList<Presence> getAttendance(String date, String firstName, String lastName);

    String getGroupName(String firstName, String lastName);

    Timetable[][] getTimetables(String groupName);

    String[] getStartAndEndDate(String date);
}
