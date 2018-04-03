package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.domain.Show;

public class SuccessfulPayment extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SuccessfulPayments";

    private Show show;
    private Movie movie;
    private int ticketsOrdered;
    private String email;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_payment);

        Intent extras = getIntent();
        //Pakt show object waarvan tickets besteld zijn
        show = (Show) extras.getSerializableExtra("SHOW");
        TextView textViewOrderShow = (TextView) findViewById(R.id.TextViewMovieOrderShow);
        //Pakt ticket object waarvan tickets besteld zijn
        movie = (Movie) extras.getSerializableExtra("MOVIE");
        TextView textViewOrderMovie = (TextView) findViewById(R.id.TextViewMovieOrderMovie);
        //Aantal tickets wat besteld zijn
        ticketsOrdered = extras.getIntExtra("TICKETSORDERED", 1);
        TextView textViewOrdered = (TextView) findViewById(R.id.TextViewMovieOrderTicketOrdered);
        //Pakt email waarnaar de tickets verstuurd zijn
        email = extras.getStringExtra("EMAIL");
        TextView textViewEmail = (TextView) findViewById(R.id.TextViewMovieOrderEmail);
        //Totale prijs van de bestelling
        price = extras.getDoubleExtra("PRICE", 10.0);
        TextView textViewPrice = (TextView) findViewById(R.id.TextViewMovieOrderPrice);

        Button buttonMainActivity = (Button) findViewById(R.id.buttonMainActivity);

        textViewOrderShow.append(String.valueOf(show.getShowId()));
        textViewOrderMovie.append(String.valueOf(movie.getTitle()));
        textViewOrdered.append(String.valueOf(ticketsOrdered));
        textViewEmail.append(String.valueOf(email));
        textViewPrice.append(String.valueOf(price));

        buttonMainActivity.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent orderTicketIntent = new Intent(this, MainActivity.class);

        startActivity(orderTicketIntent);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick was called");
        Intent mainActivityIntent = new Intent(this, MainActivity.class);

        startActivity(mainActivityIntent);
    }
}
