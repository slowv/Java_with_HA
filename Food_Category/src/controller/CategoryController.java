package controller;

import enity.Category;
import enity.Food;
import utility.AlertSuccessString;
import utility.Error;
import utility.Number;
import utility.Confirm;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryController implements ApplicationController {

    private String entityName = "danh mục";
    static ArrayList<Category> categoryArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Number number = new Number();
    private Confirm confirmString = new Confirm();
    private Error error = new Error();
    private AlertSuccessString alertSuccessString = new AlertSuccessString();

    @Override
    public void create() {
        boolean isLoop = false;
        int count = 0;
        while (!isLoop) {
            System.out.printf("Danh mục thứ: %d \n", count + 1);
            System.out.println("--------------*--------------");
            System.out.println("Nhập mã danh mục: ");
            String code = sc.nextLine();
            System.out.println("Nhập tên danh mục: ");
            String name = sc.nextLine();
            System.out.println("Nhập mô tả danh mục: ");
            String description = sc.nextLine();

            Category category = new Category(code, name, description);
            if (!categoryArrayList.isEmpty()) {
                boolean checkExistCode = category.checkExistCode(categoryArrayList);

                if (checkExistCode) {
                    while (checkExistCode) {
                        System.out.println("Mã danh mục đã tồn tại! Vui lòng nhập mã khác.\n");
                        System.out.println("Nhập mã danh mục mới hoặc ấn HUY để quay lại: ");
                        code = sc.nextLine();

                        if (code.equals("HUY")) return;

                        category.setCode(code);
                        checkExistCode = category.checkExistCode(categoryArrayList);
                    }
                }
            }

            categoryArrayList.add(category);

            alertSuccessString.alertSuccess("Thêm mới", entityName);
            System.out.println("Ấn enter để tiếp tục. Nhập HUY để trở ra màn hình danh mục.");
            String choice = sc.nextLine();

            if (choice.equals("HUY")) {
                isLoop = true;
            } else System.out.println("Tiếp tục thêm danh mục! \n");
            count++;

        }
    }

    @Override
    public void readList() {
//        if (categoryArrayList.isEmpty()) {
//            categoryArrayList.add(new Category("A001", "Món ngon Hà Nội", "Tất cả món ăn ngon ở Hà Nội"));
//            categoryArrayList.add(new Category("A002", "Món ngon Huế", "Tất cả món ăn ngon ở Huế"));
//            categoryArrayList.add(new Category("A003", "Món ngon Nha Trang", "Tất cả món ăn ngon ở Nha Trang"));
//            categoryArrayList.add(new Category("A004", "Món ngon Đà Nẵng", "Tất cả món ăn ngon ở Đà Nẵng"));
//            categoryArrayList.add(new Category("A005", "Món ngon Sài Gòn", "Tất cả món ăn ngon ở Sài Gòn"));
//        }
        if (!categoryArrayList.isEmpty()) {

            System.out.println("Tổng số danh mục: " + categoryArrayList.size() + "\t \t \t \t \t \t \t \t \t DANH SÁCH DANH MỤC\n");
            System.out.println("------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
            System.out.println(String.format(" %-9s|", "STT")
                    + String.format(" %-45s|", " ID")
                    + String.format(" %-20s|", " Tên")
                    + String.format(" %-20s|", " Mã")
                    + String.format(" %-30s", " Mô tả"));
            System.out.println("------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
            for (int i = 0; i < categoryArrayList.size(); i++) {
                System.out.println(String.format(" %-9d| ", i + 1)
                        + String.format(" %-44s| ", categoryArrayList.get(i).getId())
                        + String.format(" %-19s| ", categoryArrayList.get(i).getName())
                        + String.format(" %-19s| ", categoryArrayList.get(i).getCode())
                        + String.format(" %-30s", categoryArrayList.get(i).getDescription()));
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        } else error.alertErrorIsEmpty(entityName);
        System.out.println("<====  Ấn enter để quay lại!");
        sc.nextLine();
    }

    @Override
    public void update() {
        Category category = new Category();
        boolean isNumberResult;
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true) {
                    String choiceUpdate;
                    System.out.println(category.displayOneCategory());
                    confirmString.confirmString(category.getName(), "sửa", entityName);
                    choiceUpdate = sc.nextLine();
                    isNumberResult = number.checkIsNumber(choiceUpdate);

                    while (!isNumberResult) {
                        System.out.println(category.displayOneCategory());
                        error.alertErrorNumberFormat("(1|2)");
                        confirmString.confirmString(category.getName(), "sửa", entityName);
                        choiceUpdate = sc.nextLine();
                        isNumberResult = number.checkIsNumber(choiceUpdate);
                    }
                    switch (Integer.parseInt(choiceUpdate)) {
                        case 1:
                            System.out.println("Tên danh mục mới(ấn S để bỏ qua không cập nhật tên danh mục): ");
                            String nameUpdate = sc.nextLine();

                            System.out.println("Mô tả danh mục mới(ấn S để bỏ qua không cập nhật mô tả danh mục): ");
                            String descriptionUpdate = sc.nextLine();

                            if (nameUpdate.equals("S") && descriptionUpdate.equals("S")) {
                                System.out.println("Bịp à ko update cái gì thì vào đây làm gì?\n");
                                return;
                            }

                            if (descriptionUpdate.equals("S")) descriptionUpdate = category.getDescription();
                            if (nameUpdate.equals("S")) nameUpdate = category.getName();

                            Category categoryUpdate = new Category(category.getCode(), nameUpdate, descriptionUpdate);

                            for (Category item : categoryArrayList) {
                                if (category.getCode().equals(item.getCode())) {
                                    item.setCode(categoryUpdate.getCode());
                                    item.setName(categoryUpdate.getName());
                                    item.setDescription(categoryUpdate.getDescription());
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
            } else error.alertErrorFindNotFound(entityName, code);

        } else error.alertErrorIsEmpty(entityName);
    }

    @Override
    public void destroy() {
        Category category = new Category();
        boolean isNumberResult;
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true) {
                    String choiceRemove;
                    System.out.println(category.displayOneCategory());
                    confirmString.confirmString(category.getName(), "xóa", entityName);
                    choiceRemove = sc.nextLine();
                    isNumberResult = number.checkIsNumber(choiceRemove);

                    while (!isNumberResult) {
                        System.out.println(category.displayOneCategory());
                        error.alertErrorNumberFormat("(1|2)");
                        confirmString.confirmString(category.getName(), "xóa", entityName);
                        choiceRemove = sc.nextLine();
                        isNumberResult = number.checkIsNumber(choiceRemove);
                    }
                    switch (Integer.parseInt(choiceRemove)) {
                        case 1:
                            if (!FoodController.foodArrayList.isEmpty()) {
                                for (Food itemFood : FoodController.foodArrayList) {
                                    if (category.getCode().equals(itemFood.getCategoryId())) {
                                        System.out.println("Hiện đang có món ăn thuộc danh mục này, nên không thể xóa danh mục này!");
                                        System.out.println("===>> Vui lòng xóa món ăn đó trước!");
                                        return;
                                    }
                                }
                            }
                            for (int i = 0; i < categoryArrayList.size(); i++) {
                                if (!category.getCode().isEmpty()) {
                                    if (category.getCode().equals(categoryArrayList.get(i).getCode())) {
                                        categoryArrayList.remove(i);
                                    }
                                }
                            }
                            alertSuccessString.alertSuccess("Xóa", entityName);
                            return;
                        case 2:
                            return;
                        default:
                            error.alertErrorChoice("(1|2)");
                            break;
                    }
                }
            } else error.alertErrorFindNotFound(entityName, code);

        } else error.alertErrorIsEmpty(entityName);
    }

    @Override
    public void find() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                System.out.println(category.displayOneCategory());
                System.out.println("<======= Ấn enter để quay lại.");
                sc.nextLine();
            } else {
                error.alertErrorFindNotFound(entityName, code);
            }

        } else error.alertErrorIsEmpty(entityName);
    }


}
