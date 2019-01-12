package Database.pojo;

import Database.Db_tests;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {

    private int personID;
    private String login;
    private String hash;
    private int permission;
    private String mailAddress;
    private Integer studentID;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Account(String login, String hash, int permission, String mailAddress, Integer studentID) {
        this.personID = ID_GENERATOR.getAndIncrement();
        this.login = login;
        this.hash = hash;
        this.permission = permission;
        this.mailAddress = mailAddress;
        this.studentID = studentID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }


    @Override
    public String toString() {
        return "Account{" +
                "personID=" + personID +
                ", login='" + login + '\'' +
                ", hash='" + hash + '\'' +
                ", permission=" + permission +
                ", mailAddress='" + mailAddress + '\'' +
                ", studentID=" + studentID +
                '}';
    }
}
