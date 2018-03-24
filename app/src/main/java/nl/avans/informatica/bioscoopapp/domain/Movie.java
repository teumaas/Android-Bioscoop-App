package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 23-3-2018.
 */

public class Movie {
    private String MovieId;
    private String Title;
    private String Year;
    private String Plot;
    private String Language;
    private String Genre;
    private String Image;

    public Movie(String MovieId, String Title, String Year, String Language, String Genre){
        this.MovieId = MovieId;
        this.Title = Title;
        this.Year = Year;
        this.Language = Language;
        this.Genre = Genre;
    }

    public void setPlot(String Plot){
        this.Plot = Plot;
    }

    public void setImage(String Image){
        this.Image = Image;
    }

    public String getMovieId(){
        return this.MovieId;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getYear(){
        return this.Year;
    }

    public String getPlot(){
        return this.Plot;
    }

    public String getLanguage(){
        return this.Language;
    }

    public String getGenre(){
        return this.Genre;
    }

    public String getImage(){
        return this.Image;
    }
}
