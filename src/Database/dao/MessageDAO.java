package Database.dao;

import Database.C3poDataSource;
import Database.pojo.Account;
import Database.pojo.Mark;
import Database.pojo.Message;
import Database.pojo.Student;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {

    public static void insertMessage(Message msg)
    {
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "INSERT INTO WIADOMOSC"
                    + "(ID_WIADOMOSCI, ID_NADAWCY, ID_ODBIORCY, TEMAT, WIADOMOSC, PRZECZYTANA) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, msg.getId_message());
            preparedStatement.setInt(2, msg.getId_sender());
            preparedStatement.setInt(3, msg.getId_recipient());
            preparedStatement.setString(4, msg.getTopic());
            preparedStatement.setString(5, msg.getMsg());
            preparedStatement.setInt(6, msg.getReaded());
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

    /* Zwraca liste wszystkich wiadomosci do odczytu dla danego konta */
    public static ArrayList<Message> getAllMesseges(Account acc)
    {
        ArrayList<Message> ListOfMesseges = new ArrayList<Message>();
        Connection con = null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from WIADOMOSC where id_odbiorcy = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, acc.getPersonID());
            ResultSet rs = preparedStatement.executeQuery();
            int id_message;
            int id_sender;
            int id_recipient;
            String topic;
            String msg;
            int readed;

            while(rs.next())
            {
                id_message = rs.getInt("id_wiadomosci");
                id_sender = rs.getInt("id_nadawcy");
                id_recipient = rs.getInt("id_odbiorcy");
                topic = rs.getString("temat");
                msg = rs.getString("wiadomosc");
                readed = rs.getInt("przeczytana");

                Message msg1 = new Message(id_sender, id_recipient, topic, msg, readed);
                msg1.setId_message(id_message);
                ListOfMesseges.add(msg1);

            }
            return ListOfMesseges;
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



    /* Zwraca liste wszystkich wiadomosci wyslanych z danego konta */
    public static ArrayList<Message> getAllSended(Account acc)
    {
        ArrayList<Message> ListOfMesseges = new ArrayList<Message>();
        Connection con =null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = " select * from WIADOMOSC where id_nadawcy = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, acc.getPersonID());
            ResultSet rs = preparedStatement.executeQuery();
            int id_message;
            int id_sender;
            int id_recipient;
            String topic;
            String msg;
            int readed;

            while(rs.next())
            {
                id_message = rs.getInt("id_wiadomosci");
                id_sender = rs.getInt("id_nadawcy");
                id_recipient = rs.getInt("id_odbiorcy");
                topic = rs.getString("temat");
                msg = rs.getString("wiadomosc");
                readed = rs.getInt("przeczytana");

                Message msg1 = new Message(id_sender, id_recipient, topic, msg, readed);
                msg1.setId_message(id_message);
                ListOfMesseges.add(msg1);
            }
            return ListOfMesseges;
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


        return ListOfMesseges;
    }

    public static void markAsReaded(Message msg) {
        Connection con=null;
        try {
            con = C3poDataSource.getConnection();
            String insertTableSQL = "UPDATE WIADOMOSC set przeczytana = 1 where id_wiadomosci = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setInt(2, msg.getId_message());
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
}
