package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO {

    /*** Insert do bazy ****/
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

    /** zwraca obiekt Przedmiot z bazy na podstawie id, moze sie przyda **/
    public static Subject getSubject(int id)
    {
        Subject sub = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Przedmiot where id_przedmiotu = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int subjectID;
            String name;
            while(rs.next())
            {
                subjectID = rs.getInt("id_przedmiotu");
                name = rs.getString("nazwa");
                sub = new Subject(name);
                sub.setSubjectID(subjectID);
                return sub;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sub;
    }



}
