package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Group;
import Database.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDAO {

    /** Insert do bazy **/
    public static void insertGroup(Group gr)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Klasa"
                    + "(id_klasy, nazwa, id_sali, id_nauczyciela) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, gr.getGroupID());
            preparedStatement.setString(2, gr.getName());
            preparedStatement.setInt(3, gr.getClassroomID());
            preparedStatement.setInt(4, gr.getTeacherID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    /** Delete z bazy **/
    public static void deleteGroup(Group gr)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "Delete from KLASA WHERE Id_klasy= ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, gr.getGroupID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /** zwraca obiekt Group z bazy na podstawie id, moze sie przyda **/
    public static Group getGroup(int id)
    {
        Group grp = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Klasa where id_klasy = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int groupID;
            String name;
            int teacherID;
            int classroomID;


            while(rs.next())
            {
                groupID = rs.getInt("ID_klasy");
                name = rs.getString("nazwa");
                teacherID = rs.getInt("id_nauczyciela");
                classroomID = rs.getInt("id_sali");

                grp = new Group(name, teacherID, classroomID);
                grp.setGroupID(groupID);
                return  grp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grp;
    }

}
