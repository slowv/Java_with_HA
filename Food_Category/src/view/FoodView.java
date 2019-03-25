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
            isLoop = CategoryView.isLoop(controller, isLoop, sc);
        }
    }
}
