import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainThread {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        Scanner sc = new Scanner(System.in);
        String fileName = "src/main/resources/StudentList.txt";
        while (true) {
            System.out.println("1. Them sinh vien");
            System.out.println("2. Lưu sinh vien");
            System.out.println("3. Danh sach sinh vien.");
            System.out.println("4. Thoat");
            System.out.println("Nhap lua chon cua bạn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên:");
                    String name = sc.nextLine();
                    System.out.println("Nhập mã sinh viên");
                    String rollNumber = sc.nextLine();
                    Student student = new Student(rollNumber, name);
                    studentArrayList.add(student);
                    System.out.println("Thêm sinh viên thành công!");
                    break;
                case 2:
                    String str = "";
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    for (Student st : studentArrayList) {
                        str += st.getName() + " | " + st.getRollNumber() + "\n";
                    }
                    writer.write(str);
                    writer.close();
                    System.out.println("Luu thanh cong!");
                    break;
                case 3:
                    String line = null;
                    try {
                        // Always wrap FileReader in BufferedReader.
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        // Always close files.
                        bufferedReader.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println(
                                "Unable to open file '" +
                                        fileName + "'");
                    } catch (IOException ex) {
                        System.out.println(
                                "Error reading file '"
                                        + fileName + "'");
                        // Or we could just do this:
                        // ex.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Tamp biet!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("VUi long chon lai!");
                    break;
            }
        }
    }
}

class Student {
    private String rollNumber;
    private String name;

    public Student() {
    }

    public Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
