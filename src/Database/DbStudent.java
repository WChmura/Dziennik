package Database;
import java.sql.*;

public class DbStudent {

    private int studentID;
    private int groupID;
    private String firstName;
    private String secondName;
    private String adress;
    private String sex;
    private String personal_identity_number;

    public DbStudent(int studentID, int groupID, String firstName, String secondName, String adress, String sex, String personal_identity_number) {
        this.studentID = studentID;
        this.groupID = groupID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;
        this.sex = sex;
        this.personal_identity_number = personal_identity_number;

        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO Uczen"
                    + "(id_ucznia, nazwisko, id_klasy, plec, pesel, adres, imie) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, this.studentID);
            preparedStatement.setString(2, this.secondName);
            preparedStatement.setInt(3, this.groupID);
            preparedStatement.setString(4, this.sex);
            preparedStatement.setString(5, this.personal_identity_number);
            preparedStatement.setString(6, this.adress);
            preparedStatement.setString(7, this.firstName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Blad, opis ponizej: ");
            e.printStackTrace();
        }
    }
    /**********Zmiana danych osobowych*******************************************/
    public static void updateAdress(int studentID, String adress) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE uczen set adres = ? where id_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, adress);
            preparedStatement.setInt(2, studentID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /************Wyswietlanie danych osobowych***************************************/
    public static void getAllPersonalData() {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " Select * from Uczen";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            ResultSet rs = preparedStatement.executeQuery();

            /**test**/
            while(rs.next())
            {
                System.out.println(rs.getInt("ID_ucznia")+" "+
                        rs.getString("Nazwisko")+" "+
                        rs.getString("Imie")+" "+
                        rs.getString("Pesel")+" "+
                        rs.getString("Adres")+" "+
                        rs.getString("Plec")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void getPersonalData(int studentID) {
        try {
            Connection con = C3poDataSource.getConnection();
            String insertTableSQL = " Select * from Uczen where ID_ucznia = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, studentID);
            ResultSet rs = preparedStatement.executeQuery();

            /**test**/
            while(rs.next())
            {
                System.out.println(rs.getInt("ID_ucznia")+" "+
                        rs.getString("Nazwisko")+" "+
                        rs.getString("Imie")+" "+
                        rs.getString("Pesel")+" "+
                        rs.getString("Adres")+" "+
                        rs.getString("Plec")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}