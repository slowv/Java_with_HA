package model;

import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    private Connection cnn = null;
    private void initConnection(){
        try {
            if (cnn == null || cnn.isClosed()){
                ConnectionDb Db = new ConnectionDb();
                cnn = Db.connection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean register(Employee emp){
        initConnection();
        try {
            PreparedStatement stt = cnn.prepareStatement(emp.sqlInsert);
            stt.setString(1, emp.getId());
            stt.setString(2, emp.getFullName());
            stt.setString(3, emp.getAddress());
            stt.setString(4, emp.getEmail());
            stt.setString(5, emp.getAccount());
            stt.setString(6, emp.getPassword());
            stt.setLong(7, emp.getCreated_at());
            stt.setLong(8, emp.getUpdated_at());
            stt.setInt(9, emp.getStatus());
            stt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkExistAccount(String account){
        initConnection();
        Employee employee = new Employee();
        try {
            PreparedStatement stt = cnn.prepareStatement(employee.sqlSelectByAccount);
            stt.setString(1, account);
            ResultSet rs =  stt.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee login(String account, String password){
        initConnection();
        Employee employee = new Employee();
        try {
            PreparedStatement stt = cnn.prepareStatement(employee.sqlSelectByAccountAndPassword);
            stt.setString(1, account);
            stt.setString(2, password);
            ResultSet rs =  stt.executeQuery();
            if (rs.next()){
                employee.setId(rs.getString(1));
                employee.setFullName(rs.getString(2));
                employee.setAddress(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setAccount(rs.getString(5));
                employee.setPassword(rs.getString(6));
                employee.setCreated_at(rs.getLong(7));
                employee.setUpdated_at(rs.getLong(8));
                employee.setStatus(rs.getInt(9));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
