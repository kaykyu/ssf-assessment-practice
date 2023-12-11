package kq.practice.assessmentworkshop.model;

public class Request {

    private String category;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Request() {
    }

    public Request(String category, String code) {
        this.category = category;
        this.code = code;
    }

}
