import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 1234);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            System.out.println(inFromServer.readObject());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
