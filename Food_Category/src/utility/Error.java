package utility;

public class Error {
    public void alertErrorNumberFormat(String choice){
        System.out.printf("Nhập sai định dạng số, vui lòng nhập lại %s.\n", choice);
    }

    public void alertErrorChoice(String choice){
        System.out.printf("Lựa chọn sai, vui lòng chọn lại %s.\n", choice);
    }

    public void alertErrorIsEmpty(String entityName){
        System.out.printf("Hiện tại bạn chưa có %s nào!\n", entityName);
    }

    public void alertErrorFindNotFound(String entityName, String code){
        System.out.printf("Không tìm thấy %s nào với mã là: %s \n", entityName, code);
    }


}
