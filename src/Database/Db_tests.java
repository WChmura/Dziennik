package Database;
import Database.pojo.*;
import Database.dao.*;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;

import static Database.DbMark.updateMark;
import static Database.dao.AccountDAO.*;
import static Database.dao.ClassroomDAO.getClassroom;
import static Database.dao.ClassroomDAO.insertClassroom;
import static Database.dao.GroupDAO.*;
import static Database.dao.MarkDAO.*;
import static Database.dao.MessageDAO.*;
import static Database.dao.PresenceDAO.*;
import static Database.dao.StudentDAO.*;
import static Database.dao.SubjectDAO.getSubject;
import static Database.dao.SubjectDAO.insertSubject;
import static Database.dao.TeacherDAO.getTeacher;
import static Database.dao.TeacherDAO.insertTeacher;
import static Database.dao.TimetableDAO.insertTimetable;

public class Db_tests {

    public static int ID_GEN = 1000;

    public static void main(String args[])
    {





    }
}
