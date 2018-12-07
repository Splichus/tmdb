package com.example.uzivatel.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void searchMoviesActivity (View view){
        Intent intent = new Intent(this, SearchMoviesById.class);
        startActivity(intent);
    }

    public void discoverMoviesActivity (View view){
        Intent intent = new Intent(this, DiscoverMovies.class);
        startActivity(intent);
    }

}
