
package Database;
import java.sql.*;
public class DbSubject {

    private int subjectID;
    private String name;
    private Connection con = DbConnection.getConnection();

    /**GETTERS**/
    public int getSubjectID() {
        return subjectID;
    }

    public String getName() {
        return name;
    }
}