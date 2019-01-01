package Database.dao;

import Database.C3poDataSource;
import Database.pojo.*;

import java.sql.*;
import java.util.ArrayList;

public class PresenceDAO {

    /** Insert do bazy **/
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

    /** Zmienia nieobecnosc na obecnosc(usprawiedliwienie); Czy jest potrzeba zmiany obecnosci na nieobecnosc? **/
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

    /** Zwraca liste obecnosci ucznia; Do dorobobienia dla nauczyciela i klasy? **/
    public static ArrayList<Presence> getAttendance(Student std)
    {
        ArrayList<Presence> ListOfAttendance = new ArrayList<Presence>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Obecnosc where id_ucznia = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, std.getStudentID());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int presenceId = rs.getInt("Id_obecnosci");
                Date date = rs.getDate("data");
                Integer type = rs.getInt("typ");
                int studentId = rs.getInt("Id_ucznia");
                int teacherId = rs.getInt("Id_nauczyciela");
                int subjectId = rs.getInt("Id_przedmiotu");
                Presence pres = new Presence(date, type, studentId, teacherId, subjectId);
                pres.setPresenceId(presenceId);
                ListOfAttendance.add(pres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfAttendance;
    }

    /** zwraca obiekt Account z bazy na podstawie id, moze sie przyda **/
    public static Presence getPresence(int id)
    {
        Presence pres = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Obecnosc where id_obecnosci = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            int presenceId;
            Date date;
            Integer type;
            int studentId;
            int teacherId;
            int subjectId;

            while(rs.next())
            {
                presenceId = rs.getInt("id_obecnosci");
                date = rs.getDate("data");
                type = rs.getInt("typ");
                studentId = rs.getInt("id_ucznia");
                teacherId = rs.getInt("id_nauczyciela");
                subjectId = rs.getInt("id_przedmiotu");
                pres = new Presence(date, type, studentId, teacherId, subjectId);
                pres.setPresenceId(presenceId);
                return pres;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pres;
    }
}
