package Database;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DbMark {

    private int markID;
    private int subjectID;
    private int studentID;
    private int teacherID;
    private SimpleDateFormat date;
    private String type;
    private int mark;
    private int weight;
    private String description;
    private Connection con = DbConnection.getConnection();

    /*******************GETTERS*****************************/
    public int getMarkID() {
        return markID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getMark() {
        return mark;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }
}