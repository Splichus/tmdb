package com.example.uzivatel.myapp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.uzivatel.myapp.DiscoverMovies;
import com.example.uzivatel.myapp.R;

import java.net.URL;

public class LoadMoviesTask extends AsyncTask<Void, String, String> {

//    DiscoverMovies discoverMovies;
//    ProgressBar progressBar = discoverMovies.findViewById(R.id.discover_progress_bar);

    @Override
    protected void onPreExecute() {
        Log.i("loadMoviesTaskLogging", "onPreExecute() entered");
    }

    @Override
    protected String doInBackground(Void... voids){
        Log.i("loadMoviesTaskLogging", "doInBackground() entered");
        publishProgress("Loading");

//        discoverMovies.getMoviesPage();
        return "Finished loading";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i("loadMoviesTaskLogging", "onPostExecute() entered");
//        progressBar.setProgress(100);
    }
}
