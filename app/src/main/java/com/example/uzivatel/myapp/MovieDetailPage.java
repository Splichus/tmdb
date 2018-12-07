package com.example.uzivatel.myapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uzivatel.myapp.models.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailPage extends AppCompatActivity {

    ConstraintLayout mainLayout;
    TextView textViewTitle;
    TextView textViewLanguage;
    TextView textViewPopularity;
    TextView textViewTitleTitle;
    TextView textViewLanguageTitle;
    TextView textViewPopularityTitle;
    ImageView imageViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_page);

        mainLayout = findViewById(R.id.search_layout);
        textViewTitle = findViewById(R.id.movie_title);
        textViewLanguage = findViewById(R.id.movie_language);
        textViewPopularity = findViewById(R.id.movie_popularity);
        textViewTitleTitle = findViewById(R.id.movie_title_title);
        textViewLanguageTitle = findViewById(R.id.movie_language_title);
        textViewPopularityTitle = findViewById(R.id.movie_popularity_title);
        imageViewMovie = findViewById(R.id.movie_image);

        long selected_movie_id = getIntent().getLongExtra("selected_movie_id", 0);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        TMDBClient client = retrofit.create(TMDBClient.class);
        Call<Movie> call = client.getMovieById(selected_movie_id);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.d("mojeAppkaResponse", response.raw().toString());
                Movie movie = response.body();

                textViewTitle.setText(movie.getOriginal_title());
                textViewLanguage.setText(movie.getOriginal_language());
                textViewPopularity.setText("" + movie.getPopularity());
                textViewTitleTitle.setText("Title:");
                textViewLanguageTitle.setText("Language:");
                textViewPopularityTitle.setText("Popularity:");
                new DownloadImageTask(imageViewMovie).execute(
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.getPoster_path());

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("mojeAppkaFailure", t.getMessage());
                Toast.makeText(MovieDetailPage.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

