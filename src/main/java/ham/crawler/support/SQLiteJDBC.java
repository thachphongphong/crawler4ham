package ham.crawler.support;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by dtlinh on 3/4/2016.
 */
public class SQLiteJDBC {
    public static Connection connectiondb() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:E:/ham_en.sl3");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }
}
