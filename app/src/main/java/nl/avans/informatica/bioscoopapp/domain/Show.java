package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class Show {
    private int ShowId;
    private String Date;
    private String Time;
    private double Price;
    private String MovieId;
    private int RoomId;

    public Show(int ShowId, String Date, String Time, double Price, String MovieId, int RoomId){
        this.ShowId = ShowId;
        this.Date = Date;
        this.Time = Time;
        this.Price = Price;
        this.MovieId = MovieId;
        this.RoomId = RoomId;
    }

    public int getShowId(){
        return this.ShowId;
    }

    public String getDate(){
        return this.Date;
    }

    public String getTime(){
        return this.Time;
    }

    public double getPrice(){
        return this.Price;
    }

    public String getMovieId(){
        return this.MovieId;
    }

    public int getRoomId(){
        return this.RoomId;
    }
}
