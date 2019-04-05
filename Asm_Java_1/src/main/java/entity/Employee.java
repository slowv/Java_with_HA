package entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

public class Employee {
    private String id;
    private String fullName;
    private String address;
    private String email;
    private String account;
    private String password;
    private long created_at;
    private long updated_at;
    private int status;


    public Employee() {
    }

    public Employee(String fullName, String address, String email, String account, String password) {
        long now = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.account = account;
        this.password = password;
        this.created_at = now;
        this.updated_at = now;
        this.status = EmployeeStatus.ACTIVE.getInt();
    }

    public void displayEmployee(){
        System.out.printf("%-20s| %-30s| %-40s| %-20s| %-20s| %-20s| %-20s \n", "Họ và tên", "Địa chỉ", "Email", "Tài khoản", "Mật khẩu", "Ngày tạo", "Trạng thái");
        System.out.printf("%-20s| %-30s| %-40s| %-20s| %-20s| %-20s| %-20s \n",
                this.fullName, this.address, this.email, this.account, this.password, convertMiliToDate(this.created_at), EmployeeStatus.getStatusByValue(this.status));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    private String convertMiliToDate(long value){
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(value);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);


        return mDay + "-" + mMonth + "-" + mYear;
    }

    public enum EmployeeStatus {
        ACTIVE(1), DEACTIVE(0);
        private int number;

        EmployeeStatus(int number){
            this.number = number;
        }

        public int getInt(){
            return number;
        }

        public static EmployeeStatus getStatusByValue(int value) {
            for (EmployeeStatus l : EmployeeStatus.values()) {
                if (l.number == value) return l;
            }
            throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
        }
    }

    public String sqlInsert = "insert into human_resource (id, fullName, address, email, account, password, created_at, updated_at, status) " +
            "value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public String sqlSelectByAccount = "select account from human_resource where account = ? ";
    public String sqlSelectByAccountAndPassword = "select * from human_resource where account = ? and password = ?";
}



