package controller;

import enity.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryController implements ApplicationController {

    private ArrayList<Category> categoryArrayList = new ArrayList<>();
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
        if (categoryArrayList.isEmpty()) {
            categoryArrayList.add(new Category("A001", "Danh mục 1", "Rất là ngon nếu món ăn ở danh mục này"));
            categoryArrayList.add(new Category("A002", "Danh mục 2", "Rất là ngon nếu món ăn ở danh mục này"));
        }

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
        System.out.println("<====  Ấn enter để quay lại!");
        sc.nextLine();
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void find() {
        Category category = new Category();
        if (!categoryArrayList.isEmpty()) {
            System.out.println("Nhập mã danh mục:");
            String code = sc.nextLine();
            category = category.findCategory(categoryArrayList, code);
            if (category != null) {
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
                System.out.println( String.format(" %-45s|", " ID")
                        + String.format(" %-20s|", " Tên")
                        + String.format(" %-20s|", " Mã")
                        + String.format(" %-30s", " Mô tả"));
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
                System.out.println( String.format(" %-45s| ", category.getId())
                        + String.format(" %-19s| ", category.getName())
                        + String.format(" %-19s| ", category.getCode())
                        + String.format(" %-30s", category.getDescription()));
                System.out.println("------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
                System.out.println("<======= Ấn enter để quay lại.");
                sc.nextLine();
            }else {
                System.out.printf("Không tìm thấy danh mục nào với mã là: %s \n", code);
            }

        } else {
            System.out.println("Hiện tại bạn chưa có danh mục nào!\n");
            return;
        }
    }
}
