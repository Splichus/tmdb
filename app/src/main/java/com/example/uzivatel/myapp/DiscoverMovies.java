package com.example.uzivatel.myapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uzivatel.myapp.adapters.MovieAdapter;
import com.example.uzivatel.myapp.models.Movie;
import com.example.uzivatel.myapp.models.Result;
import com.example.uzivatel.myapp.tasks.LoadMoviesTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverMovies extends AppCompatActivity {

    Button searchButton;
    ProgressBar progressBar;
    ListView listView;
    private int pageCount = 1;
    MovieAdapter movieAdapter;
//    private LoadMoviesTask loadMoviesTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_movies);
        searchButton = findViewById(R.id.discover_search_button);
        listView = findViewById(R.id.discover_list_view);

        movieAdapter = new MovieAdapter(this);
        listView.setAdapter(movieAdapter);

        listView.setOnScrollListener(onScrollListener());

//        loadMoviesTask.execute();

        getMoviesPage();
    }

    private AbsListView.OnScrollListener onScrollListener (){
        return new AbsListView.OnScrollListener() {
            int currentScrollState;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 9;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE){
                    if (listView.getLastVisiblePosition() >= count - threshold){
                        Log.i("scrollingLogging", "loading more data");
                        getMoviesPage();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        };
    }

    public void getMoviesPage() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        TMDBClient client = retrofit.create(TMDBClient.class);
        Call<Result> call = client.getTopRatedMovies(pageCount);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("mojeAppkaResponse", response.raw().toString());
                List<Movie> movies = response.body().getResults();

                movieAdapter.addAll(movies);

                pageCount++;

//                for (int i = 0; i < 10; i++) {
//                    movieAdapter.add(movies.get(i));
//                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("mojeAppkaFailure", t.getMessage());
                Toast.makeText(DiscoverMovies.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
