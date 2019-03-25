package utility;

public class Confirm {
    public void confirmString(String Name, String action, String entityName){
        System.out.printf("Bạn có chắc muốn %s %s: %s \n", action, entityName, Name);
        System.out.println("1. Đồng ý \t \t \t \t 2. Quay lại\n");
        System.out.println("Lựa chọn của bạn là: ");
    }
}
