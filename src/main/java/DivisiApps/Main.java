package DivisiApps;

import DivisiApps.Controller.Login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * to build,
 * mvn clean -> for clean all jar in target folder
 * mvn clean compile assembly:single -> to build with its dependencies
 */
public class Main extends Application {

    private static Stage primaryStage;

    private static BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Divisi Model");
        initLoginLayout();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static BorderPane getRootLayout() {
        return rootLayout;
    }

    public void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/layout_root.fxml"));
            rootLayout = (BorderPane) fxmlLoader.load();

            Scene scence = new Scene(rootLayout);
            primaryStage.setScene(scence);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDivisiLayout() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/layout/divisi/layout_main.fxml"));
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            rootLayout.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initLoginLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/login/layout_main.fxml"));
            AnchorPane loginLayout = (AnchorPane) fxmlLoader.load();

            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startApps() {
        this.initRootLayout();
        this.initDivisiLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
