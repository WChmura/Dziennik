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

    public TeacherPanel() { }

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
        MarkDAO.insertMark(new Mark(teacher.getSubject_id(), student.getStudentID(), teacher.getTeacherID(), date, value, weight, desc));
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
            int weekday = cal.get(Calendar.DAY_OF_WEEK);
            Presence presence = new Presence(date, TimetableDAO.getNumberOfLesson(group.getGroupID(), weekday, teacher.getTeacherID()), marks[index], student.getStudentID(), teacher.getTeacherID(), teacher.getSubject_id());
            PresenceDAO.insertPresence(presence);
            index++;
        }
    }

    public boolean doesLessonExist(int groupId, int teacherId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
        int weekday = LocalDate.parse(date, formatter).getDayOfWeek().getValue();
        int numberOfLesson = TimetableDAO.getNumberOfLesson(groupId, weekday, teacherId);
        if (numberOfLesson >= 0) return true;
        else return false;
    }
}
