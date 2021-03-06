package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {

    /** Insert do tabeli. Jako parametr przyjmuje obiekt typu Account**/
    public static void insertAccount(Account acc)
    {
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Konto"
                    + "(id_konta,login, hash, uprawnienia, adres_mail, id_ucznia) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, acc.getPersonID());
            preparedStatement.setString(2, acc.getLogin());
            preparedStatement.setString(3, acc.getHash());
            preparedStatement.setInt(4, acc.getPermission());
            preparedStatement.setString(5, acc.getMailAddress());
            preparedStatement.setInt(6, acc.getStudentID());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** Delete z bazy **/
    public static void deleteAccount(Account acc)
    {
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "Delete from KONTO WHERE Id_konta= ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, acc.getPersonID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** Zmiana hasla w bazie **/
    public static void updatePassword(Account acc, String hash) {
        Connection con=null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE konto set hash = ? where id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, hash);
            preparedStatement.setInt(2, acc.getPersonID());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /** Zmiana maila w bazie **/
    public static void updateMail(Account acc, String mail) {
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE konto set adres_mail = ? where id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, acc.getPersonID());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** Zwraca liste wszystkich loginow **/
    public static ArrayList<String> getAllAccounts()
    {
        Connection con = null;
        ArrayList<String> ListOfLogins = new ArrayList<String>();
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = " Select login from Konto ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                ListOfLogins.add(rs.getString("login"));
            }
            return  ListOfLogins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     return null;
    }

    /**Zwraca obiekt klasy Account  **/
    /**Jezeli nie istnieje konto o takim loginie i hashu zwraca null  **/
    public static Account getAccount(String login, String hash)
    {
        Account acc = null;
        Connection con=null;
        try {
            System.out.printf("przed polaczeniem");
            con = C3poDataSource.getConnection();
            System.out.println("po polaczeniu");
            String insertTableSQL = " select * from Konto where login = ? AND hash = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hash);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst())
            {
                System.out.println("Takie konto nie istnieje");
                return null;
            }
            int personIDT;
            String loginT;
            String hashT;
            int permissionT;
            String mailAddressT;
            int studentIDT;
            while(rs.next())
            {
                System.out.println("jest rekord");
                System.out.printf(rs.getString("adres_mail"));
                personIDT = rs.getInt("ID_KONTA");
                loginT=rs.getString("LOGIN");
                hashT=rs.getString("HASH");
                permissionT=rs.getInt("UPRAWNIENIA");
                mailAddressT=rs.getString("ADRES_MAIL");
                studentIDT=rs.getInt("ID_ucznia");
                acc = new Account(loginT,hashT,permissionT,mailAddressT,studentIDT);
                acc.setPersonID(personIDT);
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return acc;

    }


    /** zwraca obiekt Account z bazy na podstawie id, moze sie przyda **/
    public static Account getAccount(int id)
    {
        Account acc = null;
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Konto where id_konta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            int personIDT;
            String loginT;
            String hashT;
            int permissionT;
            String mailAddressT;
            int studentIDT;
            while(rs.next())
            {
                personIDT = rs.getInt("ID_KONTA");
                loginT=rs.getString("LOGIN");
                hashT=rs.getString("HASH");
                permissionT=rs.getInt("UPRAWNIENIA");
                mailAddressT=rs.getString("ADRES_MAIL");
                studentIDT=rs.getInt("ID_ucznia");
                acc = new Account(loginT,hashT,permissionT,mailAddressT,studentIDT);
                acc.setPersonID(personIDT);
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return acc;
    }

    /** zwraca obiekt Account z bazy na podstawie loginu **/
    public static Account getAccount(String login)
    {
        Account acc = null;
        Connection con=null;
        try {
            System.out.println("getAccount(" + login + ")");
            con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Konto where login = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("get2Account(" + login + ")");
            int personIDT;
            String loginT;
            String hashT;
            int permissionT;
            String mailAddressT;
            int studentIDT;
            while(rs.next())
            {
                personIDT = rs.getInt("ID_KONTA");
                loginT=rs.getString("LOGIN");
                hashT=rs.getString("HASH");
                permissionT=rs.getInt("UPRAWNIENIA");
                mailAddressT=rs.getString("ADRES_MAIL");
                studentIDT=rs.getInt("ID_ucznia");
                acc = new Account(loginT,hashT,permissionT,mailAddressT,studentIDT);
                acc.setPersonID(personIDT);
                System.out.println("get3Account(" + login + ")");

            }
            return acc;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return acc;
    }

    /** zwraca obiekt Account ucznia z bazy na podstawie imienia i nazwiska **/
    public static Account getStudentAccount(String firstName, String lastName)
    {
        Account acc = null;
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from Konto natural join uczen where imie = ? and nazwisko = ? and uprawnienia = 0";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet rs = preparedStatement.executeQuery();

            int personIDT;
            String loginT;
            String hashT;
            int permissionT;
            String mailAddressT;
            int studentIDT;
            while(rs.next())
            {
                personIDT = rs.getInt("ID_KONTA");
                loginT=rs.getString("LOGIN");
                hashT=rs.getString("HASH");
                permissionT=rs.getInt("UPRAWNIENIA");
                mailAddressT=rs.getString("ADRES_MAIL");
                studentIDT=rs.getInt("ID_ucznia");
                acc = new Account(loginT,hashT,permissionT,mailAddressT,studentIDT);
                acc.setPersonID(personIDT);
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return acc;
    }
}
