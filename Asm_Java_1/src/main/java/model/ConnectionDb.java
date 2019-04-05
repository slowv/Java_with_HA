package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    public Connection connection(){
        Connection cnn = null;
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost/human_resource?user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }
}
