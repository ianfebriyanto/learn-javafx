package DivisiApps.Controller.Login;

import DivisiApps.DAO.DAOLogin;
import DivisiApps.Main;
import DivisiApps.Utilities.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.lang.management.ManagementFactory;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView icon;

    @FXML
    private Button btnLogin;

    public void initialize() {
        icon.setImage(new Image("/images/icon.png"));
    }

    @FXML
    private void doLogin() {
        String username = this.username.getText();
        String password = this.password.getText();
        Boolean userFound = DAOLogin.login(username, password);
        if (userFound) {
            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.close();

            Main main = new Main();
            main.startApps();
        } else {
            AlertUtil.show(Alert.AlertType.ERROR,
                    "Oops!",
                    "Terjadi Kesalahan",
                    "Username atau password anda tidak cocok",
                    null);
        }
    }
}
