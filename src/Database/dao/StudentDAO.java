package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Mark;
import Database.pojo.Student;
import Database.pojo.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    /** Insert do tabeli. Jako parametr przyjmuje obiekt typu Student**/
    public static void insertStudent(Student std)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Uczen"
                    + "(id_ucznia, nazwisko, id_klasy, plec, pesel, adres, imie) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, std.getStudentID());
            preparedStatement.setString(2, std.getSecondName());
            preparedStatement.setInt(3, std.getGroupID());
            preparedStatement.setString(4, std.getSex());
            preparedStatement.setString(5, std.getPersonal_identity_number());
            preparedStatement.setString(6, std.getAdress());
            preparedStatement.setString(7, std.getFirstName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    /** Delete z bazy **/
    public static void deleteStudent(Student std)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "Delete from UCZEN WHERE Id_ucznia= ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, std.getStudentID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**********Zmiana danych osobowych*******************************************/
    public static void updatePersonalData(Student std, String adress) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE uczen set adres = ? where id_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, adress);
            preparedStatement.setInt(2, std.getStudentID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /************Zwraca dane osobowe wszystkich studentow***************************************/
    public static ResultSet getAllPersonalData() {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " Select * from Uczen";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();

            /**test**/
            while(rs.next())
            {
                System.out.println(rs.getInt("ID_ucznia")+" "+
                        rs.getString("Nazwisko")+" "+
                        rs.getString("Imie")+" "+
                        rs.getString("Pesel")+" "+
                        rs.getString("Adres")+" "+
                        rs.getString("Plec")
                );
            }
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** Zwraca liste wszystkich ocen **/
    public static ArrayList<Mark> getAllMarks(Student std)
    {
        ArrayList<Mark> ListOfMarks = new ArrayList<Mark>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Ocena where id_ucznia = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, std.getStudentID());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int markID = rs.getInt("ID_OCENY");
                int subjectID = rs.getInt("ID_PRZEDMIOTU");
                int studentID = rs.getInt("ID_UCZNIA");
                int teacherID = rs.getInt("ID_NAUCZYCIELA");
                Date date = rs.getDate("DATA");
                int mark = rs.getInt("OCENA");
                int weight = rs.getInt("WAGA");
                String description = rs.getString("OPIS");

                Mark mr = new Mark(subjectID, studentID, teacherID, date, mark, weight, description );
                mr.setMarkID(markID);
                ListOfMarks.add(mr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfMarks;
    }



}
