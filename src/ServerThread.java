import Models.StudentPanel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;

    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Utworzono watek");
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());

            StudentPanel model = new StudentPanel("uczen");

            outToClient.writeObject(model.getSex());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}