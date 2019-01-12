package Database.pojo;

import Database.Db_tests;

import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Presence {

    private int presenceId;
    private Date date;
    /** 0-obecnosc, 1-nieobecnosc **/
    private Integer type;
    private int studentId;
    private int teacherId;
    private int subjectId;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Presence(Date date, Integer type, int studentId, int teacherId, int subjectId) {
        this.presenceId = ID_GENERATOR.getAndIncrement();
        this.date = date;
        this.type = type;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public int getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(int presenceId) {
        this.presenceId = presenceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Presence{" +
                "presenceId=" + presenceId +
                ", date=" + date +
                ", type=" + type +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                '}';
    }
}
