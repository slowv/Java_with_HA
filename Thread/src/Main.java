import thread.CountNumberThread;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new CountNumberThread();
        t1.start();
    }
}
