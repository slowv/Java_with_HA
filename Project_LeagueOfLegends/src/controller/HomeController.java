package controller;

import com.jfoenix.controls.JFXButton;
import database.seeder.InitSeeder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private Circle avatarUser;
    GlobalController globalController = new GlobalController();

    @FXML
    private JFXButton closeBtn;

    @FXML
    private ImageView slideImageVIew;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Circle cricleOnline;

    @FXML
    private Button lBtn;

    @FXML
    private Button rBtn;

    ArrayList<String> list = new ArrayList<>();
    int j = 0;
    double orgReleaseSceneX, orgCliskSceneX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        avatarUser.setStroke(Color.web("#f1f1f1", 0));
        Image image = new Image(getClass().getResource("../asset_public/image/face.jpg").toExternalForm(), false);
        avatarUser.setFill(new ImagePattern(image));
//        avatarUser.setEffect(new DropShadow(+25d, 2 ,+2d, Color.web("#f1f1f1")));

//        slideImage.setImage(new Image("https://lienminh.garena.vn/images/Lan_h3lpm3/vu%20khi%20toi%20thuong%20600x338.jpg"));
        //Sử lý slide show image
        list.add("https://lienminh.garena.vn/images/Lan_h3lpm3/Shen_PulsefireSkin%20full%20size.jpg");
        list.add("https://lienminh.garena.vn/images/Lan_h3lpm3/Twisted_Fate_PulsefireSkin%20full%20size.jpg");
        list.add("http://gosu.vn/data/images/GOSU_NEWS/2018-3/16/lmht-01.png");
        list.add("https://lienminh.garena.vn/images/00-LR/assets/splash/Jayce_FullMetal.jpg");
        Image images[] = new Image[list.size()];

        for (int i = 0; i < list.size(); i++) {
            images[i] = new Image(list.get(i));
        }

        slideImageVIew.setImage(images[j]);
        slideImageVIew.setPreserveRatio(false);
        slideImageVIew.setOnMousePressed(circleOnMousePressedEventHandler);

        slideImageVIew.setOnMousePressed(e -> {
            orgReleaseSceneX = e.getSceneX();
            if (orgCliskSceneX > orgReleaseSceneX) {
                lBtn.fire();
            } else {
                rBtn.fire();
            }
        });

        lBtn.setOnAction(e -> {
            j = j - 1;
            if (j == 0 || j > list.size() + 1 || j == -1) {
                j = list.size() - 1;
            }
            slideImageVIew.setImage(images[j]);
        });

        rBtn.setOnAction(e -> {
            j = j + 1;
            if (j == list.size()) {
                j = 0;
            }
            slideImageVIew.setImage(images[j]);
        });

        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            rBtn.fire();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgCliskSceneX = t.getSceneX();
        }
    };


    public void minimizeProgram(MouseEvent mouseEvent) {
        globalController.miniProgram(anchorPane);
    }

    public void closeProgram(ActionEvent actionEvent) {
        StackPane stackPane = (StackPane) anchorPane.getScene().getRoot();
        globalController.dialogConfirmExit(stackPane, anchorPane, closeBtn);
    }

    public void onHover(MouseEvent mouseEvent) {
    }
}
