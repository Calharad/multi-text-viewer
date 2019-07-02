package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;
import sample.code.dialogs.ExitDialog;
import sample.code.manager.FileManager;

import java.io.File;

public class Controller {

    private Stage stage = null;

    private FileManager manager;

    @FXML
    private TextArea textArea;

    @FXML
    private HBox list_box;

    @SuppressWarnings("WeakerAccess")
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        manager = new FileManager(list_box, textArea);
    }

    @SuppressWarnings("deprecation")
    @FXML
    public void openFile() {
        String currentDir = System.getProperty("user.dir") + File.separator;
        FileChooserBuilder fcb = FileChooserBuilder.create();
        FileChooser fc = fcb.title("Open Dialog").initialDirectory(new
                File(currentDir)).build();
        manager.openFile(fc.showOpenDialog(stage));
    }

    public void closeFile() {
        manager.closeFile(manager.getVisibleFile());
    }

    public void closeApp() {
        if(new ExitDialog().showDialog())
            stage.close();
    }
}
