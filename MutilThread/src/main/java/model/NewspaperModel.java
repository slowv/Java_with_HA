package model;

import entity.Newspaper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class NewspaperModel {
    ConnectionDb db;
    Connection cnn;
    private String tableName = "";
    public NewspaperModel() throws SQLException {
        db = ConnectionDb.getInstance();
        cnn = db.getConnection();
    }

    public void truncateDb(){
        try {
            PreparedStatement stt = cnn.prepareStatement("truncate table  `newspaperclone`");
            stt.execute();
            System.out.println("Truncate thanh cong!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistUrl(String url){
        try {
            PreparedStatement stt = cnn.prepareStatement("SELECT `url` from `newspaperclone` where `url` = ?");
            stt.setString(1, url);
            ResultSet resultSet = stt.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Newspaper newspaper){
        if (!checkExistUrl(newspaper.getUrl())){
            try {
                PreparedStatement stt = cnn.prepareStatement("INSERT into `newspaperclone` (`title`, `url`, `index`) value (?, ?, ?)");
                stt.setString(1, newspaper.getTilte());
                stt.setString(2, newspaper.getUrl());
                stt.setInt(3, newspaper.getIndex());
                System.out.println(stt);
                System.out.println(newspaper);
                stt.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
