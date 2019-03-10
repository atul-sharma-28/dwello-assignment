package com.atulsharma.dwelloassignment.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Poster")
    private String poster;

    @SerializedName("Title")
    private String title;

    @SerializedName("Type")
    private String type;

    @SerializedName("Year")
    private int year;

    @SerializedName("imdbID")
    private String imdbID;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
