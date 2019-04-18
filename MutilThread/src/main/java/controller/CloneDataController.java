package controller;


import entity.Newspaper;
import model.NewspaperModel;
import thread.ThreadCloneData;

import java.sql.SQLException;
import java.util.concurrent.*;

public class CloneDataController implements CloneData {

    public CloneDataController() {
        NewspaperModel model = null;
        try {
            model = new NewspaperModel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (model != null) {
            model.truncateDb();
        }
    }

    @Override
    public void cloneData(String url) {
//        final int myThread = 2;
//        boolean isLoop = true;
//        long startTime = System.currentTimeMillis();
//        System.out.println("Executing in Thread: " + Thread.currentThread().getName());
//        ExecutorService executor = Executors.newFixedThreadPool(myThread);
//        System.out.println("Submitting the tasks for execution...");
//        executor.submit(thread);
//        executor.shutdown();
        ThreadCloneData cloneData = new ThreadCloneData(url);
        cloneData.getLink();
    }
}
