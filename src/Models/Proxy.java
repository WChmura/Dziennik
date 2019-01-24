package Models;

import Common.SerializableMethod;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;

public class Proxy implements InvocationHandler {
    private Model obj;

    public static Object newInstance(Model obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new Proxy(obj));
    }

    private Proxy(Model obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 2137);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());


            String login = "";
            int permission = 4;
            if (obj.getAccount() != null) {
                login = obj.getAccount().getLogin();
                permission = obj.getAccount().getPermission();
            }
            outToServer.writeObject(login);
            outToServer.writeObject(permission);
            outToServer.writeObject(new SerializableMethod(m));
            outToServer.writeObject(args);


            System.out.println("wysylam zapytanie o " + m.getName());
            //result = m.invoke(obj, args);

            result = inFromServer.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}