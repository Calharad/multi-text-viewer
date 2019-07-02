package sample.code.views;

import com.sun.istack.internal.Nullable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sample.code.manager.FileManager;

import java.io.File;

/**
 * Class representing one File
 * Contains two elements:
 *  - name of the file
 *  - close symbol used to close file
 *
 *  Extends HBox
 *
 *  @see javafx.scene.layout.HBox
 */
public class FileDescView extends HBox {

    /**
     * Reference to FileManager instance
     */
    private FileManager fileManager;

    /**
     * Reference to file on a device
     */
    private File file;

    /**
     * Prepared content of a file
     */
    private String content;

    /**
     * Main constructor. Sets view of object and handlers
     * @param manager - ref to FileManager instance
     * @param file - ref to file on a device
     */
    public FileDescView(FileManager manager, File file) {
        super();
        setAlignment(Pos.BOTTOM_LEFT);
        setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) fileManager.setVisibleFile(this);
        });
        fileManager = manager;
        this.file = file;
        /**
         *
         */
        Text name = new Text(file != null ? file.getName() : "(empty)");
        getChildren().add(name);
        Text close = new Text("X");
        HBox.setMargin(close, new Insets(0, 15 , 0, 5));
        close.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) fileManager.closeFile(this);
        });
        getChildren().add(close);
    }

    /**
     * Getter
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter
     * @param content value
     */
    public void setContent(String content) {
        this.content = content;
    }

    @SuppressWarnings("unused")
    public File getFile() {
        return file;
    }
}
