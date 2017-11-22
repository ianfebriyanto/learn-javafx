package DivisiApps.DAO;

import DivisiApps.Model.DivisiModel;
import DivisiApps.Utilities.Contracts.ContractDBUtil;
import DivisiApps.Utilities.Contracts.ContractSumbitForm;
import DivisiApps.Utilities.DBUtil;
import DivisiApps.Utilities.DBUtil2;
import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAODivisi {
    /**
     * Get all divisi list
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<DivisiModel> getListDivisi() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM divisi";

        try {
            ResultSet divisi = DBUtil.executeQuery(query);
            ObservableList<DivisiModel> divisiModelObservableList = getListDivisiFromResultSet(divisi);
            return divisiModelObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * get divisi list from result set
     *
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static ObservableList<DivisiModel> getListDivisiFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        ObservableList<DivisiModel> divisiModelObservableList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            DivisiModel divisiModel = new DivisiModel();
            divisiModel.setKodeDivisi(resultSet.getString("kode_divisi"));
            divisiModel.setDivisi(resultSet.getString("divisi"));
            divisiModel.setKodeKantor(resultSet.getString("kd_kantor"));
            divisiModelObservableList.add(divisiModel);
        }

        return divisiModelObservableList;
    }

    /**
     * search divisi by name
     *
     * @param search
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<DivisiModel> searchDivisi(String search) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM divisi WHERE divisi LIKE '%" + search + "%'";

        try {
            ResultSet divisi = DBUtil.executeQuery(query);
            ObservableList<DivisiModel> divisiModelObservableList = getListDivisiFromResultSet(divisi);
            return divisiModelObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

   /* static ObservableList<DivisiModel> divisiModelObservableList;
    public static ObservableList<DivisiModel> searchDivisi(String search, int jenis) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM divisi WHERE divisi LIKE ?";
        DBUtil2.dbQuery(new ContractDBUtil() {
            @Override
            public void query(Connection connection, ResultSet resultSet, CachedRowSetImpl crs) {
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, "%" + search + "%");
                    resultSet = ps.executeQuery(query);
                    crs.populate(resultSet);
                    divisiModelObservableList = getListDivisiFromResultSet(crs);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void closeConnection(Connection connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * for insert new Divisi
     *
     * @param kodeDivisi
     * @param namaDivisi
     * @param kodeKantor
     * @throws SQLException
     */
    public static void insertNewDivisi(String kodeDivisi, String namaDivisi, String kodeKantor, @Nullable ContractSumbitForm<SQLException> response) throws SQLException {
        String query = "INSERT INTO divisi VALUES ('" + kodeDivisi + "', '" + namaDivisi + "', '" + kodeKantor + "')";

        try {
            DBUtil.dbExecuteUpdate(query);
            response.onSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
            response.onError(e);
        }
    }

    /**
     * for update divisi model
     *
     * @param kodeDivisi
     * @param namaDivisi
     * @param kodeKantor
     * @throws SQLException
     */
    public static void updateDivisi(String kodeDivisi, String namaDivisi, String kodeKantor, @Nullable ContractSumbitForm<SQLException> response) {
        String query = "UPDATE divisi SET divisi='" + namaDivisi + "', kd_kantor='" + kodeKantor + "' WHERE kode_divisi='" + kodeDivisi + "'";

        try {
            DBUtil.dbExecuteUpdate(query);
            if (response != null) {
                response.onSuccess();
            }
        } catch (SQLException e) {
            if (response != null) {
                response.onError(e);
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete data divisi
     *
     * @param kodeDivisi
     * @return
     * @throws SQLException
     */
    public static void deleteDivisi(String kodeDivisi, @Nullable ContractSumbitForm<SQLException> response) throws SQLException {
        String query = "DELETE FROM divisi WHERE kode_divisi = '" + kodeDivisi + "'";

        try {
            DBUtil.dbExecuteUpdate(query);
            response.onSuccess();
        } catch (SQLException e) {
            response.onError(e);
            e.printStackTrace();
        }
    }
}
