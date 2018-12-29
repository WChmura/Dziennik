package Database.dao;

import Database.C3poDataSource;
import Database.pojo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MarkDAO {

    public static void insertMark(Mark mark)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO OCENA"
                    + "(ID_OCENY, DATA, OCENA, WAGA, OPIS, ID_UCZNIA, ID_NAUCZYCIELA, ID_PRZEDMIOTU) VALUES"
                    + "(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, mark.getMarkID());
            preparedStatement.setDate(2, mark.getDate());
            preparedStatement.setInt(3, mark.getMark());
            preparedStatement.setInt(4, mark.getWeight());
            preparedStatement.setString(5, mark.getDescription());
            preparedStatement.setInt(6, mark.getStudentID());
            preparedStatement.setInt(7, mark.getTeacherID());
            preparedStatement.setInt(8, mark.getSubjectID());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


    /** Edycja oceny w bazie **/
    public static void changeMark(Mark mar, int mark) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set ocena = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, mark);
            preparedStatement.setInt(2, mar.getMarkID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeWeight(Mark mar, int weight) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set waga = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, weight);
            preparedStatement.setInt(2, mar.getMarkID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeDescription(Mark mar, String description) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set opis = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, mar.getMarkID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
