package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassroomDAO {

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
}
