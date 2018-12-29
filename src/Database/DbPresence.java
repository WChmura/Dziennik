package Database;
import java.sql.*;

public class DbPresence {

    private int presenceId;
    private Date date;
    private Integer type;
    private int studentId;
    private int teacherId;
    private int subjectId;


    public DbPresence(int presenceId, Date date, Integer type, int studentId, int teacherId, int subjectId) {
        if(type == null)
        {
            type=0;
        }
        this.presenceId = presenceId;
        this.date = date;
        this.type = type;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO OBECNOSC"
                    + "(id_obecnosci, data, typ, id_ucznia, id_nauczyciela, id_przedmiotu) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.presenceId);
            preparedStatement.setDate(2, this.date);
            preparedStatement.setInt(3, this.type);
            preparedStatement.setInt(4, this.studentId);
            preparedStatement.setInt(5, this.teacherId);
            preparedStatement.setInt(6, this.subjectId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    /**************************Edycja nieobecnosc*************************************/
    public static void updatePresenceType(int presenceID, int PresenceType) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Obecnosc set typ = ? where id_obecnosci = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, PresenceType);
            preparedStatement.setInt(2, presenceID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}