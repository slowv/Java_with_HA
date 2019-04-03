package model;

import controller.GlobalController;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@Table(name = "Account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Account {
    public static Account currentLogin = new Account();

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",  strategy = "uuid2")
    @Column(name = "id", columnDefinition = "varchar(60)")
    private String id;

    @Column
    @NotNull
    private String username;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column(name = "email", unique = true, columnDefinition = "varchar(191)")
    private String email;

//    @Column(columnDefinition = "varchar(10)")
//    @NotNull
//    private String salt;

    @Column
    private long created_at;

    @Column
    private String phone;

    @Column
    private long updated_at;

    @Column
    private long deteled_at;

    @Column
    private int status;

    public enum AccountStatus {
        ACTIVE(1), DEACTIVE(0), DELETED(-1);
        private int code;

        AccountStatus(int code){
            this.code = code;
        }

//        public int toInt() {
//            return code;
//        }

    }

    public Account() {
        long now = System.currentTimeMillis();
        this.created_at = now;
        this.updated_at = now;
        this.status = AccountStatus.ACTIVE.code;
    }

    public Account(String username, String password, String email, String phone) {
        long now = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.created_at = now;
        this.updated_at = now;
        this.status = AccountStatus.ACTIVE.code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", phone='" + phone + '\'' +
                ", updated_at=" + updated_at +
                ", deteled_at=" + deteled_at +
                ", status=" + status +
                '}';
    }

    public boolean checkLogin(){
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        Account result = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where username = :username");
            query.setMaxResults(1);
            query.setParameter("username", this.username);
            result = (Account) query.uniqueResult();

            if (result != null){
                if (result.getUsername().equals(this.username) && result.getPassword().equals(this.password)){
                    return true;
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Account account, Text titleRegister){
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            Account.currentLogin = account;
            titleRegister.setText("Đăng ký thành công!");
            titleRegister.setFill(Color.web("#59A869"));
            System.out.println("Đăng ký thành công!");
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return false;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public long getDeteled_at() {
        return deteled_at;
    }

    public void setDeteled_at(long deteled_at) {
        this.deteled_at = deteled_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


