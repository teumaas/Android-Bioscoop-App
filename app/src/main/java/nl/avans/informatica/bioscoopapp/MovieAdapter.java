package nl.avans.informatica.bioscoopapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = "MovieAdapter";

    private ArrayList<Movie> movies;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View view;
        private ImageView imageViewMovie;
        private TextView textViewTitle;
        private TextView textViewActors;
        private TextView textViewGenre;
        private TextView textViewDuration;

        private ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(this);

            imageViewMovie = (ImageView) itemView.findViewById(R.id.imageViewMovie);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewMovieTitle);
            textViewActors = (TextView) itemView.findViewById(R.id.textViewMovieActors);
            textViewGenre = (TextView) itemView.findViewById(R.id.textViewGenre);
            textViewDuration = (TextView) itemView.findViewById(R.id.textViewDuration);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick was called.");

            int position = getAdapterPosition();
            Movie m = (Movie) movies.get(position);

            Intent movieDetailIntent = new Intent(
                    view.getContext().getApplicationContext(),
                    MovieDetailActivity.class);

            movieDetailIntent.putExtra("MOVIE", m);

            view.getContext().startActivity(movieDetailIntent);
        }
    }

    public MovieAdapter(Context context, ArrayList<Movie> myDataset) {
        this.context = context;
        movies = myDataset;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder was called.");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder was called.");

        Movie movie = (Movie) this.movies.get(position);

        Picasso.with(context)
                .load(movie.getImageURL())
                .into(holder.imageViewMovie);

        holder.textViewTitle.setText(String.valueOf(movie.getTitle()));
        holder.textViewActors.setText(String.valueOf(movie.getActors()));
        holder.textViewGenre.setText(String.valueOf(movie.getGenre()));
        holder.textViewDuration.setText(String.valueOf(movie.getDuration()) + " minutes");
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount was called.");

        return movies.size();
    }
}
