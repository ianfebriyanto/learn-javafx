package DivisiApps.DAO;

import DivisiApps.Utilities.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOLogin {
    public static Boolean login(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
            ResultSet resultSet = DBUtil.dbExecuteQuery(query);
            int count = 0;
            while (resultSet.next()) {
                count++;
            }

            if (count != 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
