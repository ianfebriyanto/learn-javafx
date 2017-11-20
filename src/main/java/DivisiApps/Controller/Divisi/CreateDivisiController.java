package DivisiApps.Controller.Divisi;

import DivisiApps.DAO.DAODivisi;
import DivisiApps.Utilities.AlertUtil;
import DivisiApps.Utilities.Contracts.ContractSumbitForm;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class CreateDivisiController extends AbstractDivisi {
    public CreateDivisiController() {
        super();
    }

    @Override
    public void btnSubmit() {
        super.btnSubmit();
        String kodeDivisi = inpKodeDivisi.getText();
        String namaDivisi = inpNamaDivisi.getText();
        String kodeKantor = inpKodeKantor.getText();

        if (!kodeDivisi.trim().equals("") && !namaDivisi.trim().equals("") && !kodeKantor.trim().equals("")) {
            try {
                DAODivisi.insertNewDivisi(kodeDivisi, namaDivisi, kodeKantor, new ContractSumbitForm<SQLException>() {
                    @Override
                    public void onSuccess() {
                        AlertUtil.show(Alert.AlertType.INFORMATION,
                                "Information",
                                "Berhasil",
                                "Data berhasil ditambahkan", null);
                        dialogStage.close();
                    }

                    @Override
                    public void onError(SQLException e) {
                        AlertUtil.show(Alert.AlertType.INFORMATION,
                                "Information",
                                "Berhasil",
                                "Data berhasil ditambahkan", null);
                        dialogStage.close();
                    }
                });
            } catch (SQLException e) {
                AlertUtil.show(Alert.AlertType.ERROR,
                        "Oops!",
                        "Terjadi Kesalahan",
                        e.getMessage(), null);
                e.printStackTrace();
            }
        } else {
            AlertUtil.show(Alert.AlertType.ERROR,
                    "Oops!",
                    "Terjadi Kesalahan",
                    "Input tidak boleh kosong", null);
        }
    }
}