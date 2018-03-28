package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);

        Intent extras = getIntent();
        this.movie = (Movie) extras.getSerializableExtra("MOVIE");

        GetShowsRequest getShowsRequest = new GetShowsRequest(this);
        String params[] = {"https://api.teumaas.nl/getShows.php?where=movieid&movieid=" + this.movie.getMovieId()};
        getShowsRequest.execute(params);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
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
    }

    @Override
    public void onShowAvailable(Show show) {
        paths.add(show.getDate() + ", " + show.getTime());
        shows.add(show);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick was called");
//        Intent orderTicketIntent = new Intent(this, OrderTicketActivity.class);
//
//        orderTicketIntent.putExtra("MOVIE", this.movie);
//
//        startActivity(orderTicketIntent);

        Log.d(TAG, shows.get(selectionSpinner.getSelectedItemPosition()).toString());

    }
}
