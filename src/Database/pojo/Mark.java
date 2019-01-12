package Database.pojo;

import Database.Db_tests;

import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Mark {
    private int markID;
    private int subjectID;
    private int studentID;
    private int teacherID;
    private Date date;
    private int mark;
    private int weight;
    private String description;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);


    public Mark(int subjectID, int studentID, int teacherID, Date date, int mark, int weight, String description) {
        this.markID = ID_GENERATOR.getAndIncrement();
        this.subjectID = subjectID;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.date = date;
        this.mark = mark;
        this.weight = weight;
        this.description = description;
    }

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "markID=" + markID +
                ", subjectID=" + subjectID +
                ", studentID=" + studentID +
                ", teacherID=" + teacherID +
                ", date=" + date +
                ", mark=" + mark +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
    }
}
