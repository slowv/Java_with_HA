package controller;

import enity.Category;
import enity.Food;
import view.MainView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FoodController implements ApplicationController {

    public static ArrayList<Food> foodArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void create() {
        if (!CategoryController.categoryArrayList.isEmpty()){
            boolean isLoop = false;
            int count = 0;
            Category category = new Category();
            while (!isLoop) {
                System.out.printf("Món ăn thứ: %d \n", count + 1);
                System.out.println("--------------*--------------");
                System.out.println("Nhập mã món ăn: ");
                String code = sc.nextLine();
                System.out.println("Nhập tên món ăn: ");
                String name = sc.nextLine();
                System.out.println("Nhập mô tả món ăn: ");
                String description = sc.nextLine();
                System.out.println("Nhập giá món ăn: ");
                double price = sc.nextDouble();
                sc.nextLine();
                String content = category.tempoListCategory(CategoryController.categoryArrayList);
                if (content.isEmpty()){
                    content = "Hiện không có danh mục nào!";
                }
                System.out.println(content);
                System.out.println("Nhập mã danh mục: ");
                String codeCategory = sc.nextLine();
                category.setCode(codeCategory);

                boolean checkExistCode = category.checkExistCode(CategoryController.categoryArrayList);
                if (!checkExistCode){
                    while (!checkExistCode){
                        content = category.tempoListCategory(CategoryController.categoryArrayList);
                        System.out.println(content);
                        System.out.println("Mã danh mục không tồn tại, vui lòng nhập lại: ");
                        System.out.println("Nhập mã danh mục");
                        codeCategory = sc.nextLine();
                        category.setCode(codeCategory);
                        checkExistCode = category.checkExistCode(CategoryController.categoryArrayList);
                    }
                }
                Food food = new Food(name, description, price, category.getCode(), code);
//                System.out.println(food);
                foodArrayList.add(food);
//                System.out.println(Arrays.toString(foodArrayList.toArray()));
//                return;
//
                System.out.println("Thêm món ăn `" + name + "` thành công!");
                System.out.println("Ấn enter để tiếp tục. Nhập HUY để trở ra màn hình chính.");
                String choice = sc.nextLine();

                if (choice.equals("HUY")) {
                    isLoop = true;
                } else {
                    System.out.println("Tiếp tục thêm món ăn! \n");
                }
                count++;

            }
        }else{
            System.out.println("Vui lòng tạo danh mục món ăn trước khi tạo món ăn!");
            MainView view =  new MainView();
            view.menu();
            return;
        }
    }

    @Override
    public void readList() {
        Food food = new Food();
        String content ="";
        if (!foodArrayList.isEmpty() && !CategoryController.categoryArrayList.isEmpty()){
            content = food.printList(foodArrayList, CategoryController.categoryArrayList);
        }

        if (content.isEmpty()){
            content = "Hiện không có món ăn nào!";
        }
        System.out.println(content);
        System.out.println("<==== Ấn enter để quay lại.");
        sc.nextLine();
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = sc.nextLine();
            Food food = new Food();
            Food foodResult = food.findFood(foodArrayList, code);
            if (!CategoryController.categoryArrayList.isEmpty()){
                System.out.println(foodResult.toString(CategoryController.categoryArrayList, foodResult.getCategoryId()));
                while (true){
                    System.out.println("Bạn có chắc muốn xóa món ăn: " + foodResult.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            for (int i = 0; i < foodArrayList.size(); i++) {
                                if (foodResult.getCode().equals(foodArrayList.get(i).getCode())){
                                    foodArrayList.remove(i);
                                }
                            }
                            System.out.println("Xóa món ăn `" + foodResult.getName() +"` thành công!");
                            System.out.println("<=== Ấn enter để ra màn hình chính.");
                            sc.nextLine();
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Lựa chọn sai, vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            }else{
                System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
                return;
            }
        }else{
            System.out.println("Hiện tại không có món ăn nào!");
        }
    }

    @Override
    public void find() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = sc.nextLine();
            Food food = new Food();
            Food foodResult = food.findFood(foodArrayList, code);
            if (!CategoryController.categoryArrayList.isEmpty()){
                System.out.println("Kết quả tìm kiếm mới mã món ăn là: " + code);
                System.out.println(foodResult.toString(CategoryController.categoryArrayList, foodResult.getCategoryId()));
            }else{
                System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
                return;
            }
        }else {
            System.out.println("Hiện không có món ăn nào!");
        }
    }
}
