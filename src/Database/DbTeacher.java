package Database;
import java.sql.*;

public class DbTeacher {

    private int teacherID;
    private String firstName;
    private String secondName;
    private String degree;

    public DbTeacher(int teacherID, String firstName, String secondName, String degree) {
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.degree = degree;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Nauczyciel"
                    + "(id_nauczyciela,imie, nazwisko, wyksztalcenie) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.teacherID);
            preparedStatement.setString(2, this.firstName);
            preparedStatement.setString(3, this.secondName);
            preparedStatement.setString(4, this.degree);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }


}