package nl.avans.informatica.bioscoopapp;

import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private String actors;
    private String genre;
    private int duration;
    private String imageURL;
    private String description;
    private int releaseYear;

    public Movie(String title, String actors, String genre, int duration, String imageURL) {
        this.title = title;
        this.actors = actors;
        this.genre = genre;
        this.duration = duration;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getActors() {
        return actors;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }
}
