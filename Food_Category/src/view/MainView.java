package view;

import controller.ApplicationController;
import controller.CategoryController;
import controller.FoodController;
import utility.Number;

import java.util.Scanner;

public class MainView{
    public void menu() {
        Scanner sc = new Scanner(System.in);
        ApplicationController controller = null;
        GenerateMenu generateMenu = null;
        Number number = new Number();
        boolean isNumberResult;
        while (true){
            System.out.println("----------------- Quản lý nhà hàng -----------------\n");
            System.out.println("1. Món ăn \t \t \t \t \t 2. Danh mục món ăn");
            System.out.println("3. Thoát chương trình\n");
            System.out.println("-------------------------*--------------------------\n");
            System.out.println("Nhập lựa chọn: ");
            String choice = sc.nextLine();

            isNumberResult = number.checkIsNumber(choice);

            while (!isNumberResult){
                System.out.println("Bạn đã nhập sai định dạng số! vui lòng nhập lại!\n");
                System.out.println("Nhập lại lựa chọn của bạn(1|2|3): ");
                choice = sc.nextLine();
                isNumberResult = number.checkIsNumber(choice);
            }

            switch (Integer.parseInt(choice)){
                case 1:
                    controller =  new FoodController();
                    generateMenu = new FoodView();
                    break;
                case 2:
                    controller = new CategoryController();
                    generateMenu = new CategoryView();
                    break;
                case 3:
                    System.out.println("Tạm biệt, hẹn gặp lại!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn sai!, vui lòng chọn (1|2|3.");
                    break;
            }

            if (generateMenu != null) {
                generateMenu.menu(controller);
            }
        }
    }
}
