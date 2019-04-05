package view;

import controller.EmployeeController;
import sun.invoke.empty.Empty;

import java.util.Scanner;

public class EmployeeMenu {
    private Scanner sc = new Scanner(System.in);
    private EmployeeController controller =  new EmployeeController();
    public void menu() {
        while (true) {
            System.out.println("------ * ------\n");
            System.out.println("1. Đăng nhập \t \t \t 2. Đăng ký");
            System.out.println("3. Thoát");
            System.out.println("------ * ------\n");
            System.out.println("Lựa chọn của bạn là: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    controller.doLogin();
                    break;
                case 2:
                    controller.doRegister();
                    break;
                case 3:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn sai vui lòng chọn lại!");
                    break;
            }
            if (choice == 3){
                System.exit(1);
            }
        }
    }
}
