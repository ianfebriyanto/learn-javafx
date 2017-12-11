package DivisiApps.Controller;

import DivisiApps.Main;
import DivisiApps.Utilities.AlertUtil;
import DivisiApps.Utilities.Contracts.ContractConfirmation;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RootLayoutController {
    public BorderPane rootLayout;

    public void initialize() {

    }

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

    @FXML
    private void setting() {
        rootLayout = Main.getRootLayout();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/layout/setting/layout_main.fxml"));
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            rootLayout.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void divisi() {
        Main main = new Main();
        main.initDivisiLayout();
    }
}
