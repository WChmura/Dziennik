package Database.pojo;

import Database.Db_tests;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Timetable implements Serializable {

    private int lessonID;
    private int groupID;
    private int classroomID;
    private int teacherID;
    private int day;
    private int hour;
    private int subjectID;
    private String teacherName;
    private String classroomName;
    private String groupName;
    private String subjectName;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Timetable(int groupID, int classroomID, int teacherID, int day, int hour, int subjectID) {
        this.lessonID = ID_GENERATOR.getAndIncrement();
        this.groupID = groupID;
        this.classroomID = classroomID;
        this.teacherID = teacherID;
        this.day = day;
        this.hour = hour;
        this.subjectID = subjectID;
    }

    public Timetable(String groupName, String classroomName, String teacherName, int day, int hour, String subjectName) {
        this.groupName = groupName;
        this.classroomName = classroomName;
        this.teacherName = teacherName;
        this.day = day;
        this.hour = hour;
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "lessonID=" + lessonID +
                ", groupID=" + groupID +
                ", classroomID=" + classroomID +
                ", teacherID=" + teacherID +
                ", day=" + day +
                ", hour=" + hour +
                ", subjectID=" + subjectID +
                '}';
    }
}
