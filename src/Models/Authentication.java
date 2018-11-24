package Models;

import Database.DbAccount;
import FrontEnd.Views.WelcomePage;

public class Authentication extends Model {

    private String hash(String password) {
        // TODO: implement a hashing function
        return password;
    }

    public void logIn(String username, String password) {
        if (account = Database.DbAccount.fetch(username, hash(password)) {
            this.account = account;
        }
        else showPage(new FrondEnd.View.WelcomePage());
    }

    public void logOut() {
        this.account = null;
        showPage(new FrondEnd.View.WelcomePage());
    }
}
