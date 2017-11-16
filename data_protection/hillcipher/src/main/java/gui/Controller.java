package io.github.tkaczenko.HillCipher.src.main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller for {@code cipher.fxml}
 *
 * @see TextFlow
 * @see Text
 */
public class Controller implements Initializable {
    @FXML
    private TextField file_path;
    @FXML
    private TextFlow text_flow;
    @FXML
    private LetterTextField edit_key;

    private ResourceBundle bundle;

    private File selectedFile;
    private List<String> strings;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @FXML
    private void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(bundle.getString("text_files"), "*.txt")
        );
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            file_path.setText(selectedFile.getAbsolutePath());
            readFile();
        }
    }

    private void readFile() {
        // Read file using NIO and stream of {@code String}
        try (Stream<String> stream = Files.lines(Paths.get(selectedFile.getAbsolutePath()))) {
            strings = stream.collect(Collectors.toList());

            List<Text> texts = strings.parallelStream()
                    .map(Text::new)
                    .collect(Collectors.toList());

            text_flow.getChildren().clear();
            text_flow.getChildren().addAll(texts);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(bundle.getString("read_error"));
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void encrypt(ActionEvent event) {

    }

    @FXML
    private void decrypt(ActionEvent event) throws Exception {

    }

}