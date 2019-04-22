package database.model;

import controller.GlobalController;
import controller.HomeController;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static controller.HomeController.*;
import static mainThread.MainThread.currentLogin;

@Entity
@Table(name = "Account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Account {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

    public Account() {
        long now = System.currentTimeMillis();
        this.created_at = now;
        this.updated_at = now;
        this.status = AccountStatus.ACTIVE.toInt();
    }

    public Account(String username, String password, String email, String phone) {
        long now = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.created_at = now;
        this.updated_at = now;
        this.status = AccountStatus.ACTIVE.toInt();
    }

    public Account(String id, String username, String password, String email, long created_at, String phone, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.phone = phone;
        this.status = status;
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

    public boolean checkLogin() {
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        Account result = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where username = :username");
            query.setMaxResults(1);
            query.setParameter("username", this.username);
            result = (Account) query.uniqueResult();

            if (result != null) {
                if (result.getUsername().equals(this.username) && result.getPassword().equals(this.password)) {
                    currentLogin.setId(result.getId());
                    currentLogin.setUsername(result.getUsername());
                    currentLogin.setPassword(result.getPassword());
                    currentLogin.setEmail(result.getEmail());
                    currentLogin.setCreated_at(result.getCreated_at());
                    currentLogin.setPhone(result.getPhone());
                    currentLogin.setStatus(result.getStatus());
                    System.out.println(currentLogin.toString());
                    return true;
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean insert(Account account, Text titleRegister) {
        Session session = GlobalController.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            currentLogin.setId(account.getId());
            currentLogin.setUsername(account.getUsername());
            currentLogin.setPassword(account.getPassword());
            currentLogin.setEmail(account.getEmail());
            currentLogin.setCreated_at(account.getCreated_at());
            currentLogin.setPhone(account.getPhone());
            currentLogin.setStatus(account.getStatus());
            titleRegister.setText("Đăng ký thành công!");
            titleRegister.setFill(Color.web("#59A869"));
            System.out.println("Đăng ký thành công!");
            return true;

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
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

enum AccountStatus {
    ACTIVE(1), DEACTIVE(0), DELETED(-1);
    private int code;

    AccountStatus(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }
}


