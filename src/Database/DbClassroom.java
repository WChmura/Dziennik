package Database;
import java.sql.*;

public class DbClassroom {


    private int classroomID;
    private String name;
    private int numberOfSeats;
    private String type;
    private String specialEquipment;
    private Connection con = DbConnection.getConnection();


    /*************Getters***************************/
    public int getClassroomID() {
        return classroomID;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getType() {
        return type;
    }

    public String getSpecialEquipment() {
        return specialEquipment;
    }
}