package Database;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DbTimetable {

    private int lessonID;
    private int groupID;
    private int classroomID;
    private int teacherID;
    private int day;
    private double hour;
    private int subjectID;


    public DbTimetable(int lessonID, int groupID, int classroomID, int teacherID, int day, double hour, int subjectID) {
        this.lessonID = lessonID;
        this.groupID = groupID;
        this.classroomID = classroomID;
        this.teacherID = teacherID;
        this.day = day;
        this.hour = hour;
        this.subjectID = subjectID;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Plan"
                    + "(id_lekcji, dzien, godzina, id_klasy, id_sali, id_nauczyciela, id_przedmiotu) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.lessonID);
            preparedStatement.setInt(2, this.day);
            preparedStatement.setDouble(3, this.hour);
            preparedStatement.setInt(4, this.groupID);
            preparedStatement.setInt(5, this.classroomID);
            preparedStatement.setInt(6, this.teacherID);
            preparedStatement.setInt(7, this.subjectID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }
}