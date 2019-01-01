package Database.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class Teacher {

    private int teacherID;
    private String firstName;
    private String secondName;
    private String degree;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1060);

    public Teacher(String firstName, String secondName, String degree) {
        this.teacherID = ID_GENERATOR.getAndIncrement();
        this.firstName = firstName;
        this.secondName = secondName;
        this.degree = degree;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
