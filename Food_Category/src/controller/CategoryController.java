package controller;

import enity.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryController implements ApplicationController {

    public static ArrayList<Category> categoryArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

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
            if (!categoryArrayList.isEmpty()){
                boolean checkExistCode = category.checkExistCode(categoryArrayList);

                if(checkExistCode){
                    while (checkExistCode){
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

            System.out.println("Thêm danh mục thành công!\n");
            System.out.println("Ấn enter để tiếp tục. Nhập HUY để trở ra màn hình danh mục.");
            String choice = sc.nextLine();

            if (choice.equals("HUY")) {
                isLoop = true;
            } else {
                System.out.println("Tiếp tục thêm danh mục! \n");
            }
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
        if (!categoryArrayList.isEmpty()){

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
        }else{
            System.out.println("Hiện không có danh mục nào!");
        }
        System.out.println("<====  Ấn enter để quay lại!");
        sc.nextLine();
    }

    @Override
    public void update() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true){
                    System.out.println(category.displayOneCategory());
                    System.out.printf("Bạn có chắc muốn sửa danh mục: %s \n", category.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choiceRemove = sc.nextInt();
                    sc.nextLine();
                    switch (choiceRemove) {
                        case 1:
                            System.out.println("Mã danh mục mới(ấn S để bỏ qua không cập nhật mã danh mục): ");
                            String codeUpdate = sc.nextLine();

                            System.out.println("Tên danh mục mới(ấn S để bỏ qua không cập nhật tên danh mục): ");
                            String nameUpdate = sc.nextLine();

                            System.out.println("Mô tả danh mục mới(ấn S để bỏ qua không cập nhật mô tả danh mục): ");
                            String descriptionUpdate = sc.nextLine();

                            if (codeUpdate.equals("S") && nameUpdate.equals("S") && descriptionUpdate.equals("S")){
                                System.out.println("Bịp à ko update cái gì thì vào đây làm gì?");
                                return;
                            }

                            if (codeUpdate.equals("S")) codeUpdate = category.getCode();
                            if (descriptionUpdate.equals("S")) descriptionUpdate = category.getDescription();
                            if (nameUpdate.equals("S")) nameUpdate = category.getName();

                            Category categoryUpdate = new Category(codeUpdate, nameUpdate, descriptionUpdate);

                            boolean checkExistCode = categoryUpdate.checkExistCode(categoryArrayList);

                            if (checkExistCode){
                                while (checkExistCode){
                                    System.out.println("Mã danh mục đã tồn tại! Vui lòng nhập mã khác.\n");
                                    System.out.println("Nhập mã danh mục mới hoặc ấn HUY để quay lại: ");
                                    codeUpdate = sc.nextLine();

                                    if (codeUpdate.equals("HUY")) return;

                                    categoryUpdate.setCode(codeUpdate);
                                    checkExistCode = categoryUpdate.checkExistCode(categoryArrayList);
                                }
                            }

                            if (category.getCode().isEmpty()){
                                System.out.println("Danh mục bạn muốn sửa đã không còn tồn tại, có thể do ai đó đã xóa hoặc sửa trước đó.");
                                return;
                            }

                            for (Category item : categoryArrayList) {
                                if (category.getCode().equals(item.getCode())){
                                    item.setCode(categoryUpdate.getCode());
                                    item.setName(categoryUpdate.getName());
                                    item.setDescription(categoryUpdate.getDescription());
                                }
                            }

                            System.out.println("Sửa thành công!");
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            } else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }
    }

    @Override
    public void destroy() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                while (true){
                    System.out.println(category.displayOneCategory());
                    System.out.printf("Bạn có chắc muốn xóa danh mục: %s \n", category.getName());
                    System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại");
                    int choiceRemove = sc.nextInt();

                    switch (choiceRemove) {
                        case 1:
                            for (int i = 0; i < categoryArrayList.size(); i++) {
                                if (!category.getCode().isEmpty()){
                                    if (category.getCode().equals(categoryArrayList.get(i).getCode())){
                                        categoryArrayList.remove(i);
                                    }
                                }
                            }
                            System.out.println("Xóa thành công!");
                            return;
                        case 2:
                            return;
                        default:
                            System.out.println("Vui lòng chọn 1 hoặc 2.");
                            break;
                    }
                }
            } else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }
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
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }
    }
}
