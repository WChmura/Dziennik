package Database;
import java.sql.*;

public class DbStudent {

    private int studentID;
    private int groupID;
    private String firstName;
    private String secondName;
    private String personalInfo;
    private static Connection con = DbConnection.getConnection();

    static void changePersonalInfo(int id, String personalInfo)
    {
        try {
            Statement statement = con.createStatement();
            String insertTableSQL = "UPDATE uczen Set dane_osobowe = ? WHERE id_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, personalInfo);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    /***********GETTERS********************/
    public int getStudentID() {
        return studentID;
    }
    public int getGroupID() {
        return groupID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getPersonalInfo() {
        return personalInfo;
    }
}
