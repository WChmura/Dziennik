package Database.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class Group {

    private int groupID;
    private String name;
    private int teacherID;
    private int classroomID;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1040);

    public Group(String name, int teacherID, int classroomID) {
        this.groupID = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.teacherID = teacherID;
        this.classroomID = classroomID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", name='" + name + '\'' +
                ", teacherID=" + teacherID +
                ", classroomID=" + classroomID +
                '}';
    }
}
