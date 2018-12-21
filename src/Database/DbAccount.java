package Database;
import java.sql.*;

public class DbAccount {

    private int personID;
    private String login;
    private String hash;
    private int permission;
    private String mailAddress;
    private int studentID;

    /*******************Zaslepki/do usuniecia**********************/
    public DbAccount() {
    }
    public static DbAccount fetch(String login, String haslo) {
        DbAccount db = new DbAccount();
        return db;
    }

    /********************Insert_do_tabeli***********************/
    public DbAccount(int personID, String login, String hash, int permission, String mailAddress, int studentID) {

        this.personID = personID;
        this.login = login;
        this.hash = hash;
        this.permission = permission;
        this.mailAddress = mailAddress;
        this.studentID = studentID;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Konto"
                    + "(id_konta,login, hash, uprawnienia, adres_mail, id_ucznia) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.personID);
            preparedStatement.setString(2, this.login);
            preparedStatement.setString(3, this.hash);
            preparedStatement.setInt(4, this.permission);
            preparedStatement.setString(5, this.mailAddress);
            preparedStatement.setInt(6, this.studentID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }

    /******************Delete_z_bazy*****************************************************/
    public static void delete(int personID) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "Delete from KONTO WHERE Id_konta= ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, personID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /******************Zmiana hash*****************************************************/
    public static void updatePassword(int personID, String hash) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE konto set hash = ? where id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, hash);
            preparedStatement.setInt(2, personID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /******************Zmiana maila*****************************************************/
    public static void updateMail(String mail, int id) {
        try {
            Connection con = C3poDataSource.getConnection();
            Statement statement = con.createStatement();
            String insertTableSQL = "UPDATE konto Set adres_mail = ? WHERE id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}