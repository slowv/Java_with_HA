package view;

import controller.ApplicationController;
import utility.Error;

import java.util.Scanner;

public class CategoryView implements GenerateMenu {
    private static Error error = new Error();
    @Override
    public void menu(ApplicationController controller) {
        boolean isLoop = false;
        Scanner sc = new Scanner(System.in);
        while (!isLoop) {
            System.out.println("---------------------- Quản lý danh mục -------------------\n");
            System.out.println("1. Thêm danh mục \t \t \t \t \t 2. Danh sách danh mục");
            System.out.println("3. Sửa danh mục \t \t \t \t \t 4. Xóa danh mục");
            System.out.println("5. Tìm kiếm \t \t \t \t \t \t 6. Quay lại");
            isLoop = isLoop(controller, isLoop, sc);

        }
    }

    static boolean isLoop(ApplicationController controller, boolean isLoop, Scanner sc) {
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
                error.alertErrorChoice("(1|2|3|4|5)");
                System.out.println("Nhập lựa chọn của bạn: ");
                break;
        }
        return isLoop;
    }
}
