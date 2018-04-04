package nl.avans.informatica.bioscoopapp.controllers;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.api.ExecuteQuery;
import nl.avans.informatica.bioscoopapp.api.GetMovieReviewsRequest;
import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.domain.MovieReview;
import nl.avans.informatica.bioscoopapp.domain.SQLResult;
import nl.avans.informatica.bioscoopapp.domain.Show;
import nl.avans.informatica.bioscoopapp.util.ReviewAdapter;
import nl.avans.informatica.bioscoopapp.util.interfaces.OnMovieReviewAvailable;
import nl.avans.informatica.bioscoopapp.util.interfaces.OnSQLResultAvailable;

public class ReviewActivity extends AppCompatActivity implements OnMovieReviewAvailable, OnSQLResultAvailable, View.OnClickListener {

    private ReviewAdapter adapter;
    private ArrayList<MovieReview> reviews = new ArrayList<MovieReview>();
    private View contentView;
    private final static String TAG = "ReviewActivity";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        contentView = this.findViewById(android.R.id.content);

        final ListView reviewListView = (ListView) findViewById(R.id.reviewListView);
        adapter = new ReviewAdapter(getApplicationContext(), getLayoutInflater(), reviews);
        reviewListView.setAdapter(adapter);

        Intent extras = getIntent();
        movie = (Movie) extras.getSerializableExtra("MOVIE");

        GetMovieReviewsRequest getMovies = new GetMovieReviewsRequest(this);
        String params[] = {"https://api.teumaas.nl/getMovieReviews.php?where=movieid&movieid=" + movie.getMovieId() + ""};
        getMovies.execute(params);
        // onderstaande zorgt ervoor dat de listview gehide word wanneer je aan het typen bent.
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                contentView.getWindowVisibleDisplayFrame(r);
                int screenHeight = contentView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) { // keyboard is opened
                    reviewListView.setVisibility(View.GONE);
                }
                else { // keyboard is closed
                    reviewListView.setVisibility(View.VISIBLE);
                }
            }
        });

        EditText reviewEditText = (EditText) findViewById(R.id.reviewEditText);

        Button reviewButton = (Button) findViewById(R.id.plaatsReviewButton);
        reviewButton.setOnClickListener(this);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void addReview(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();
        Log.d(TAG, dateFormat.format(currentDate));

        String date = dateFormat.format(currentDate);
        String content = "Wat een mooie testfilm";
        String rating = "4.0";
        String movieid = "tt5198796";

        ExecuteQuery addReview = new ExecuteQuery(this);
        String parameters[] = {"https://api.teumaas.nl/changeValues.php?action=create&table=review&date=" + date + "&content=" + content + "&rating=" + rating + "&movieid=" + movieid + ""};
        addReview.execute(parameters);
    }

    @Override
    public void onMovieReviewAvailable(MovieReview movieReview) {
        reviews.add(movieReview);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSQLResultAvailable(SQLResult sqlResult) {
        //
    }

    @Override
    public void onClick(View view) {
        addReview();
        Log.d(TAG, "onClick was called");
    }
}
