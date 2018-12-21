package Database;
import java.sql.*;

public class DbClassroom {


    private int classroomID;
    private String name;
    private int numberOfSeats;
    private String type;
    private String specialEquipment;


    public DbClassroom(int classroomID, String name, int numberOfSeats, String type, String specialEquipment) {
        this.classroomID = classroomID;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
        this.specialEquipment = specialEquipment;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO SALA"
                    + "(id_sali, nazwa, ilosc_miejsc, typ, wyposazenie_specjalne) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.classroomID);
            preparedStatement.setString(2, this.name);
            preparedStatement.setInt(3, this.numberOfSeats);
            preparedStatement.setString(4, this.type);
            preparedStatement.setString(5, this.specialEquipment);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


}