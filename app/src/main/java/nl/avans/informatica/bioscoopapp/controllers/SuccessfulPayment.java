package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.domain.Show;

public class SuccessfulPayment extends AppCompatActivity {
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
        //Pakt ticket object waarvan tickets besteld zijn
        movie = (Movie) extras.getSerializableExtra("MOVIE");
        //Aantal tickets wat besteld zijn
        ticketsOrdered = extras.getIntExtra("TICKETSORDERED", 1);
        //Pakt email waarnaar de tickets verstuurd zijn
        email = extras.getStringExtra("EMAIL");
        //Totale prijs van de bestelling
        price = extras.getDoubleExtra("PRICE", 10.0);
    }
}
