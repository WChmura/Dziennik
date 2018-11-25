package Database;
import java.sql.*;

public class DbAccount {

    private int personID;
    private String login;
    private String password;
    private String permission;
    private String mailAddress;
    private static Connection con = DbConnection.getConnection();

    public DbAccount()
    {

    }
    /*Narazie zaslepka*/
    public static DbAccount fetch(String login, String haslo)
    {
        DbAccount db = new DbAccount();
        return db;
    }

    /********************Insert_do_tabeli***********************/
    /********************Zastonowic_sie_co_z_uczniem***************/
    public DbAccount(int personID, String login, String password, String permission, String mailAddress ) {

        this.personID = personID;
        this.login = login;
        this.password = password;
        this.permission = permission;
        this.mailAddress = mailAddress;

        try {
            String insertTableSQL = "INSERT INTO Konto"
                    + "(id_osoby,login, haslo, uprawnienia, adres_mail) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.personID);
            preparedStatement.setString(2, this.login);
            preparedStatement.setString(3, this.password);
            preparedStatement.setString(4, this.permission);
            preparedStatement.setString(5, this.mailAddress);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }
    /******************Delete_z_bazy*****************************************************/
    static void delete(int personID)
    {
        try {
            Statement statement = con.createStatement();
            statement.execute("Delete from KONTO where ID_osoby="+personID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /******************Zmiana hasla*****************************************************/
    static void updatePassword(int personID, String password)
    {
        try {
            Statement statement = con.createStatement();
            String insertTableSQL = "UPDATE konto Set haslo = ? WHERE id_osoby = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, personID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /******************Zmiana maila*****************************************************/
    static void updateMail(String mail, int id)
    {
        try {
            Statement statement = con.createStatement();
            String insertTableSQL = "UPDATE konto Set adres_mail = ? WHERE id_osoby = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***********************Getters**********************************************************/
    public int getPersonID() {
        return personID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPermission() {
        return permission;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
