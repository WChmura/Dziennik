package Database.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {
    private int studentID;
    private int groupID;
    private String firstName;
    private String secondName;
    private String adress;
    private String sex;
    private String personal_identity_number;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1040);

    public Student(int groupID, String firstName, String secondName, String adress, String sex, String personal_identity_number) {
        this.studentID = ID_GENERATOR.getAndIncrement();
        this.groupID = groupID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;
        this.sex = sex;
        this.personal_identity_number = personal_identity_number;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonal_identity_number() {
        return personal_identity_number;
    }

    public void setPersonal_identity_number(String personal_identity_number) {
        this.personal_identity_number = personal_identity_number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", groupID=" + groupID +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", adress='" + adress + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
