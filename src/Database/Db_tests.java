package Database;

import Database.dao.GroupDAO;
import Database.pojo.Account;

import static Database.dao.AccountDAO.getAccount;
import static Database.dao.TeacherDAO.getTeacherFromAccount;

public class Db_tests {

    public static int ID_GEN = 1000;

    public static void main(String args[])
    {
        Account acc1 = getAccount(1001);
        System.out.println(getTeacherFromAccount(acc1));




    }
}
