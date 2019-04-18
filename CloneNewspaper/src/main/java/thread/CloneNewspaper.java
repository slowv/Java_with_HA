package thread;

import enity.NewSpaper;
import javafx.animation.Timeline;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CloneNewspaper extends Thread {
    public static HashMap<String, NewSpaper> newSpapers = new HashMap<String, NewSpaper>();
    private String link;
    private String selector;

    public CloneNewspaper(String link, String selector) {
        this.link = link;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            long startTime = System.nanoTime();
            Connection cnn = Jsoup.connect(this.link).timeout(1 * 1000000);
            Document document = cnn.get();
            Elements elements = document.select(this.selector);
            System.out.println(elements.size());
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                NewSpaper newSpaper = new NewSpaper(element.attr("href"), element.attr("title"));
                if (newSpapers.containsKey(newSpaper.getUrl())){
                    continue;
                }
                String http = this.link + newSpaper.getUrl().replace("http://cafebiz.vn", "");
                System.out.println(http);
                Thread t1 = new CloneNewspaper( http, this.selector);
                t1.start();
                newSpapers.put(newSpaper.getUrl(), newSpaper);
                if (newSpapers.size() == 1000 || newSpapers.size() > 1000){
                    t1.stop();
                    long endTime = System.nanoTime();
                    long timeElapsed = startTime + endTime;
                    System.out.println(newSpapers);
                    System.out.println("Lay " + newSpapers.size() + " du lieu ve trong vong: " + timeElapsed + " giay.");
                    return;
                };
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

}
