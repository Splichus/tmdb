package com.example.uzivatel.myapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.uzivatel.myapp.MovieDetailPage;
import com.example.uzivatel.myapp.R;
import com.example.uzivatel.myapp.models.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie current = getItem(position);

        convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.movie, parent, false);

        final TextView movieTitle = (TextView) convertView.findViewById(R.id.original_title);
        TextView movieLang = (TextView) convertView.findViewById(R.id.original_language);
        TextView moviePop = (TextView) convertView.findViewById(R.id.popularity);


        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("xxxxxxxxxxxxxx", "" + current.getId());
                Intent intent = new Intent(getContext(), MovieDetailPage.class);
                intent.putExtra("selected_movie_id", current.getId());
                getContext().startActivity(intent);
            }
        });

        if (current != null && movieTitle != null){
            movieTitle.setText(current.getOriginal_title());
        }

        if (current != null && movieLang != null){
            movieLang.setText(current.getOriginal_language());
        }

        if (current != null && moviePop != null){
            moviePop.setText("" + current.getPopularity());
        }

        return convertView;
    }
}
