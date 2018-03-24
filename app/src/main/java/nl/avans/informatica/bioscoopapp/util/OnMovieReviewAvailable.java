package nl.avans.informatica.bioscoopapp.util;

import nl.avans.informatica.bioscoopapp.domain.MovieReview;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnMovieReviewAvailable {
    void onMovieReviewAvailable(MovieReview movieReview);
}
