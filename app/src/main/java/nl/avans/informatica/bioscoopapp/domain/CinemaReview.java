package nl.avans.informatica.bioscoopapp.domain;

import java.io.Serializable;

public class CinemaReview {
    private int ReviewId;
    private String Date;
    private String Content;
    private double Rating;

    public CinemaReview(int ReviewId, String Date, String Content, double Rating) {
        this.ReviewId = ReviewId;
        this.Date = Date;
        this.Content = Content;
        this.Rating = Rating;
    }

    public int getReviewId() {
        return this.ReviewId;
    }

    public String getDate() {
        return this.Date;
    }

    public String getContent() {
        return this.Content;
    }

    public double getRating() {
        return this.Rating;
    }
}
