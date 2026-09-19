package view;

import java.sql.SQLException;

public class Main {
    static void main() {
        try {
            AppContext ctx = new AppContext();
            new MainMenu(ctx).start();
        }catch (SQLException e){
            IO.println("ERROR: SQL Exception: " + e.getMessage());
        }
    }
}
