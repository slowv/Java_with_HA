package thread;

import entity.Newspaper;
import model.NewspaperModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCloneData implements Runnable {
    private String urlClone;

    public ThreadCloneData(String urlClone) {
        this.urlClone = urlClone;
    }

    @Override
    public void run() {

        HashMap<String, Newspaper> map = new HashMap<String, Newspaper>();
        String url = this.urlClone;
        Connection cnn = Jsoup.connect(url);
        NewspaperModel model = null;
        try {
            model = new NewspaperModel();
//            model.truncateDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Document document = cnn.get();
            Elements elements = document.select("body a");
            for (Element element : elements) {
                String http = element.attr("href").replace("javascript:void(0);", "");
                if (!http.toLowerCase().startsWith("http")) {
                    http = url + http;
                }
                String title = element.attr("title");
                map.put(http, new Newspaper(title, http, 0));
                Newspaper newspaper = map.get(http);
                if (model != null) {
                    if (newspaper.getTilte().length() != 0) {
                        model.insert(newspaper);
                    }
                }
//                System.out.println(map.get(http));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUrlClone() {
        return urlClone;
    }

    public void setUrlClone(String urlClone) {
        this.urlClone = urlClone;
    }
}
