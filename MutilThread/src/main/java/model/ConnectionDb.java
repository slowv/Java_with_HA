package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private final String url = "jdbc:mysql://localhost/newspaper?useUnicode=true&characterEncoding=utf-8";
    private Connection connection;
    private static ConnectionDb instance;
    private String user = "root";
    private String password = "";

    private ConnectionDb() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException ex){
            System.out.println("Ket noi Db khong thanh cong : " + ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConnectionDb getInstance() throws SQLException {
        if (instance == null  || instance.getConnection().isClosed()){
            instance = new ConnectionDb();
        }
        return instance;
    }



}
