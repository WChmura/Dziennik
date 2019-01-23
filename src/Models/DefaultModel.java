package Models;

public abstract class DefaultModel implements Model {
    Database.pojo.Account account;

    public DefaultModel() {
    }

    public DefaultModel(String login) {
        account = Database.dao.AccountDAO.getAccount(login);
    }

}
