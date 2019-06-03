package es.iessaladillo.yeraymoreno.pr06_new.data;

public class Joke {
    private String icon_url;
    private String id;
    private String url;
    private String value;

    public Joke() {
    }

    public Joke(String icon_url, String id, String url, String value) {
        this.icon_url = icon_url;
        this.id = id;
        this.url = url;
        this.value = value;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getValue() {
        return value;
    }
}
