package controller;

import entity.Employee;
import model.EmployeeModel;

import java.util.Scanner;

public class EmployeeController {
    private Scanner sc = new Scanner(System.in);
    private EmployeeModel model = new EmployeeModel();

    public void doRegister() {
        String account;
        System.out.println("--------- ĐĂNG KÝ ---------\n");
        System.out.println("Họ và tên:");
        String fullName = sc.nextLine();
        System.out.println("Địa chỉ:");
        String address = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Tên tài khoản:");
        account = sc.nextLine();

        while (model.checkExistAccount(account)) {
            System.err.println("Tên tài khoản đã tồn tại!");
            System.out.println("Nhập tên tài khoản!");
            account = sc.nextLine();
        }

        System.out.println("Mật khẩu");
        String password = sc.nextLine();

        Employee emp = new Employee(fullName, address, email, account, password);

        emp.displayEmployee();
        System.out.println("");
        System.out.println("Bạn muốn tạo tài khoản với những thông tin trên ?");
        System.out.println("1 Đồng ý \t \t \t \t 2. Không");
        int choice = sc.nextInt();
        boolean checkRegisterSuccess = false;
        switch (choice) {
            case 1:
                checkRegisterSuccess = model.register(emp);
                break;
            case 2:
                return;
            default:
                System.out.println("Lựa chọn sai vui lòng chọn lại");
                break;
        }

        if (!checkRegisterSuccess) {
            System.out.println("Có lỗi sảy ra khổng thể đăng ký, vui lòng thử lại sau.");
            return;
        }

        System.out.println("Đăng ký thành công!");
        System.out.println("<======== Ấn enter để quay lại!");
        sc.nextLine();
    }

    public void doLogin() {
        System.out.println("Tài khoản:");
        String account = sc.nextLine();
        System.out.println("Mật khẩu:");
        String password = sc.nextLine();

        Employee emp = model.login(account, password);
        if (emp == null) {
            System.err.println("Tài khoản hoặc mật khẩu không đúng, vui lòng thử lại!");
            return;
        }
        System.out.println("------- Đăng nhập thành công! --------");
        System.out.println("Xin chào " + emp.getFullName() + " !\n");
        emp.displayEmployee();
        sc.nextLine();
    }
}
