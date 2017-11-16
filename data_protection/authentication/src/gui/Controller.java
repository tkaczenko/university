package io.github.tkaczenko.Authentication.src.gui;

import io.github.tkaczenko.Authentication.src.security.authentication.AuthDataProvider;
import io.github.tkaczenko.Authentication.src.security.authentication.Authenticator;
import io.github.tkaczenko.Authentication.src.security.encryption.Alphabet;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import io.github.tkaczenko.Authentication.src.security.authentication.PassAuthDataProvider;
import io.github.tkaczenko.Authentication.src.security.encryption.Playfair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txLogin;
    @FXML
    private TextField txPass;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnSign;

    private Playfair playfair;
    private AuthDataProvider provider;
    private Authenticator authenticator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signIn() {
        String login = txLogin.getText();
        String password = txPass.getText();

        txLogin.clear();
        txPass.clear();

        playfair = new Playfair(new Alphabet.Ukrainian());
        playfair.setDelimetr('’');
        provider = new PassAuthDataProvider();
        authenticator = new Authenticator();
        authenticator.setDataProvider(provider);
        authenticator.setEncryptionSystem(playfair);

        Authenticator.AuthResult authResult = authenticator.authenticate(login, password);
        switch (authResult) {
            case OK:
                lblStatus.setText("Sign in successful");
                lblStatus.setTextFill(Color.LIGHTGREEN);
                showResult(login, password);
                break;
            case WRONG_PASSWORD:
                lblStatus.setText("Wrong password");
                lblStatus.setTextFill(Color.RED);
                break;
            case NO_SUCH_USER:
                lblStatus.setText("No such user");
                lblStatus.setTextFill(Color.RED);
                break;
            case NO_DATA:
                lblStatus.setText("No data");
                lblStatus.setTextFill(Color.RED);
                break;
            case NO_ENCRYPTION_SYSTEM:
                lblStatus.setText("No encryption system");
                lblStatus.setTextFill(Color.RED);
                break;
        }
    }

    private void showResult(String login, String password) {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        TextFlow textFlow = new TextFlow();

        textFlow.getChildren().addListener((ListChangeListener<Node>) ((change) -> {
            textFlow.requestLayout();
            scrollPane.requestLayout();
            scrollPane.setVvalue(1.0F);
        }));

        playfair = new Playfair(new Alphabet.Ukrainian());
        playfair.setDelimetr('’');
        List<Text> texts = new ArrayList<>();
        String encrypted = playfair.encrypt(password, login);
        texts.add(new Text("Encrypte:\n"));
        texts.add(new Text(playfair.toString() + "\n"));
        texts.add(new Text("Encrypted: " + encrypted + "\n"));

        String decrypted = playfair.decrypt(encrypted, login);
        texts.add(new Text("Decrypted: " + decrypted + "\n"));
        textFlow.getChildren().addAll(texts);

        scrollPane.setContent(textFlow);
        borderPane.setCenter(scrollPane);

        stage.setTitle("Show result");
        stage.setScene(new Scene(borderPane));
        stage.show();
    }
}
