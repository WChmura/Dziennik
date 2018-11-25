package Database;
import java.sql.*;

public class DbStudent {

    private int studentID;
    private int groupID;
    private String firstName;
    private String secondName;
    private String personalInfo;
    private static Connection con;

    /***********GETTERS********************/
    public int getStudentID() {
        return studentID;
    }
    public int getGroupID() {
        return groupID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getPersonalInfo() {
        return personalInfo;
    }
}
