package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.Movie;

/**
 * Created by sjoer on 23-3-2018.
 */

public interface OnMovieAvailable {
    void onMovieAvailable(Movie movie);
}
