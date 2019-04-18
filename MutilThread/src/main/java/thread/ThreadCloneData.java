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
    private String selector;

    public ThreadCloneData(String urlClone) {
        this.urlClone = urlClone;
    }
    public static HashMap<String, Newspaper> map = new HashMap<String, Newspaper>();
    @Override
    public void run() {


        String url = this.urlClone;
        Connection cnn = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        NewspaperModel model = null;
        try {
            model = new NewspaperModel();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Document document = cnn.get();
            Elements elements = document.body().select("a");
            for (Element element : elements) {
                String http = element.attr("href").replace("javascript:void(0);", "");
//                boolean isHttpBad = http.contains("///");
//                if (!isHttpBad){
//
//                }
                if (!http.toLowerCase().startsWith("http") || http.length() > 255) {
                    continue;
                }

                String title = element.attr("title");
                map.put(http, new Newspaper(title, http, 0));
                Newspaper newspaper = map.get(http);
                if (model != null) {
                    boolean existUrl = model.checkExistUrl(http);
                    if (newspaper.getTilte().length() != 0 && !existUrl) {
                        model.insert(newspaper);
                    }
                }
//                System.out.println(map.get(http));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getUrlClone() {
        return urlClone;
    }

    public void setUrlClone(String urlClone) {
        this.urlClone = urlClone;
    }
}
