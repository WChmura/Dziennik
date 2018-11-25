package Database;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DbTimetable {

    private int timetableID;
    private int groupID;
    private int classroomID;
    private int teacherID;
    private SimpleDateFormat data;
    private int hour;
    private String Subject;
    private static Connection con;



    /*****************GETTERS************************/
    public int getTimetableID() {
        return timetableID;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public SimpleDateFormat getData() {
        return data;
    }

    public int getHour() {
        return hour;
    }

    public String getSubject() {
        return Subject;
    }
}