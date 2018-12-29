package Database;
import Database.pojo.*;
import Database.dao.*;
import java.sql.*;
import java.util.ArrayList;

import static Database.DbMark.updateMark;
import static Database.dao.AccountDAO.*;
import static Database.dao.ClassroomDAO.insertClassroom;
import static Database.dao.GroupDAO.deleteGroup;
import static Database.dao.GroupDAO.insertGroup;
import static Database.dao.MarkDAO.*;
import static Database.dao.PresenceDAO.changeAttendance;
import static Database.dao.PresenceDAO.insertPresence;
import static Database.dao.StudentDAO.*;
import static Database.dao.SubjectDAO.insertSubject;
import static Database.dao.TeacherDAO.insertTeacher;
import static Database.dao.TimetableDAO.insertTimetable;

public class Db_tests {
    public static void main(String args[])
    {
        Teacher tea = new Teacher("wioleta","Wilas", "srednie");
        Subject sub = new Subject("wloski");
        Classroom cla = new Classroom("sala54", 20, "zwykla", "nic");

        Student std = new Student(1040, "Mateusz", "Natanek", "Rybitwy81", "m", "adsadsad");

        Mark mrk2 = new Mark(1040, std.getStudentID(), tea.getTeacherID(), new Date(32, 23, 22), 2, 4, "ocena2");
        Presence pr1 = new Presence(new Date(13,42,42), 0,std.getStudentID(), tea.getTeacherID(), sub.getSubjectID());
        Timetable tbl = new Timetable(1040, cla.getClassroomID(), tea.getTeacherID(),3,4,sub.getSubjectID());
        insertTimetable(tbl);


    }
}
