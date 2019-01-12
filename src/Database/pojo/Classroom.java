package Database.pojo;

import Database.Db_tests;

import java.util.concurrent.atomic.AtomicInteger;

public class Classroom {

    private int classroomID;
    private String name;
    private int numberOfSeats;
    private String type;
    private String specialEquipment;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(Db_tests.ID_GEN);

    public Classroom(String name, int numberOfSeats, String type, String specialEquipment) {
        this.classroomID = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
        this.specialEquipment = specialEquipment;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialEquipment() {
        return specialEquipment;
    }

    public void setSpecialEquipment(String specialEquipment) {
        this.specialEquipment = specialEquipment;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomID=" + classroomID +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", type='" + type + '\'' +
                ", specialEquipment='" + specialEquipment + '\'' +
                '}';
    }
}
