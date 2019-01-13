package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    public static void insertTeacher(Teacher Tea)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Nauczyciel"
                    + "(id_nauczyciela,imie, nazwisko, wyksztalcenie, id_konta) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, Tea.getTeacherID());
            preparedStatement.setString(2, Tea.getFirstName());
            preparedStatement.setString(3, Tea.getSecondName());
            preparedStatement.setString(4, Tea.getDegree());
            preparedStatement.setInt(5, Tea.getAccount_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


    /** zwraca obiekt Teacher z bazy na podstawie id, moze sie przyda **/
    public static Teacher getTeacher(int id)
    {
        Teacher tea = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Nauczyciel where id_nauczyciela = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int teacherID;
            String firstName;
            String secondName;
            String degree;
            int id_i;
            while(rs.next())
            {
                teacherID = rs.getInt("id_nauczyciela");
                firstName = rs.getString("imie");
                secondName = rs.getString("nazwisko");
                degree = rs.getString("wyksztalcenie");
                id_i = rs.getInt("id_konta");
                tea = new Teacher(firstName, secondName, degree, id_i);
                tea.setTeacherID(teacherID);
                return tea;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tea;
    }

    public static Teacher getTeacherFromAccount(Account account) {
        Teacher tea = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Nauczyciel where id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, account.getPersonID());
            ResultSet rs = preparedStatement.executeQuery();

            int teacherID;
            String firstName;
            String secondName;
            String degree;
            int id_i;
            while(rs.next())
            {
                teacherID = rs.getInt("id_nauczyciela");
                firstName = rs.getString("imie");
                secondName = rs.getString("nazwisko");
                degree = rs.getString("wyksztalcenie");
                id_i = rs.getInt("id_konta");
                tea = new Teacher(firstName, secondName, degree, id_i);
                tea.setTeacherID(teacherID);
                return tea;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tea;
    }

}
