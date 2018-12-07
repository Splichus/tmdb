package com.example.uzivatel.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uzivatel.myapp.adapters.MovieAdapter;
import com.example.uzivatel.myapp.models.Movie;
import com.example.uzivatel.myapp.models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverMovies extends AppCompatActivity {

    Button searchButton;
    ListView listView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_movies);

        searchButton = findViewById(R.id.discover_search_button);
        listView = findViewById(R.id.discover_list_view);

        final MovieAdapter movieAdapter = new MovieAdapter(this);

        listView.setAdapter(movieAdapter);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        TMDBClient client = retrofit.create(TMDBClient.class);
        Call<Result> call = client.getTopRatedMovies();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("mojeAppkaResponse", response.raw().toString());
                List<Movie> movies = response.body().getResults();

                movieAdapter.addAll(movies);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("mojeAppkaFailure", t.getMessage());
                Toast.makeText(DiscoverMovies.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
