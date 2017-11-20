package DivisiApps.Controller.Divisi;

import DivisiApps.DAO.DAODivisi;
import DivisiApps.Model.DivisiModel;
import DivisiApps.Utilities.AlertUtil;
import DivisiApps.Utilities.Contracts.ContractSumbitForm;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditDivisiController extends AbstractDivisi {
    @Override
    public void initialize() {
        super.initialize();
        inpKodeDivisi.setEditable(false);
        inpKodeDivisi.setStyle("-fx-background-color: GRAY;");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDivisiModel(DivisiModel divisiModel) {
        this.divisiModel = divisiModel;
        inpKodeDivisi.setText(divisiModel.getKodeDivisi());
        inpKodeKantor.setText(divisiModel.getKodeKantor());
        inpNamaDivisi.setText(divisiModel.getDivisi());
    }

    @Override
    public void btnSubmit() {
        boolean hasError = validateInput();
        if (!hasError) {
            String kodeDivisi = inpKodeDivisi.getText();
            String namaDivisi = inpNamaDivisi.getText();
            String kodeKantor = inpKodeKantor.getText();
            DAODivisi.updateDivisi(kodeDivisi, namaDivisi, kodeKantor, new ContractSumbitForm<SQLException>() {
                @Override
                public void onSuccess() {
                    AlertUtil.show(Alert.AlertType.INFORMATION,
                            "Update status",
                            "Updata data berhasil",
                            "Data divisi dengan kode: " + kodeDivisi + " berhasil diubah", null);
                    dialogStage.close();
                }

                @Override
                public void onError(SQLException e) {
                    AlertUtil.show(Alert.AlertType.ERROR,
                            "Oops!",
                            "Terjadi Kesalahan",
                            e.getMessage(), null);
                    dialogStage.close();
                    e.printStackTrace();
                }
            });
        }
    }
}
