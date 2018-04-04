package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.api.GetShowsRequest;
import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.domain.Show;
import nl.avans.informatica.bioscoopapp.util.interfaces.OnShowAvailable;

public class OrderTicketActivity extends AppCompatActivity implements OnShowAvailable, View.OnClickListener {
    private final static String TAG = "OrderTicketActivity";
    private Movie movie;
    private ArrayList<String> paths;
    private ArrayList<Show> shows;
    private ArrayAdapter<String> adapter;
    private Spinner selectionSpinner;
    private NumberPicker np;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);

        Intent extras = getIntent();
        this.movie = (Movie) extras.getSerializableExtra("MOVIE");

        GetShowsRequest getShowsRequest = new GetShowsRequest(this);
        String params[] = {"https://api.teumaas.nl/getShows.php?where=movieid&movieid=" + this.movie.getMovieId()};
        getShowsRequest.execute(params);

        np = (NumberPicker) findViewById(R.id.numberPicker);
        String nums[] = {"1", "2", "3", "4", "5", "6"};
        np.setMinValue(1);
        np.setMaxValue(6);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setValue(1);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);

        paths = new ArrayList<>();
        shows = new ArrayList<>();

        selectionSpinner = (Spinner) findViewById(R.id.selectionSpinner);
        adapter = new ArrayAdapter<String>(OrderTicketActivity.this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectionSpinner.setAdapter(adapter);

        TextView title = (TextView) findViewById(R.id.titleTicket);
        title.setText(movie.getTitle());

        editText = (EditText) findViewById(R.id.email_address);
    }

    @Override
    public void onShowAvailable(Show show) {
        paths.add(show.getDate() + ", " + show.getTime());
        shows.add(show);
        adapter.notifyDataSetChanged();
        TextView ticketPrice = (TextView) findViewById(R.id.ticketPrice);
        ticketPrice.setText("€" + shows.get(0).getPrice());
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick was called");
        Intent paymentIntent = new Intent(this, PaymentActivity.class);

        paymentIntent.putExtra("MOVIE", this.movie);
        paymentIntent.putExtra("SHOW", shows.get(selectionSpinner.getSelectedItemPosition()));
        paymentIntent.putExtra("AMMOUNT", np.getValue());
        paymentIntent.putExtra("EMAIL", "" + editText.getText());

        startActivity(paymentIntent);
    }
}
