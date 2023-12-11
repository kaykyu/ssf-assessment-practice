package kq.practice.assessmentworkshop.model;

public class Article {

    private String title;
    private String image;
    private String author;
    private String description;
    private String date;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article(String title, String image, String author, String description, String date, String url) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.description = description;
        this.date = date;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", image=" + image + ", author=" + author + ", description=" + description
                + ", date=" + date + ", url=" + url + "]";
    }

}