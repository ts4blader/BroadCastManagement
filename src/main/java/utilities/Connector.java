package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String userName = "root";
    private static final String passWord = "";
    private static final String dataBase = "";


    public static Connection getConnector() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName, passWord);
    }
}
