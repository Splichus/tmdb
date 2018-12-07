package com.example.uzivatel.myapp;

import com.example.uzivatel.myapp.models.Movie;
import com.example.uzivatel.myapp.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBClient {

    @GET("movie/{id}?api_key=3eddf8084ea6fa36ad49227fd10e46cc&language=en-US")
    Call<Movie> getMovieById (@Path("id") long id);

    @GET("movie/top_rated?api_key=3eddf8084ea6fa36ad49227fd10e46cc&language=en-US")
    Call<Result> getTopRatedMovies();
}
