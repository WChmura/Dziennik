package Models;

public class Authentication extends Model {

    private String hash(String password) {
        // TODO: implement a hashing function
        return password;
    }

    public void logIn(String username, String password) {
        //Database.DbAccount account = Database.DbAccount.fetch(username, hash(password));
        /*if (account) {
            this.account = account;
        }
        else showPage(new FrontEnd.Views.WelcomePage());*/
    }

    public void logOut() {
        this.account = null;
        showPage(new FrontEnd.Views.WelcomePage());
    }
}
