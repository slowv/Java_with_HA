package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public JFXButton closeBtn;
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public TextField txtUsername;
    @FXML
    public JFXButton btnMinimize;
    @FXML
    public JFXButton btnSetting;
    @FXML
    public MaterialDesignIconView iconSetting;
    @FXML
    public MaterialDesignIconView iconMinimize;
    @FXML
    public MaterialDesignIconView iconClose;

    GlobalController globalController = new GlobalController();

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private Media media;

    @FXML
    private JFXCheckBox checkboxAnimation;
    @FXML
    private JFXCheckBox checkboxMusic;

    @FXML
    private StackPane stackpane;

    @FXML
    private Hyperlink hyperLinkCreateAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sử lý media
        String path = new File("src\\asset_public\\media\\login.mp4").getAbsolutePath();

        media = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        mediaPlayer.setAutoPlay(true);

        // Sử lý css
    }

    public void closeProgram(ActionEvent event) {
        globalController.dialogConfirmExit(stackpane, anchorPane, closeBtn);
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        globalController.miniProgram(anchorPane);
    }

    public void onHover(MouseEvent mouseEvent) {
//        System.out.println("Làm sau!");
    }

    public void checkboxes(ActionEvent actionEvent) {
        globalController.checkboxesClient(checkboxAnimation, checkboxMusic, mediaPlayer);
    }

    public void toPageRegister(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) hyperLinkCreateAccount.getScene().getWindow();

        window.setScene(scene);
        mediaPlayer.stop();
        window.show();
    }
}
