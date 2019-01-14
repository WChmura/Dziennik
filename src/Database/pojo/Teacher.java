package Database.pojo;

import Database.Db_tests;

import java.util.concurrent.atomic.AtomicInteger;

public class Teacher {

    private int teacherID;
    private String firstName;
    private String secondName;
    private String degree;
    private int account_id;
    private int subject_id;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Teacher(String firstName, String secondName, String degree, int account_id, int sub_id) {
        this.teacherID = ID_GENERATOR.getAndIncrement();
        this.firstName = firstName;
        this.secondName = secondName;
        this.degree = degree;
        this.account_id = account_id;
        this.subject_id = sub_id;
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

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", degree='" + degree + '\'' +
                ", account_id=" + account_id +
                ", subject_id=" + subject_id +
                '}';
    }
}
