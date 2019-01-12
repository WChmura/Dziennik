package Database.pojo;

import Database.Db_tests;

import java.util.concurrent.atomic.AtomicInteger;

public class Subject {

    private int subjectID;
    private String name;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Subject(String name) {
        this.subjectID = ID_GENERATOR.getAndIncrement();
        this.name = name;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", name='" + name + '\'' +
                '}';
    }
}
