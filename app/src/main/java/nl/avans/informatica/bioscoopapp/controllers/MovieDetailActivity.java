package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MovieDetailActivity";
    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate was called.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie_activity);

        setupActionBar();

        Intent extras = getIntent();
        this.movie = (Movie) extras.getSerializableExtra("MOVIE");

        ImageView imageViewMovie = (ImageView) findViewById(R.id.imageViewMovie);
        TextView textViewTitle = (TextView) findViewById(R.id.textViewMovieTitle);
        TextView textViewActors = (TextView) findViewById(R.id.textViewMovieActors);
        TextView textViewGenre = (TextView) findViewById(R.id.textViewGenre);
        TextView textViewDuration = (TextView) findViewById(R.id.textViewDuration);
        TextView textViewLanguage = (TextView) findViewById(R.id.textViewLanguage);
        TextView textViewYear = (TextView) findViewById(R.id.textViewYear);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        Button orderTickets = (Button) findViewById(R.id.orderTickets);

        Button reviewButton = (Button) findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reviewIntent = new Intent(MovieDetailActivity.this, ReviewActivity.class);
                startActivity(reviewIntent);
            }
        });

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
        orderTickets.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick was called");
        Intent orderTicketIntent = new Intent(this, OrderTicketActivity.class);

        orderTicketIntent.putExtra("MOVIE", this.movie);

        startActivity(orderTicketIntent);
    }
}
