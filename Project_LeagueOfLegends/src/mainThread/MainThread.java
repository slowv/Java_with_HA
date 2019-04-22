package mainThread;

import database.model.Account;
import database.model.ConnectionDb;
import database.seeder.InitSeeder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainThread extends Application {

    public static Account currentLogin = new Account();

    @Override
    public void start(Stage stage) throws Exception {
        // DB
        ConnectionDb connectionDb = new ConnectionDb();
        connectionDb.ConnectionDbMysql();
        // Seeder
        InitSeeder seeder = new InitSeeder();
        seeder.initializableSeeder();

        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("src\\asset_public\\css\\application.css").toExternalForm());
//        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Montserrat&subset=vietnamese");
        stage.setScene(scene);

        // Ẩn thanh công cụ mặc định của app (cái nút thu nhỏ, tắt chương trình).
        stage.initStyle(StageStyle.UNDECORATED);

        // Thêm icon cho app
        stage.getIcons().add(new Image(new FileInputStream("src\\asset_public\\image\\icon_application.png")));

        // Cho phép thay đổi kích thước
        stage.setResizable(true);

        stage.show();

        // TEST CALL API BY JAVAFX


        // String keyword = "Buianhtuan";
        // keyword.split("\\s+");

        // System.out.println(keyword);
        String keyword = "BuiAnhTuan";
        URL url = new URL("https://content.googleapis.com/youtube/v3/search?q=" + keyword + "&type=video&maxResults=9&part=snippet&key=AIzaSyBfaWEIuvQuHlKqM3d0h8blst_xRREA3iw");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type", "application/json");
        con.setUseCaches(false);
        con.setDoOutput(true);

        InputStream inputStream = con.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
//            response.append("\r");
        }
        bufferedReader.close();
        System.out.println(response.toString());
        System.out.println(con.getResponseCode());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
