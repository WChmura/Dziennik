package Database.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class Timetable {

    private int lessonID;
    private int groupID;
    private int classroomID;
    private int teacherID;
    private int day;
    private int hour;
    private int subjectID;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1040);

    public Timetable(int groupID, int classroomID, int teacherID, int day, int hour, int subjectID) {
        this.lessonID = ID_GENERATOR.getAndIncrement();
        this.groupID = groupID;
        this.classroomID = classroomID;
        this.teacherID = teacherID;
        this.day = day;
        this.hour = hour;
        this.subjectID = subjectID;
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
