package com.example.uzivatel.myapp.models;

public class Movie {

    String original_title;
    String original_language;
    float popularity;
    String poster_path;
    long id;

    public Movie(String original_title, String original_language, float popularity, String poster_path, long id) {
        this.original_title = original_title;
        this.original_language = original_language;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.id = id;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
}
