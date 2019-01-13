package Models;

import Database.dao.GroupDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public String getFormGroup(String firstName, String lastName) {
        return GroupDAO.getGroup(firstName, lastName).getName();
    }
}
