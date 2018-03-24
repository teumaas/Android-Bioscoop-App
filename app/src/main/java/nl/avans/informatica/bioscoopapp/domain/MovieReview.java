package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class MovieReview {
    private int ReviewId;
    private String Date;
    private String Content;
    private double Rating;
    private String MovieId;

    public MovieReview(int ReviewId, String Date, String Content, double Rating, String MovieId){
        this.ReviewId = ReviewId;
        this.Date = Date;
        this.Content = Content;
        this.Rating = Rating;
        this.MovieId = MovieId;
    }

    public int getReviewId(){
        return this.ReviewId;
    }

    public String getDate(){
        return this.Date;
    }

    public String getContent(){
        return this.Content;
    }

    public double getRating(){
        return this.Rating;
    }

    public String getMovieId(){
        return this.MovieId;
    }
}
