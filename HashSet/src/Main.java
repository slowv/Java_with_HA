import entity.LateInformation;
import entity.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ArrayList<LateInformation> list = new ArrayList<>();
        HashMap<String, LateInformation> map = new HashMap<>();

        list.add(new LateInformation(
                "A001",
                "Sơn Tùng",
                30000,
                "20/10/2018")
        );

        list.add(new LateInformation(
                "A002",
                "Đàm Vĩnh Hưng",
                10000,
                "20/10/2018")
        );

        list.add(new LateInformation(
                "A003",
                "Cường tỏi",
                10000,
                "20/10/2018")
        );

        list.add(new LateInformation(
                "A001",
                "Sơn Tùng",
                10000,
                "20/10/2018")
        );

        list.add(new LateInformation(
                "A003",
                "Cường tỏi",
                10000,
                "20/10/2018")
        );

        for (LateInformation lateInformation : list){
            double currentMoney = 0;
            if (map.containsKey(lateInformation.getRollNumber())){
                LateInformation infoTemp =  map.get(lateInformation.getRollNumber());
                currentMoney += infoTemp.getMoney();
            }
            currentMoney += lateInformation.getMoney();
            Student student = new Student(lateInformation.getRollNumber(), lateInformation.getName(), currentMoney, lateInformation.getDay());
            map.put(lateInformation.getRollNumber(), student);
        }

        for (LateInformation st : map.values()) {
            System.out.println(st.getName());
            System.out.println(st.getMoney());
            System.out.println("--------------------");
        }

    }
}
