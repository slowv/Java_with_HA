package controller;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloneDataController implements CloneData{

    @Override
    public void cloneData(Runnable thread) {
        final int myThread = 20;
        ExecutorService executor = Executors.newFixedThreadPool(myThread);
        executor.execute(thread);
        executor.shutdown();
        while (!executor.isTerminated()){

        }

        System.out.println("Lấy tin thành công! hoàn thành tất cả thread.");
    }
}
