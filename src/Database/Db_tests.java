package Database;
import java.sql.*;
public class Db_tests {
    public static void main(String args[])
    {

        DbStudent student2 = new DbStudent(1111, 1111, "Wojtek", "Chmura", "Dywizjonow 303", "mezczyzna", "1231414151512515");
        DbStudent student3 = new DbStudent(1113, 1111, "Michal", "Bojes", "gdzies", "mezczyzna", "1231414151512589");
        DbStudent student4 = new DbStudent(1115, 1111, "Krzysiek", "Redut", "onet@kada", "mezczyzna", "12314141515125821");
        DbStudent.updateAdress(1113, "Rybitwy81");
        DbMark.updateDescription(1111, "Nowy opis");
        DbMark.updateMark(1111, 5);
        DbMark.updateWeight(1111, 4);
        DbPresence.updatePresenceType(1111,0);

        DbStudent.getPersonalData(1113);
    }
}
