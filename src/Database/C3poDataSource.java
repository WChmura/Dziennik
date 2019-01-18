package Database;
import java.sql.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;

public class C3poDataSource {

    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");
            cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
            cpds.setUser("dziennik3");
            cpds.setPassword("natan1995");
            cpds.setMaxPoolSize(1000000000);
        } catch (PropertyVetoException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
    private C3poDataSource(){}
}