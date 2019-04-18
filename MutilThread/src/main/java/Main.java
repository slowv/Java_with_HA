import model.ConnectionDb;
import utility.CheckCharacterSpecial;
import view.MainView;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectionDb.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MainView view = new MainView();
        try {
            view.menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
