package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.domain.Show;

public class PaymentActivity extends AppCompatActivity {
    private WebView webView;
    private Show show;
    private Movie movie;
    private int ticketsOrdered;
    private String email;
    private double price;
    private final static String TAG = "PaymentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent extras = getIntent();
        show = (Show) extras.getSerializableExtra("SHOW");
        movie = (Movie) extras.getSerializableExtra("MOVIE");
        ticketsOrdered = extras.getIntExtra("AMMOUNT", 1);
        email = extras.getStringExtra("EMAIL");

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("https://api.teumaas.nl/sendTicket.php")) {
                    startCompleted();
                    return false;
                } else {
                    view.loadUrl(url);
                    return true;
                }
            }
        });

        String QRCode = show.getMovieId() + show.getTime() + show.getDate() + show.getRoomId();
        int ShowId = show.getShowId();
        price = ticketsOrdered * show.getPrice();
        String redirectUrl = "sendTicket.php?ticketID=1&email=" + email + "&QRCode=" + QRCode + "&showID=" + ShowId + "&chairID=4&paymentMethod=iDeal&price=" + price +"";

        webView.loadUrl("https://api.teumaas.nl/setupPayment.php?amount=" + price + "&redirect=" + redirectUrl + "&desc=" + movie.getTitle() + "");
    }

    private void startCompleted(){
        Intent paymentSuccessfulIntent = new Intent(this, SuccessfulPayment.class);

        paymentSuccessfulIntent.putExtra("MOVIE", this.movie);
        paymentSuccessfulIntent.putExtra("SHOW", this.show);
        paymentSuccessfulIntent.putExtra("TICKETSORDERED", this.ticketsOrdered);
        paymentSuccessfulIntent.putExtra("EMAIL", this.email);
        paymentSuccessfulIntent.putExtra("PRICE", this.price);

        startActivity(paymentSuccessfulIntent);
    }
}
