public class Flight {
    private int number;
    private String destination;

    public Flight() {
        this.destination = null;
        this.number = 0;
    }

    public Flight(int number, String description) {
        this.number = number;
        this.destination = description;
    }

    void display(){
        System.out.println(this.number + ", " + this.destination);
    }

    String getDescription(){
        return this.destination;
    }

    int getNumber(){
        return this.number;
    }
}
