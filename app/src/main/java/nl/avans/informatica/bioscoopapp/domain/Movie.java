package nl.avans.informatica.bioscoopapp.domain;

import java.io.Serializable;

public class Movie implements Serializable {
    private String MovieId;
    private String Title;
    private String Runtime;
    private String Year;
    private String Actors;
    private String Plot;
    private String Language;
    private String Genre;
    private String Image;

    public Movie(String MovieId, String Title, String Runtime, String Year, String Actors, String Language, String Genre) {
        this.MovieId = MovieId;
        this.Title = Title;
        this.Runtime = Runtime;
        this.Year = Year;
        this.Actors = Actors;
        this.Language = Language;
        this.Genre = Genre;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getActors() {
        return Actors;
    }

    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getMovieId() {
        return this.MovieId;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getYear() {
        return this.Year;
    }

    public String getPlot() {
        return this.Plot;
    }

    public String getLanguage() {
        return this.Language;
    }

    public String getGenre() {
        return this.Genre;
    }

    public String getImage() {
        return this.Image;
    }
}
