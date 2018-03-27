package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.MovieReview;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnMovieReviewAvailable {
    void onMovieReviewAvailable(MovieReview movieReview);
}
