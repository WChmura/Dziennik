package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Group;
import Database.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /** zwraca obiekt Group z bazy na podstawie nauczyciela, moze sie przyda **/
    public static Group getGroup(String firstName, String lastName)
    {
        Group grp = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Klasa where imie = ? and nazwisko = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
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
    /** zwraca obiekt Group z bazy na podstawie nazwy, moze sie przyda **/
    public static Group getGroup(String groupName)
    {
        Group grp = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Klasa where nazwa = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, groupName);
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

    /** Zwraca liste wszystkich klas **/
    public static ArrayList<String> getAllGroups()
    {
        ArrayList<String> ListOfClasses = new ArrayList<String>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " Select nazwa from Klasa ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                ListOfClasses.add(rs.getString("nazwa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfClasses;
    }
}
