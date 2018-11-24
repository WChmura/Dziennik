package Models;

import FrontEnd.Page;
import Database.DbAccount;

public abstract class Model {
    DbAccount account;

    public void showPage(FrontEnd.Page page) {
        page.createGUI();
    }
}
