package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Group;
import Database.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupDAO {

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


}
