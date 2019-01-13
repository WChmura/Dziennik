package Models;

import Database.dao.GroupDAO;
import Database.dao.TeacherDAO;
import Database.dao.TimetableDAO;
import Database.pojo.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static Database.dao.StudentDAO.getAllPersonalData;

public class TeacherPanel extends ParentPanel {

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
}
