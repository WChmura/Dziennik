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

    public DbMark(int markID, int subjectID, int studentID, int teacherID, SimpleDateFormat date, String type, int mark, int weight, String description) {
        this.markID = markID;
        this.subjectID = subjectID;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.mark = mark;
        this.weight = weight;
        this.date = date;
        this.type = type;
        this.description = description;
    }
    /*******************SETTERS*****************************/

    //TODO:[Mateusz]Potrzebowałbym żeby settery mark and weight sprawdzały
    // czy to co dostają jest liczbą, jak nie to dawały np 0
    // jak ocena jest wyższa niż 6 mniejsza niż 0 to też niech na cos zamienia
    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

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