package Database;
import java.sql.*;
public class DbTeacher {
    private int teacherID;
    private String firstName;
    private String secondName;
    private String degree;
    private Connection con = DbConnection.getConnection();



    /*****GETTERS**********/
    public int getTeacherID() {
        return teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getDegree() {
        return degree;
    }
}