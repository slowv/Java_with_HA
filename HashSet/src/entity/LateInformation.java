package entity;

public class LateInformation {
    private String rollNumber;
    private String name;
    private String day;
    private double money;

    public LateInformation() {
    }

    public LateInformation(String rollNumber, String name, double money, String day) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.money = money;
        this.day = day;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
