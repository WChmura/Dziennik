package Database.dao;

import Database.C3poDataSource;
import Database.pojo.*;

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

    /**********Zmiana danych osobowych; Czy potrzeba zmieniac cos wiecej niz adres? **/
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

    /**********Zmiana klasy **/
    public static void updateGroup(Student std, Group group) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE uczen set id_klasy = ? where id_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, group.getGroupID());
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

    /** zwraca obiekt Student z bazy na podstawie id, moze sie przyda **/
    public static Student getStudent(int id)
    {
        Student std= null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Uczen where id_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int studentID;
            int groupID;
            String firstName;
            String secondName;
            String adress;
            String sex;
            String personal_identity_number;
            while(rs.next())
            {
                studentID = rs.getInt("ID_ucznia");
                groupID = rs.getInt("Id_klasy");
                firstName = rs.getString("Imie");
                secondName = rs.getString("nazwisko");
                adress = rs.getString("adres");
                sex = rs.getString("plec");
                personal_identity_number = rs.getString("Pesel");
                std = new Student(groupID,firstName,secondName,adress,sex,personal_identity_number);
                std.setStudentID(studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return std;
    }

    /** zwraca obiekt Student z bazy na podstawie imienia i nazwiska **/
    public static Student getStudent(String firstName, String lastName)
    {
        Student std= null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Uczen where nazwisko = ? and imie = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            ResultSet rs = preparedStatement.executeQuery();

            int studentID;
            int groupID;
            String secondName;
            String adress;
            String sex;
            String personal_identity_number;
            while(rs.next())
            {
                studentID = rs.getInt("ID_ucznia");
                groupID = rs.getInt("Id_klasy");
                firstName = rs.getString("Imie");
                secondName = rs.getString("nazwisko");
                adress = rs.getString("adres");
                sex = rs.getString("plec");
                personal_identity_number = rs.getString("Pesel");
                std = new Student(groupID,firstName,secondName,adress,sex,personal_identity_number);
                std.setStudentID(studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return std;
    }

    /* Zwraca liste wszystkich uczniow danej klasy */
    public static ArrayList<Student> getStudentsFromGroup(String groupName)
    {
        ArrayList<Student> ListOfStudents = new ArrayList<Student>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Uczen natural join Klasa where Klasa.nazwa = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, groupName);
            ResultSet rs = preparedStatement.executeQuery();
            int id_message;
            int id_sender;
            int id_recipient;
            String topic;
            String msg;
            int readed;


            int studentID;
            int groupID;
            String firstName;
            String secondName;
            String adress;
            String sex;
            String personal_identity_number;
            while(rs.next()) {
                studentID = rs.getInt("ID_ucznia");
                groupID = rs.getInt("Id_klasy");
                firstName = rs.getString("Imie");
                secondName = rs.getString("nazwisko");
                adress = rs.getString("adres");
                sex = rs.getString("plec");
                personal_identity_number = rs.getString("Pesel");
                Student std = new Student(groupID, firstName, secondName, adress, sex, personal_identity_number);
                std.setStudentID(studentID);
                ListOfStudents.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfStudents;
    }

    /* Zwraca liste wszystkich uczniow danej klasy danego dnia danego nauczyciela */
    public static ArrayList<Student> getStudentsFromGroup(String groupName, Date date, int teacherID)
    {
        ArrayList<Student> ListOfStudents = new ArrayList<Student>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Uczen natural join Klasa where Klasa.nazwa = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, groupName);
            ResultSet rs = preparedStatement.executeQuery();
            int id_message;
            int id_sender;
            int id_recipient;
            String topic;
            String msg;
            int readed;


            int studentID;
            int groupID;
            String firstName;
            String secondName;
            String adress;
            String sex;
            String personal_identity_number;
            while(rs.next()) {
                studentID = rs.getInt("ID_ucznia");
                groupID = rs.getInt("Id_klasy");
                firstName = rs.getString("Imie");
                secondName = rs.getString("nazwisko");
                adress = rs.getString("adres");
                sex = rs.getString("plec");
                personal_identity_number = rs.getString("Pesel");
                Student std = new Student(groupID, firstName, secondName, adress, sex, personal_identity_number);
                std.setStudentID(studentID);
                ListOfStudents.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfStudents;
    }

}
