package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.CinemaReview;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnCinemaReviewAvailable {
    void onCinemaReviewAvailable(CinemaReview cinemaReview);
}
