package Models;

import Database.dao.*;
import Database.pojo.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static Database.dao.StudentDAO.getAllPersonalData;

public class TeacherPanel extends ParentPanel {

    public TeacherPanel(String login) {
        super(login);
    }

    public ArrayList<String> getNamesOfGroup(String groupName) {
        int groupID = GroupDAO.getGroup(groupName).getGroupID();
        ArrayList<String> list = new ArrayList<String>();
        try {
            ResultSet rs = getAllPersonalData();
            while(rs.next())
            {
                if (rs.getInt("id_klasy") == groupID) list.add(rs.getString("Imie")+" "+rs.getString("Nazwisko"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFormGroup() {
        Teacher teacher = TeacherDAO.getTeacherFromAccount(account);
        return GroupDAO.getGroup(teacher.getFirstName(), teacher.getSecondName()).getName();
    }

    public ArrayList<String> getStudentsFromLesson(String groupName) {
        return getNamesOfGroup(groupName);
    }

    public ArrayList<String> getAllGroupsNames() {
        return GroupDAO.getAllGroups();
    }

    public void addMark(String firstName, String lastName, Date date, int value, int weight, String desc) {
        Teacher teacher = TeacherDAO.getTeacherFromAccount(account);
        Student student = StudentDAO.getStudent(firstName, lastName);
        MarkDAO.insertMark(new Mark(teacher.getSubjectId(), student.getStudentID(), teacher.getTeacherID(), date, value, weight, desc));
    }

    public String getTeacherFirstName(String login) {
        return TeacherDAO.getTeacherFromAccount(AccountDAO.getAccount(login)).getFirstName();
    }

    public String getTeacherFirstName() {
        return TeacherDAO.getTeacherFromAccount(account).getFirstName();
    }

    public String getTeacherLastName() {
        return TeacherDAO.getTeacherFromAccount(account).getSecondName();
    }

    public String getDegree() {
        return TeacherDAO.getTeacherFromAccount(account).getDegree();
    }

    public String getDegree(String login) {
        return TeacherDAO.getTeacherFromAccount(AccountDAO.getAccount(login)).getDegree();
    }

    public String getTeacherLastName(String login) {
        return TeacherDAO.getTeacherFromAccount(AccountDAO.getAccount(login)).getSecondName();
    }


    public void insertPresences(Date date, String groupName, int[] marks) {
        ArrayList<String> students = getStudentsFromLesson(groupName);
        Teacher teacher = TeacherDAO.getTeacherFromAccount(account);
        Group group = GroupDAO.getGroup(groupName);
        int index = 0;
        for (String name : students) {
            String[] names = name.split(" ", 2);
            String firstName = names[0];
            String lastName = names[1];
            Student student = StudentDAO.getStudent(firstName, lastName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, -1900);
            int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
            Presence presence = new Presence(date, TimetableDAO.getNumberOfLesson(group.getGroupID(), weekday, teacher.getTeacherID()), marks[index], student.getStudentID(), teacher.getTeacherID(), teacher.getSubjectId());
            PresenceDAO.insertPresence(presence);
            index++;
        }
    }

    public boolean doesLessonExist(String groupName, int teacherId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
        int weekday = LocalDate.parse(date, formatter).getDayOfWeek().getValue();
        int groupId = GroupDAO.getGroup(groupName).getGroupID();
        int numberOfLesson = TimetableDAO.getNumberOfLesson(groupId, weekday, teacherId);
        if (numberOfLesson >= 0) return true;
        else return false;
    }

    @Override
    public int getTeacherId() {
        return TeacherDAO.getTeacherFromAccount(account).getTeacherID();
    }
}
