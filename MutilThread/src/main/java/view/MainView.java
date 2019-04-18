package view;

import controller.CloneData;
import controller.CloneDataController;
import thread.ThreadCloneData;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {
    public void menu() throws SQLException {
        Runnable thread = null;
        CloneData controller = new CloneDataController();

        Scanner sc = new Scanner(System.in);
        System.out.println("------------ Lay du lieu ------------");
        System.out.println("1. VnExpress \t \t \t \t 2. Cafebiz");
        System.out.println("3. Thoat");
        System.out.println("Nhap lua chon cua ban: ");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                thread = new ThreadCloneData("https://vnexpress.net");
                break;
            case 2:
                thread = new ThreadCloneData("http://cafebiz.vn");
                break;
            case 3:
                System.out.println("Tam biet!");
                break;
            default:
                System.out.println("Lua chon sai! vui long chon lai.");
                break;
        }

        if (choice == 3){
            System.exit(1);
        }
        if (thread != null){
            controller.cloneData(thread);
        }
    }
}
