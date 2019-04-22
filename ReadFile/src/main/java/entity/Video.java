package entity;

public class Video {
    private String id;
    private String title;
    private String day;
    private String conutView;

    public Video() {
    }

    public Video(String id, String title, String day, String conutView) {
        this.id = id;
        this.title = title;
        this.day = day;
        this.conutView = conutView;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getConutView() {
        return conutView;
    }

    public void setConutView(String conutView) {
        this.conutView = conutView;
    }
}
