package Models;

import Common.UserType;
import Database.dao.MarkDAO;
import Database.dao.StudentDAO;
import Database.dao.TeacherDAO;
import Database.pojo.Mark;
import Database.pojo.Presence;
import Database.pojo.Student;
import Database.pojo.Teacher;

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

    abstract public boolean doesLessonExist(int groupId, int teacherId, String date);

    abstract public int getSubjectCountOfStudent(String firstName, String lastName);

    abstract public HashSet<Integer> getSubjectsOfStudent(String firstName, String lastName);

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

}
