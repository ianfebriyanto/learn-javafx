package DivisiApps.Utilities;

import DivisiApps.Utilities.Contracts.ContractConfirmation;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class AlertUtil {
    static Alert alert;

    /**
     * Only for warning, error, information
     * @param type
     * @param title
     * @param headerText
     * @param contentText
     */
    public static void show(Alert.AlertType type, String title, String headerText, String contentText, @Nullable ContractConfirmation confirmation) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        if (type == Alert.AlertType.CONFIRMATION) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                confirmation.btnOK(alert);
            } else {
                confirmation.btnCancel(alert);
            }
        } else {
            alert.show();
        }

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    alert.close();
                });
            }
        }, 10000, 1000);
    }

    /**
     * close the current alert
     */
    public static void close() {
        alert.close();
    }

    public static void setTitle() {

    }
}
