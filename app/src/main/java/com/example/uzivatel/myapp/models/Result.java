package com.example.uzivatel.myapp.models;

import java.util.List;

public class Result {

    List<Movie> results;

    public Result(List<Movie> results) {
        this.results = results;
    }

    public Result() {
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
