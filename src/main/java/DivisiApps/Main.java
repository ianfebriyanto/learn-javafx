package DivisiApps;

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

    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Divisi Model");
        initRootLayout();
        initDivisiLayout();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static URL setResource(String src) {
        return Main.class.getResource(src);
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

    public static void main(String[] args) {
        launch(args);
    }
}
