package controller;

import enity.Category;
import enity.Food;
import utility.AlertSuccessString;
import utility.Confirm;
import utility.Error;
import utility.Number;
import view.MainView;

import java.util.ArrayList;
import java.util.Scanner;

import static controller.CategoryController.categoryArrayList;

public class FoodController implements ApplicationController {

    private String entityName = "món ăn";
    static ArrayList<Food> foodArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private AlertSuccessString alertSuccessString = new AlertSuccessString();
    private Error error = new Error();
    private Confirm confirm = new Confirm();
    private Number number = new Number();

    @Override
    public void create() {
        if (!categoryArrayList.isEmpty()){
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
                String content = category.tempoListCategory(categoryArrayList);
                if (content.isEmpty()){
                    content = "Hiện không có danh mục nào!";
                }
                System.out.println(content);
                System.out.println("Nhập mã danh mục: ");
                String codeCategory = sc.nextLine();
                category.setCode(codeCategory);

                boolean checkExistCode = category.checkExistCode(categoryArrayList);
                codeCategory = getString(codeCategory, category, checkExistCode);
                Food food = new Food(name, description, price, category.getCode(), code);
//                System.out.println(food);
                foodArrayList.add(food);
//                System.out.println(Arrays.toString(foodArrayList.toArray()));
//                return;
//
                alertSuccessString.alertSuccess("Thêm mới", entityName);
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
            new MainView().menu();
        }
    }

    @Override
    public void readList() {
        String content ="";
        if (!foodArrayList.isEmpty() && !categoryArrayList.isEmpty()){
            content = new Food().printList(foodArrayList, categoryArrayList);
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
        boolean isNumberResult;
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = sc.nextLine();
            Food foodResult = new Food().findFood(foodArrayList, code);
            if (!categoryArrayList.isEmpty()){
                while (true){
                    String choice;

                    System.out.println(foodResult.toString(categoryArrayList, foodResult.getCategoryId()));
                    confirm.confirmString(foodResult.getName(), "sửa", entityName);
                    choice = sc.nextLine();
                    isNumberResult = number.checkIsNumber(choice);

                    while (!isNumberResult) {
                        System.out.println(foodResult.toString(categoryArrayList, foodResult.getCategoryId()));
                        error.alertErrorNumberFormat("(1|2)");
                        confirm.confirmString(foodResult.getName(), "sửa", entityName);
                        choice = sc.nextLine();
                        isNumberResult = number.checkIsNumber(choice);
                    }

                    switch (Integer.parseInt(choice)){
                        case 1:
                            System.out.println("Tên món ăn mới(ấn S để bỏ qua không cập nhật tên món ăn): ");
                            String nameUpdate = sc.nextLine();
                            System.out.println("Mô tả món ăn mới(ấn S để bỏ qua không cập nhật mô tả món ăn): ");
                            String descriptionUpdate = sc.nextLine();
                            System.out.println("Giá món ăn mới(ấn 0 để bỏ qua không cập nhật giá món ăn): ");
                            double priceUpdate = sc.nextDouble();
                            sc.nextLine();
                            String content = new Category().tempoListCategory(categoryArrayList);
                            System.out.println(content);
                            System.out.println("Mã danh mục mới(ấn S để bỏ qua không cập nhật mã danh mục món ăn): ");
                            String categoryIdUpdate = sc.nextLine();

                            if (nameUpdate.equals("S") && descriptionUpdate.equals("S") && priceUpdate == 0 && categoryIdUpdate.equals("S")){
                                System.out.println("Bịp à ko update cái gì thì vào đây làm gì?\n");
                                return;
                            }

                            if (descriptionUpdate.equals("S")) descriptionUpdate = foodResult.getDescription();
                            if (nameUpdate.equals("S")) nameUpdate = foodResult.getName();
                            if (priceUpdate == 0) priceUpdate = foodResult.getPrice();
                            if (categoryIdUpdate.equals("S")) categoryIdUpdate = foodResult.getCategoryId();

                            Category category = new Category();
                            category.setCode(categoryIdUpdate);
                            boolean checkExistCode = category.checkExistCode(categoryArrayList);
                            categoryIdUpdate = getString(categoryIdUpdate, category, checkExistCode);

                            Food food = new Food(nameUpdate, descriptionUpdate, priceUpdate, categoryIdUpdate, foodResult.getCode());

                            for(Food index : foodArrayList){
                                if (food.getCode().equals(index.getCode())){
                                    index.setName(food.getName());
                                    index.setDescription(food.getDescription());
                                    index.setPrice(food.getPrice());
                                    index.setCode(food.getCode());
                                }
                            }
                            alertSuccessString.alertSuccess("Sửa", entityName);
                            return;
                        case 2:
                            return;
                        default:
                            error.alertErrorChoice("(1|2)");
                            break;
                    }
                }
            }else System.out.println("Hiện không có danh mục nào nên sẽ không thể tim kiếm món ăn!");
        }else error.alertErrorIsEmpty(entityName);
    }

