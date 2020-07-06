package utilities;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
    private static final String userName = "root";
    private static final String passWord = "";
    private static final String dataBase = "";


    public static JdbcRowSet createRowSet (String url, String user, String pass, String cmd) {
        try {
            JdbcRowSet rowset = RowSetProvider.newFactory().createJdbcRowSet();
            rowset.setUrl (url);
            rowset.setUsername(user);
            rowset.setPassword(pass);
            rowset.setCommand(cmd);
            rowset.execute();
            return rowset;

        } catch (SQLException e) {}
        return null;
    }

    public static JdbcRowSet createRowSet (String cmd) {
        try {
            JdbcRowSet rowset = RowSetProvider.newFactory().createJdbcRowSet();
            rowset.setUrl ("jdbc:mysql://localhost/QLCTTV");
            rowset.setUsername("root");
            rowset.setPassword("");
            rowset.setReadOnly(false);
            rowset.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            rowset.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            rowset.setCommand(cmd);
            rowset.execute();
            return rowset;

        } catch (SQLException e) {}
        return null;
    }
}
