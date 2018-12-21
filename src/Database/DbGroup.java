package Database;
import java.sql.*;
import java.sql.Statement;
public class DbGroup {

    private int groupID;
    private String name;
    private int teacherID;
    private int classroomID;


    /***********Insert_do_tabeli******************************************************/
    public DbGroup(int groupID, String name, int classroomID, int teacherID) {
        this.groupID = groupID;
        this.name = name;
        this.classroomID = classroomID;
        this.teacherID = teacherID;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Klasa"
                    + "(id_klasy, nazwa, id_sali, id_nauczyciela) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.groupID);
            preparedStatement.setString(2, this.name);
            preparedStatement.setInt(3, this.classroomID);
            preparedStatement.setInt(4, this.teacherID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }
    /******************Delete_z_bazy*****************************************************/
    static void delete(int groupID)
    {

    }

    /*****************Getters************************************************************/



}