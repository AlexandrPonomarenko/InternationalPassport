package com.InternationalPassport.helper;

public class SearchPassportForm {
    private String seria;
    private String type;

    public SearchPassportForm () { }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchPassportForm{" +
                "seria='" + seria + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
