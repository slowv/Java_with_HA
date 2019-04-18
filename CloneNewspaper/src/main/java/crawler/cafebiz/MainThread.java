package crawler.cafebiz;

import enity.NewSpaper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainThread {
    public static void main(String[] args) {
        ArrayList<NewSpaper> newSpapers = new ArrayList<NewSpaper>();
        CloneNewspaperThread thread1 = new CloneNewspaperThread(newSpapers, "http://cafebiz.vn", ".listtimeline.list_home>ul li>a");
        CloneNewspaperThread thread2 =
                new CloneNewspaperThread(newSpapers, "http://cafebiz.vn", ".listtimeline>ul.last li[ispopup=\"1\"] > a");
        thread1.start();
        thread2.start();
    }
}


class CloneNewspaperThread extends Thread{
    private ArrayList<NewSpaper> newSpapers;
    private String link;
    private String selector;

    public CloneNewspaperThread(ArrayList<NewSpaper> newSpapers, String link, String selector) {
        this.newSpapers = newSpapers;
        this.link = link;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            Document document = Jsoup.connect(this.link).get();
            Elements elements = document.select(this.selector);

            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                NewSpaper newSpaper = new NewSpaper(element.attr("href"), element.attr("title"));
                this.newSpapers.add(newSpaper);
                System.out.println(newSpaper.toString());
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

    public ArrayList<NewSpaper> getNewSpapers() {
        return newSpapers;
    }

    public void setNewSpapers(ArrayList<NewSpaper> newSpapers) {
        this.newSpapers = newSpapers;
    }
}
