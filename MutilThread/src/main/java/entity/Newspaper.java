package entity;

public class Newspaper {
    private String tilte;
    private String url;
    private int index;

    public Newspaper() {
    }

    public Newspaper(String tilte, String url, int index) {
        this.tilte = tilte;
        this.url = url;
        this.index = index;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "tilte='" + tilte + '\'' +
                ", url='" + url + '\'' +
                ", index=" + index +
                '}';
    }
}
