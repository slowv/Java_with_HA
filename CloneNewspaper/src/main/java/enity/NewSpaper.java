package enity;

public class NewSpaper {
    private String url;
    private String title;


    public NewSpaper() {
    }

    public NewSpaper(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewSpaper{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}