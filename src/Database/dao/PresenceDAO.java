package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Presence;
import Database.pojo.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PresenceDAO {
    public static void insertPresence(Presence pre)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO OBECNOSC"
                    + "(id_obecnosci, data, typ, id_ucznia, id_nauczyciela, id_przedmiotu) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, pre.getPresenceId());
            preparedStatement.setDate(2, pre.getDate());
            preparedStatement.setInt(3, pre.getType());
            preparedStatement.setInt(4, pre.getStudentId());
            preparedStatement.setInt(5, pre.getTeacherId());
            preparedStatement.setInt(6, pre.getSubjectId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    public static void changeAttendance(Student std, Date date)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Obecnosc set typ = 1 where id_ucznia = ? and data = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, std.getStudentID());
            preparedStatement.setDate(2, date);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
