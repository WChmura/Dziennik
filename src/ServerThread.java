import Common.SerializableMethod;
import Database.dao.*;
import Database.pojo.Account;
import Database.pojo.Message;
import Database.pojo.Timetable;
import FrontEnd.Views.WelcomePage;
import Models.*;
import com.sun.media.sound.ModelInstrument;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

            Object result = null;
            try {
                if (args != null) result = m.invoke(model, args);
                else result = m.invoke(model);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            outToClient.writeObject(result) ;

            socket.close();

            if (permission == 2) {
                Account acc = model.getAccount();

                ArrayList<Message> msgs = MessageDAO.getAllMesseges(acc);

                DateFormat day = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                if ((cal.get(Calendar.HOUR_OF_DAY) == 9 && cal.get(Calendar.MINUTE) >= 10) || cal.get(Calendar.HOUR_OF_DAY) > 9) { // after 9:10
                    cal.add(Calendar.DATE, 1); // set time to tomorrow
                    if (cal.get(Calendar.DAY_OF_WEEK) > 6) return; // tomorrow's a weekend day
                    for (Message msg : msgs) {
                        if (msg.getTopic().equals("Lekcje w dniu " + day.format(cal.getTime()))) return; // already notified
                    }
                    String lessons = "";
                    ArrayList<Timetable> schedule = TimetableDAO.getScheduleForTeacher(TeacherDAO.getTeacherFromAccount(acc));
                    for (Timetable t : schedule) {
                        if (t.getDay()+1 == cal.get(Calendar.DAY_OF_WEEK)) {
                            lessons += t.getHour() + ". " + GroupDAO.getGroup(t.getGroupID()).getName() + ", "
                                    + ClassroomDAO.getClassroom(t.getClassroomID()).getName() + " - "
                                    + SubjectDAO.getSubject(t.getSubjectID()).getName() + "; ";
                        }
                    }
                    if (!lessons.equals("")) {
                        Message msg = new Message(AccountDAO.getAccount("admin").getPersonID(), acc.getPersonID(),
                                "Lekcje w dniu " + day.format(cal.getTime()), lessons, 0);
                        MessageDAO.insertMessage(msg);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}