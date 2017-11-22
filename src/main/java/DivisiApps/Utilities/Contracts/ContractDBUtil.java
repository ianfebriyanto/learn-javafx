package DivisiApps.Utilities.Contracts;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.ResultSet;

public interface ContractDBUtil {
    void query(Connection connection, ResultSet resultSet, CachedRowSetImpl crs);
    void closeConnection(Connection connection);
}
