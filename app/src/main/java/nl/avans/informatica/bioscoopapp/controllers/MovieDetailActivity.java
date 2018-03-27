package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate was called.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie_activity);

        setupActionBar();

        Intent extras = getIntent();
        Movie movie = (Movie) extras.getSerializableExtra("MOVIE");

        ImageView imageViewMovie = (ImageView) findViewById(R.id.imageViewMovie);
        TextView textViewTitle = (TextView) findViewById(R.id.textViewMovieTitle);
        TextView textViewActors = (TextView) findViewById(R.id.textViewMovieActors);
        TextView textViewGenre = (TextView) findViewById(R.id.textViewGenre);
        TextView textViewDuration = (TextView) findViewById(R.id.textViewDuration);
        TextView textViewLanguage = (TextView) findViewById(R.id.textViewLanguage);
        TextView textViewYear = (TextView) findViewById(R.id.textViewYear);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        Picasso.with(this)
                .load(movie.getImage())
                .into(imageViewMovie);

        Log.d(TAG, "Picasso was used.");

        textViewTitle.setText(String.valueOf(movie.getTitle()));
        textViewActors.setText(String.valueOf(movie.getActors()));
        textViewGenre.setText(String.valueOf(movie.getGenre()));
        textViewDuration.setText(String.valueOf(movie.getRuntime()));
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewLanguage.setText(String.valueOf(movie.getLanguage()));
        textViewDescription.setText(String.valueOf(movie.getPlot()));
    }

    private void setupActionBar() {
        Log.d(TAG, "setupActionBar was called.");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Log.d(TAG, "setupActionBar was executed.");
    }
}
