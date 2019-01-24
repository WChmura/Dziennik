package Models;

import Common.UserType;
import Database.dao.AccountDAO;
import Database.pojo.Account;

public abstract class DefaultModel implements Model {
    Database.pojo.Account account;

    public DefaultModel() {
    }

    public DefaultModel(String login) {
        account = Database.dao.AccountDAO.getAccount(login);
    }

    @Override
    public Account getAccount() {
        return account;
    }

    public UserType logIn(String[] loginData) {
        account = AccountDAO.getAccount(loginData[0], loginData[1]);
        if(account!=null) {
            if (account.getPermission() == 3) {
                return UserType.admin;
            } else if (account.getPermission() == 2) {
                return UserType.teacher;
            } else if (account.getPermission() == 1) {
                return UserType.parent;
            } else if (account.getPermission() == 0) {
                return UserType.student;
            }
        }
        return null;
    }
}
