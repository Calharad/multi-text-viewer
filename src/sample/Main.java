package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.code.dialogs.ExitDialog;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setOnCloseRequest((value) -> {
            if (!new ExitDialog().showDialog()) value.consume();
        });
        primaryStage.setTitle("TextViewer");
        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
