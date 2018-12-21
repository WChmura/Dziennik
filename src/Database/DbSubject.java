package Database;
import java.sql.*;
public class DbSubject {

    private int subjectID;
    private String name;



    public DbSubject(int subjectID, String name) {
        this.subjectID = subjectID;
        this.name = name;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO PRZEDMIOT"
                    + "(id_przedmiotu, nazwa) VALUES"
                    + "(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.subjectID);
            preparedStatement.setString(2, this.name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }



    }

}
 