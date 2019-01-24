import Common.SerializableMethod;
import Database.pojo.Account;
import FrontEnd.Views.WelcomePage;
import Models.*;
import com.sun.media.sound.ModelInstrument;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.sql.Array;

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

            String login = (String)inFromClient.readObject();
            int permission = (int)inFromClient.readObject();
            Method m = ((SerializableMethod)inFromClient.readObject()).getMethod();
            Object[] args = (Object[]) inFromClient.readObject();


            //if(args.length > 0) for (Object arg : args) System.out.println("el. args: " + arg);

            //System.out.println("login = " + login + ", permission = " + permission + ", methodname = " + methodName + ", args = " + args);

            Model model;
            switch (permission) {
                case 0:
                    model = new StudentPanel(login);
                    break;
                case 1:
                    model = new ParentPanel(login);
                    break;
                case 2:
                    model = new TeacherPanel(login);
                    break;
                case 3:
                    model = new AdminPanel(login);
                    break;
                default:
                    model = new Authentication();
            }
            //Method m = null;
            /*Boolean methodExists = false;
            Class c = model.getClass();
            Class<?> classes [] = null;
            int i = 0;
            for (Object arg : args) {
                classes[i] = arg.getClass();
                i++;
            }
            while (!methodExists) {
                if (c.getName() == "java.lang.Object") c = new Authentication().getClass();
                try {
                    System.out.println("sprawdzacz czy " + methodName + "istnieje dla " + c.getName());
                    m = c.getMethod(methodName, classes);
                    m.invoke(c.getConstructor().newInstance(), args);
                    methodExists = true;
                } catch (NoSuchMethodException e) {
                    //e.printStackTrace();
                    c = c.getSuperclass();
                }
            }
            System.out.println("Found method: " + m.getName()   );*/
            Object result = null;
            try {
                if (args != null) result = m.invoke(model, args);
                else result = m.invoke(model);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            outToClient.writeObject(result) ;
            //System.out.println(result);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}