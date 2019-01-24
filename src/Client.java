import FrontEnd.Views.WelcomePage;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 2137);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            System.out.println(inFromServer.readObject());

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new WelcomePage();
                }
            });

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