    private String getString(String categoryIdUpdate, Category category, boolean checkExistCode) {
        String content;
        if (!checkExistCode){
            while (!checkExistCode){
                content = category.tempoListCategory(categoryArrayList);
                System.out.println(content);
                System.out.println("Mã danh mục không tồn tại, vui lòng nhập lại: ");
                System.out.println("Nhập mã danh mục");
                categoryIdUpdate = sc.nextLine();
                category.setCode(categoryIdUpdate);
                checkExistCode = category.checkExistCode(categoryArrayList);
            }
        }
        return categoryIdUpdate;
    }

    @Override
    public void destroy() {
        boolean isNumberResult;
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = sc.nextLine();
            Food foodResult = new Food().findFood(foodArrayList, code);
            if (!categoryArrayList.isEmpty()){
                while (true){
                    String choice;
                    System.out.println(foodResult.toString(categoryArrayList, foodResult.getCategoryId()));
                    confirm.confirmString(foodResult.getName(),"xóa", entityName);
                    choice = sc.nextLine();
                    isNumberResult = number.checkIsNumber(choice);

                    while (!isNumberResult) {
                        System.out.println(foodResult.toString(categoryArrayList, foodResult.getCategoryId()));
                        error.alertErrorNumberFormat("(1|2)");
                        confirm.confirmString(foodResult.getName(), "xóa", entityName);
                        choice = sc.nextLine();
                        isNumberResult = number.checkIsNumber(choice);
                    }

                    switch (Integer.parseInt(choice)){
                        case 1:
                            for (int i = 0; i < foodArrayList.size(); i++) {
                                if (foodResult.getCode().equals(foodArrayList.get(i).getCode())){
                                    foodArrayList.remove(i);
                                }
                            }
                            alertSuccessString.alertSuccess("Thêm mới", entityName);
                            System.out.println("<=== Ấn enter để ra màn hình chính.");
                            sc.nextLine();
                            return;
                        case 2:
                            return;
                        default:
                            error.alertErrorChoice("(1|2)");
                            break;
                    }
                }
            }else System.out.println("Hiện không có danh mục nào nên sẽ không có món ăn!");
        }else error.alertErrorIsEmpty(entityName);
    }

    @Override
    public void find() {
        if (!foodArrayList.isEmpty()){
            System.out.println("Vui lòng nhập mã món ăn: ");
            String code = sc.nextLine();
            Food food = new Food();
            Food foodResult = food.findFood(foodArrayList, code);
            if (!categoryArrayList.isEmpty()){
                System.out.println("Kết quả tìm kiếm mới mã món ăn là: " + code);
                System.out.println(foodResult.toString(categoryArrayList, foodResult.getCategoryId()));
            }else System.out.println("Hiện không có danh mục nào nên sẽ không có món ăn!");
        }else error.alertErrorIsEmpty(entityName);
    }
}
