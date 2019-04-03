package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    // VAR fxml
    @FXML
    private MediaView mediaView;

    @FXML
    private AnchorPane anchorPane;

    private MediaPlayer mediaPlayer;

    @FXML
    private JFXButton btnMinimize;

    @FXML
    private JFXButton btnSetting;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label msgUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label msgPassword;

    @FXML
    private Text titleRegister;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private Label msgRePassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private Label msgPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label msgEmail;

    @FXML
    private JFXCheckBox checkboxAnimation;

    @FXML
    private JFXCheckBox checkboxMusic;


    // VAR controller
    GlobalController globalController = new GlobalController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sử lý media
        String path = new File("src\\asset_public\\media\\register.mp4").getAbsolutePath();
//        closeBtn.getScene().getStylesheets().add("../asset_public/css/application.css");

        mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));

        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        mediaPlayer.setAutoPlay(true);

        // Sủ lý on key press register form
        RegisterController controller = new RegisterController();
        txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    msgUsername.setVisible(false);
                }
            }
        });

        txtPassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    msgPassword.setVisible(false);
                }
            }
        });

        txtRePassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    msgRePassword.setVisible(false);
                }
            }
        });

        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    msgEmail.setVisible(false);
                }
            }
        });

        txtPhone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    msgPhone.setVisible(false);
                }
            }
        });
    }

    public RegisterController(){
        GlobalController.addAllConfigsAccount();
    }


    public void minimizeProgram(MouseEvent mouseEvent) {
        globalController.miniProgram(anchorPane);
    }

    public void closeProgram(ActionEvent actionEvent) {
        StackPane stackPane = (StackPane) anchorPane.getScene().getRoot();
        globalController.dialogConfirmExit(stackPane, anchorPane, closeBtn);
    }

    public void checkboxes(ActionEvent actionEvent) {
        globalController.checkboxesClient(checkboxAnimation, checkboxMusic, mediaPlayer);
    }




    public void register(MouseEvent mouseEvent) {
        RegisterController controller = new RegisterController();
        if (controller.checkExistUsername(txtUsername.getText())){
            msgUsername.setText("* Tài khoản đã tồn tại vui lòng chọn tài khoản khác!");
            if (!msgUsername.isVisible()){
                msgUsername.setVisible(true);
            }
            return;
        }

        if (txtPassword.getText().length() < 6){
            msgPassword.setText("* Mật khẩu ít nhất phải 6 ký tự.");
            if (!msgPassword.isVisible()){
                msgPassword.setVisible(true);
            }
            return;
        }

        if (!txtRePassword.getText().equals(txtPassword.getText())){
            msgRePassword.setText("Mật khẩu không khớp, vui lòng nhập lại!");
            if (!msgRePassword.isVisible()){
                msgRePassword.setVisible(true);
            }
            return;
        }

        if (controller.checkExistPhone(txtPhone.getText())){
            msgPhone.setText("Số điện thoại đã tồn tại, vui lòng nhập số khác!");
            if (!msgPhone.isVisible()){
                msgPhone.setVisible(true);
            }
            return;
        }

        if(controller.checkExistEmail(txtEmail.getText())){
            msgEmail.setText("* Email đã tồn tại vui lòng chọn email khác!");
            if (!msgEmail.isVisible()){
                msgEmail.setVisible(true);
            }
            return;
        }

        Account account = new Account(txtUsername.getText(), txtPassword.getText(),txtEmail.getText() ,txtPhone.getText());
        boolean checkRegister = (boolean) account.insert(account, titleRegister);
        if (checkRegister){
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("../views/Home.fxml"));
                        Scene scene = new Scene(parent);
                        Stage window = (Stage) titleRegister.getScene().getWindow();
                        window.setScene(scene);
                        mediaPlayer.stop();
                        window.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            new Thread(sleeper).start();
        }
    }

    public boolean checkExistEmail(String email){
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        boolean check = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where email = :email");
            query.setParameter("email", email);
            query.setMaxResults(1);
            Account result = (Account) query.uniqueResult();
            if (result == null){
                System.out.println("ok email không tồn tại :)~");
                return check;
            }
            System.out.println(result.toString());
            transaction.commit();
            return !check;

        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return check;
    }

    public boolean checkExistUsername(String username){
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        boolean check = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where username = :username");
            query.setParameter("username", username);
            query.setMaxResults(1);
            Account result = (Account) query.uniqueResult();
            if (result == null){
                System.out.println("ok username không tồn tại :)~");
                return check;
            }
            System.out.println(result.toString());
            transaction.commit();
            return !check;

        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return check;
    }

    public boolean checkExistPhone(String phone){
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        boolean check = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where phone = :phone");
            query.setParameter("phone", phone);
            query.setMaxResults(1);
            Account result = (Account) query.uniqueResult();
            if (result == null){
                System.out.println("ok số điện thoại không tồn tại :)~");
                return check;
            }
            System.out.println(result.toString());
            transaction.commit();
            return !check;

        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return check;
    }

    @FXML
    private Pane btnBackLogin;

    public void backLogin(MouseEvent mouseEvent) throws IOException {
        StackPane stackPane = (StackPane) btnBackLogin.getScene().getRoot();
        mediaPlayer.stop();
        globalController.switchScene(closeBtn, stackPane, anchorPane, "../views/Login.fxml");
    }
}
