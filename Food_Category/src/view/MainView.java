package view;

import controller.ApplicationController;
import controller.CategoryController;
import controller.FoodController;

import java.util.Scanner;

public class MainView{
    public void menu() {
        Scanner sc = new Scanner(System.in);
        ApplicationController controller = null;
        GenerateMenu generateMenu = null;
        while (true){
            System.out.println("----------------- Quản lý nhà hàng -----------------\n");
            System.out.println("1. Món ăn \t \t \t \t \t 2. Danh mục món ăn");
            System.out.println("3. Thoát chương trình\n");
            System.out.println("-------------------------*--------------------------\n");
            System.out.println("Nhập lựa chọn: ");
            int choice = sc.nextInt();

            switch (choice){
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
                    break;
                default:
                    System.out.println("Lựa chọn sai!, vui lòng chọn 1 hoặc 2.");
                    break;
            }

            if (generateMenu != null) {
                generateMenu.menu(controller);
            }
        }
    }
}
