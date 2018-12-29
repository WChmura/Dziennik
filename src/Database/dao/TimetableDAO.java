package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimetableDAO {


    public static void insertTimetable(Timetable tbl)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO \"DZIENNIK3\".\"Plan\""
                    + "(ID_LEKCJI, DZIEN, GODZINA, ID_KLASY, ID_SALI, ID_NAUCZYCIELA, ID_PRZEDMIOTU) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, tbl.getLessonID());
            preparedStatement.setInt(2, tbl.getDay());
            preparedStatement.setDouble(3, tbl.getHour());
            preparedStatement.setInt(4, tbl.getGroupID());
            preparedStatement.setInt(5, tbl.getClassroomID());
            preparedStatement.setInt(6, tbl.getTeacherID());
            preparedStatement.setInt(7, tbl.getLessonID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }
}
