package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Db_tests {
    public static void main(String args[])
    {
        DbStudent.changePersonalInfo( 17,"Przewlekle chory");
        DbAccount.updatePassword(15, "Nowe_haslo");
        System.out.println("tu");

    }
}