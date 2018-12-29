package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubjectDAO {

    public static void insertSubject(Subject sub)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO PRZEDMIOT"
                    + "(id_przedmiotu, nazwa) VALUES"
                    + "(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, sub.getSubjectID());
            preparedStatement.setString(2, sub.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

}
