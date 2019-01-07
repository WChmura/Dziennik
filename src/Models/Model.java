package Models;

public abstract class Model {
    Database.pojo.Account account;

    public void showPage(FrontEnd.Page page) {
        page.init();
    }
}
