package Models;

import Common.UserType;

public abstract class Model {
    Database.pojo.Account account;

    public void Model () {

    }

    public void Model(String login) {
        account = Database.dao.AccountDAO.getAccount(login);
    }
}
