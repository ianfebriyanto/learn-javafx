package DivisiApps.Utilities;

import DivisiApps.Utilities.Contracts.ContractDBUtil;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil2 {
    protected final static String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=";
    protected final static String dbName = "chman";
    protected final static String username = "sa";
    protected final static String password = "Febriyanto272";
    protected final static String currentUri = dbUrl + dbName;
    static Connection conn;

    public static void dbQuery(ContractDBUtil dbutil) {
        try {
            conn = connect();
            ResultSet resultSet = null;
            CachedRowSet crs = new CachedRowSetImpl();
            dbutil.query(conn, resultSet, (CachedRowSetImpl) crs);
            dbutil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection connect() {
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return conn = DriverManager.getConnection(currentUri, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
