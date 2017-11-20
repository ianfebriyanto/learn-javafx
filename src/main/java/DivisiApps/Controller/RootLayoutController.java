package DivisiApps.Controller;

import DivisiApps.Utilities.AlertUtil;
import DivisiApps.Utilities.Contracts.ContractConfirmation;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class RootLayoutController {
    @FXML
    private void about() {
        AlertUtil.show(Alert.AlertType.INFORMATION,
                "About Apps",
                "Divisi Management Apps",
                "You can manage Divisi using this apps and SQL Server database",
                null);
    }

    @FXML
    private void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        AlertUtil.show(Alert.AlertType.CONFIRMATION,
                "Confirmation",
                "Exit Comfirmation",
                "Exit Application",
                new ContractConfirmation() {
                    @Override
                    public void btnOK(Alert alert) {
                        System.exit(0);
                    }

                    @Override
                    public void btnCancel(Alert alert) {
                        alert.close();
                    }
                });
    }
}
