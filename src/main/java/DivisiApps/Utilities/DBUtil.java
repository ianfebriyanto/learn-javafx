package DivisiApps.Utilities;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBUtil {
    protected final static String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=";
    protected final static String dbName = "chman";
    protected final static String username = "sa";
    protected final static String password = "Febriyanto272";
    protected final static String currentUri = dbUrl + dbName;
    static Connection conn;

    public DBUtil() {
    }

    public static Connection getConn() {
        return conn;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;

        try {
            connect();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            close();
        }

        return crs;
    }

    public static ResultSet dbExecuteQuery(String query) throws SQLException {
        try {
            connect();
            Statement statement = conn.createStatement();
            ResultSet data = statement.executeQuery(query);
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * For insert, update and delete
     *
     * @param query
     * @throws SQLException
     */
    public static int dbExecuteUpdate(String query) throws SQLException {
        Statement stmt;
        try {
            connect();
            stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void connect() {
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(currentUri, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
