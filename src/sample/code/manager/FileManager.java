package sample.code.manager;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import sample.code.views.FileDescView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class where main logic of application was created
 */
public class FileManager {


    /**
     * Keeps reference to HBox, where all opened files are put
     */
    private HBox container;

    /**
     * File content
     */
    private TextArea textArea;

    /**
     * List of opened files
     * @see FileDescView for more details
     */
    private ArrayList<FileDescView> fileList = new ArrayList<>();

    /**
     * Actually visible file ref
     */
    private FileDescView visibleFile;

    /**
     * Represents helpful information if any file is opened
     */
    private boolean isEmpty = true;

    /**
     * Constructor defining the background of logic
     * @param hBox reference to a container
     * @param area reference to content view
     */
    public FileManager(HBox hBox, TextArea area) {
        textArea = area;
        container = hBox;
        setNewActive(getEmptyFile());
        container.getChildren().add(visibleFile);
    }

    /**
     * Public method responsible for opening a file and adding it to a fileList
     * @param openingFile - file being opened
     *
     * @see FileDescView
     */
    public void openFile(File openingFile) {
        if(openingFile != null) {
            if (isEmpty) {
                isEmpty = false;
                container.getChildren().clear();
            }
            StringBuilder sb = readFile(openingFile);
            FileDescView view = new FileDescView(this, openingFile);
            view.setContent(sb.toString());
            container.getChildren().add(view);
            fileList.add(view);
            setNewActive(view);
            updateView();
        }
    }

    /**
     * Responsible for closing a file and removing it from list
     * @param file - object representing closing file
     */
    public void closeFile(FileDescView file) {
        if(file != visibleFile) {
            container.getChildren().remove(file);
            fileList.remove(file);
            if(fileList.size() <= 0) {
                isEmpty = true;
                setNewActive(getEmptyFile());
                container.getChildren().add(visibleFile);
            }
        }
        else {
            int index = fileList.indexOf(file);
            if(index + 1 < fileList.size()) {
                setNewActive(fileList.get(index + 1));
            }
            else if (index >0) {
                setNewActive(fileList.get(index - 1));
            }
            else {
                setNewActive(null);
            }

            if(visibleFile == null) {
                fileList.clear();
                container.getChildren().clear();
                isEmpty = true;
                setNewActive(getEmptyFile());
                container.getChildren().add(visibleFile);
            }
            else {
                fileList.remove(index);
                container.getChildren().remove(index);
            }
            updateView();
        }

    }

    /**
     * Getter returning visibleFile
     * @return file, which content is actually visible
     */
    public FileDescView getVisibleFile() {
        return visibleFile;
    }

    /**
     * Sets new visible File
     * @param visibleFile represents a File
     */
    public void setVisibleFile(FileDescView visibleFile) {
        setNewActive(visibleFile);
        updateView();
    }

    /**
     * Reads file content
     * @param selectedFile file which content is being read
     * @return StringBuilder containing content of a file
     */
    private StringBuilder readFile(File selectedFile) {
        StringBuilder sb = new StringBuilder(1024);
        String curLine = "";
        try {
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);
            while (curLine != null) {
                curLine = br.readLine();
                sb.append(curLine).append("\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return sb;
    }

    /**
     * Creates empty file reference
     * Empty File is a special type of FileDescView, because it does not represent any File
     * @return Empty File
     */
    private FileDescView getEmptyFile() {
        return new FileDescView(this, null);
    }

    /**
     * Clears textArea and puts new content
     */
    private void updateView() {
        textArea.setText(visibleFile.getContent());
    }

    /**
     * Used to set new visibleFile
     * It can also do other tasks
     * For example method sets bold styling of text for visibleFile
     * @param view
     */
    private void setNewActive(FileDescView view) {
        if (visibleFile != null) {
            visibleFile.setStyle(null);
        }
        visibleFile = view;
        if (visibleFile != null) {
            visibleFile.setStyle("-fx-font-weight: bold");
        }
    }
}
