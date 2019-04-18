package crawler.cafebiz;

import enity.NewSpaper;
import thread.CloneNewspaper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainThread2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("------------------- Lay tin ve -------------------");
        System.out.println("1. VnExpress \t \t \t \t 2. Cafebiz");
        System.out.println("3. Thoat");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                System.out.println("VnExpress");
                break;
            case 2:

                CloneNewspaper thread = new CloneNewspaper("http://cafebiz.vn", "a");
                thread.start();
                break;
            case 3:
                System.out.println("Tam biet!");
                System.exit(1);
                break;
            default:
                System.out.println("Lua chon sai! vui long chon lai.");
                break;
        }
    }
}
