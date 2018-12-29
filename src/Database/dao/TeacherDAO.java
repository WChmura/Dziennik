package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDAO {

    public static void insertTeacher(Teacher Tea)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Nauczyciel"
                    + "(id_nauczyciela,imie, nazwisko, wyksztalcenie) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, Tea.getTeacherID());
            preparedStatement.setString(2, Tea.getFirstName());
            preparedStatement.setString(3, Tea.getSecondName());
            preparedStatement.setString(4, Tea.getDegree());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


}
