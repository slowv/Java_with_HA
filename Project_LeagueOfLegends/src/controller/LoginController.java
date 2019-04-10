package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import database.model.Account;

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
    public PasswordField txtPassword;
    @FXML
    public Label msgLogin;
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

    @FXML
    private Label statusLogin;

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

        // Sử lý msg login
        txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    msgLogin.setVisible(false);
                }
            }
        });
        txtPassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    msgLogin.setVisible(false);
                }
            }
        });
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
        mediaPlayer.stop();
        globalController.switchScene(closeBtn, stackpane, anchorPane, "../views/Register.fxml");

    }

    public void login(MouseEvent mouseEvent) {
        RegisterController controller = new RegisterController();

        if (!controller.checkExistUsername(txtUsername.getText())) {
            msgLogin.setText("Tài khoản hoặc mật khẩu không đúng!");
            if (!msgLogin.isVisible()) {
                msgLogin.setVisible(true);
            }
            return;
        }

        Account account = new Account();
        account.setUsername(txtUsername.getText());
        account.setPassword(txtPassword.getText());

        statusLogin.setText("Authenting...");
        statusLogin.setVisible(true);
        boolean checkLogin = account.checkLogin();
        if (checkLogin) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                try {
                    statusLogin.setText("Loading...");
                    mediaPlayer.stop();
                    globalController.switchScene(closeBtn, stackpane, anchorPane, "../views/Home.fxml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }));
            timeline.play();
        } else {
            statusLogin.setVisible(false);
            msgLogin.setText("Tài khoản hoặc mật khẩu không đúng!");
            msgLogin.setVisible(true);
            System.out.println("Login fail!");
        }
    }
}
