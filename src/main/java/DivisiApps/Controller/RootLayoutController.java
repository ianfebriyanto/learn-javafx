package DivisiApps.Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class RootLayoutController{
    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tentang Aplikasi");
        alert.setHeaderText("Aplikasi tentang management divisi");
        alert.setContentText("Create, Read, Update, Delete");
        alert.show();
    }

    @FXML
    private void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Keluar aplikasi");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
        }
    }
}
