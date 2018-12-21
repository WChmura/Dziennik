
package Database;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DbMark {

    private int markID;
    private int subjectID;
    private int studentID;
    private int teacherID;
    private Date date;
    private int mark;
    private int weight;
    private String description;

    public DbMark(int markID, int subjectID, int studentID, int teacherID, Date date, int mark, int weight, String description) {
        this.markID = markID;
        this.subjectID = subjectID;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.date = date;
        this.mark = mark;
        this.weight = weight;
        this.description = description;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO OCENA"
                    + "(ID_OCENY, DATA, OCENA, WAGA, OPIS, ID_UCZNIA, ID_NAUCZYCIELA, ID_PRZEDMIOTU) VALUES"
                    + "(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.markID);
            preparedStatement.setDate(2, this.date);
            preparedStatement.setInt(3, this.mark);
            preparedStatement.setInt(4, this.weight);
            preparedStatement.setString(5, this.description);
            preparedStatement.setInt(6, this.studentID);
            preparedStatement.setInt(7, this.teacherID);
            preparedStatement.setInt(8, this.subjectID);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


    /********************EDYCJA OCENY**********************************************/
    public static void updateMark(int markID, int mark ) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set ocena = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, mark);
            preparedStatement.setInt(2, markID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateWeight(int markID, int weight) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set waga = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, weight);
            preparedStatement.setInt(2, markID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDescription(int markID, String description) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE Ocena set opis = ? where id_oceny = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, markID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
 