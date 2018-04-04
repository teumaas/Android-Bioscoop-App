package nl.avans.informatica.bioscoopapp.controllers;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.MovieReview;
import nl.avans.informatica.bioscoopapp.util.ReviewAdapter;

public class ReviewActivity extends AppCompatActivity {

    private ReviewAdapter adapter;
    private ArrayList<MovieReview> reviews = new ArrayList<MovieReview>();
    private View contentView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        contentView = this.findViewById(android.R.id.content);

        final ListView reviewListView = (ListView) findViewById(R.id.reviewListView);
        adapter = new ReviewAdapter(getApplicationContext(), getLayoutInflater(), reviews);
        reviewListView.setAdapter(adapter);

        //dit is test data.
        MovieReview review1 = new MovieReview(1,"04-04-2018", "Test", 5.0, "402S");
        MovieReview review2 = new MovieReview(1,"04-04-2018", "Test", 5.0, "402S");
        MovieReview review3 = new MovieReview(1,"04-04-2018", "Test", 5.0, "402S");
        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
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

        Button reviewButton = (Button) findViewById(R.id.reviewButton);
//        reviewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //ToDo add review to DB
//            }
//        });
    }
}
