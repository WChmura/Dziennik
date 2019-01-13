package Database.dao;
import Database.C3poDataSource;
import Database.pojo.*;
import FrontEnd.Views.Schedule;

import java.sql.*;
import java.util.ArrayList;

public class TimetableDAO {

    /** insert do bazy **/
    public static void insertTimetable(Timetable tbl)
    {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO \"DZIENNIK3\".\"Plan\""
                    + "(ID_LEKCJI, DZIEN, GODZINA, ID_KLASY, ID_SALI, ID_NAUCZYCIELA, ID_PRZEDMIOTU) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, tbl.getLessonID());
            preparedStatement.setInt(2, tbl.getDay());
            preparedStatement.setInt(3, tbl.getHour());
            preparedStatement.setInt(4, tbl.getGroupID());
            preparedStatement.setInt(5, tbl.getClassroomID());
            preparedStatement.setInt(6, tbl.getTeacherID());
            preparedStatement.setInt(7, tbl.getLessonID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    /**  ma zwracac liste uczniow ktorzy maja w planie lekcje z danym nauczycielem danego dnia o konkretnej godzinie **/
    public static ArrayList<Student> getStudents(int day, int hour, Teacher tea)
    {
        ArrayList<Student> ListOfStudents = new ArrayList<Student>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from uczen inner join klasa on uczen.id_klasy = klasa.id_klasy inner join \"DZIENNIK3\".\"Plan\" on \"DZIENNIK3\".\"Plan\".id_klasy = uczen.id_klasy where godzina = ? and dzien= ? and \"DZIENNIK3\".\"Plan\".id_nauczyciela=? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, hour);
            preparedStatement.setInt(2, day);
            preparedStatement.setInt(3, tea.getTeacherID());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int studentID=rs.getInt("ID_ucznia");
                int groupID=rs.getInt("ID_klasy");
                String firstName=rs.getString("imie");
                String secondName=rs.getString("nazwisko");
                String adress=rs.getString("adres");
                String sex=rs.getString("plec");
                String personal_identity_number=rs.getString("pesel");
                Student std = new Student(groupID,firstName,secondName,adress,sex,personal_identity_number );
                std.setStudentID(studentID);
                ListOfStudents.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfStudents;
    }

    /**  zqwraca id grupy ktora ma lekcje danego dnia z danym nauczycielem **/
    public static int getGroupIdOfLesson(int day, int teacherID)
    {
        ArrayList<Student> ListOfStudents = new ArrayList<Student>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "select ID_klasy from \"DZIENNIK3\".\"Plan\" where dzien = ? and id_nuczyciela = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, day);
            preparedStatement.setInt(2, teacherID);
            ResultSet rs = preparedStatement.executeQuery();
            int groupID = -1;
            while(rs.next())
            {
                groupID=rs.getInt("ID_klasy");
            }
            return groupID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /** Pobieranie planu dla grupy, tu chyba będzie trzeba coc zmienic bo obiekty Timetable przechowuja praktycznie tylko id-iki **/
    public static ArrayList<Timetable> getSchedule(Group grp)
    {
        ArrayList<Timetable> ListOfSchedule = new ArrayList<Timetable>();
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from \"DZIENNIK3\".\"Plan\" where id_klasy = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, grp.getGroupID());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int lessonID = rs.getInt("id_lekcji");
                int groupID = rs.getInt("id_klasy");
                int classroomID = rs.getInt("id_sali");
                int teacherID = rs.getInt("id_nauczyciela");
                int day = rs.getInt("dzien");
                int hour = rs.getInt("godzina");
                int subjectID = rs.getInt("id_przedmiotu");

                Timetable tim = new Timetable(groupID,classroomID,teacherID,day,hour,subjectID);
                tim.setLessonID(lessonID);
                ListOfSchedule.add(tim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfSchedule;
    }

}
