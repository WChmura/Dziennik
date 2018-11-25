package Database;
import java.sql.*;
import java.sql.Statement;
public class DbGroup {

    private int groupID;
    private int teacherID;
    private int classroomID;
    private int classLevel;

    private static Connection con = DbConnection.getConnection();

    /***********Insert_do_tabeli******************************************************/
    public DbGroup(int groupID, int teacherID, int classroomID, int classLevel) {
        this.groupID = groupID;
        this.teacherID = teacherID;
        this.classroomID = classroomID;
        this.classLevel = classLevel;
        try {
            String insertTableSQL = "INSERT INTO Klasa"
                    + "(id_klasy, id_nauczyciela, id_sali, numer) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.groupID);
            preparedStatement.setInt(2, this.teacherID);
            preparedStatement.setInt(3, this.classroomID);
            preparedStatement.setInt(4, this.classLevel);
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
        try {
            Statement statement = con.createStatement();
            statement.execute("Delete from KLASA where ID_klasy="+groupID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*****************Getters************************************************************/
    public int getGroupID() {
        return groupID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public int getClassLevel() {
        return classLevel;
    }
}