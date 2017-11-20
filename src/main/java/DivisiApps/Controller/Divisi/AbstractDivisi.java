package DivisiApps.Controller.Divisi;

import DivisiApps.Model.DivisiModel;
import DivisiApps.Utilities.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class AbstractDivisi {
    public Stage dialogStage;

    @FXML
    public Label labelTitle;

    @FXML
    public TextField inpKodeDivisi;

    @FXML
    public TextField inpKodeKantor;

    @FXML
    public TextField inpNamaDivisi;

    DivisiModel divisiModel;

    public void initialize() {
    }

    public AbstractDivisi() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void btnSubmit() {
    }

    @FXML
    public void btnCancel() {
        this.dialogStage.close();
    }

    public boolean validateInput() {
        if (inpKodeKantor.getText().trim().equals("")) {
            inpKodeKantor.setStyle(Validation.ERROR_BORDER);
            return true;
        } else {
            inpKodeKantor.setStyle(Validation.DEFAULT_BORDER);
        }

        if (inpNamaDivisi.getText().trim().equals("")) {
            inpNamaDivisi.setStyle(Validation.ERROR_BORDER);
            return true;
        } else {
            inpNamaDivisi.setStyle(Validation.DEFAULT_BORDER);
        }

        return false;
    }
}
