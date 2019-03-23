package view;

import controller.ApplicationController;

import java.util.Scanner;

public class FoodView implements GenerateMenu{
    @Override
    public void menu(ApplicationController controller) {
        boolean isLoop = false;
        Scanner sc = new Scanner(System.in);
        while (!isLoop){
            System.out.println("---------------------- Quản lý món ăn -------------------\n");
            System.out.println("1. Thêm món ăn \t \t \t \t \t 2. Danh sách món ăn");
            System.out.println("3. Sửa món ăn \t \t \t \t \t 4. Xóa món ăn");
            System.out.println("5. Tìm kiếm \t \t \t \t \t 6. Quay lại");
            System.out.println("------------------------------*-------------------------------\n");

            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    controller.create();
                    break;
                case 2:
                    controller.readList();
                    break;
                case 3:
                    controller.update();
                    break;
                case 4:
                    controller.destroy();
                    break;
                case 5:
                    controller.find();
                    break;
                case 6:
                    isLoop = true;
                    break;
                default:
                    System.out.println("Lựa chọn sai vui lòng chọn (1|2|3|4|5).\n");
                    System.out.println("Nhập lựa chọn của bạn: ");
                    break;
            }
        }
    }
}
