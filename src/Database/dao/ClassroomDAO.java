package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomDAO {

    /** Insert do bazy **/
    public static void insertClassroom(Classroom cla)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO SALA"
                    + "(id_sali, nazwa, ilosc_miejsc, typ, wyposazenie_specjalne) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, cla.getClassroomID());
            preparedStatement.setString(2, cla.getName());
            preparedStatement.setInt(3, cla.getNumberOfSeats());
            preparedStatement.setString(4, cla.getType());
            preparedStatement.setString(5, cla.getSpecialEquipment());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


    /** zwraca obiekt Classroom z bazy na podstawie id, moze sie przyda **/
    public static Classroom getClassroom(int id)
    {
        Classroom cla = null;
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Sala where id_sali = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int classroomID;
            String name;
            int numberOfSeats;
            String type;
            String specialEquipment;
            while(rs.next())
            {
                classroomID = rs.getInt("id_sali");
                name = rs.getString("nazwa");
                numberOfSeats = rs.getInt("ilosc_miejsc");
                type = rs.getString("typ");
                specialEquipment = rs.getString("wyposazenie_specjalne");
                cla = new Classroom(name, numberOfSeats, type, specialEquipment);
                cla.setClassroomID(classroomID);
                return cla;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cla;
    }


}


