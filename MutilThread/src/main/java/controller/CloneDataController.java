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
    public void cloneData(Runnable thread) {
        NewspaperModel model = null;
        try {
            model = new NewspaperModel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final int myThread = 5;
        boolean isLoop = true;
        long startTime = System.currentTimeMillis();
        while (isLoop) {
            ExecutorService executor = Executors.newFixedThreadPool(myThread);
            executor.execute(thread);
            if (ThreadCloneData.map.size() == 500 || ThreadCloneData.map.size() > 500){
                isLoop = false;
                long workingTime = (System.currentTimeMillis() - startTime);
                System.out.println("Tong thoi gian lau du lieu: " + workingTime);
                System.out.println("Lấy tin thành công! hoàn thành tất cả thread.");
                executor.shutdown();
                return;
            }else{
                while (!executor.isTerminated()) {
                    if (model != null){
                        String url = model.getUrl();
                        if (url != null){
                            Runnable runnable = new ThreadCloneData(url);
                            Thread t1 = new Thread (runnable);
                            model.update(url);
                            t1.start();
                            if (t1.isAlive()){
                                System.out.println("Thread moi da chay!");
                                System.out.println(ThreadCloneData.map.size());
                            }
                        }
                    }
                }
            }

        }
    }
}
