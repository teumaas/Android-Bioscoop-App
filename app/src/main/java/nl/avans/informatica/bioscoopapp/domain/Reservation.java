package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class Reservation {
    private int ReservationId;
    private int ChairId;
    private int ShowId;

    public Reservation(int Reservationid, int ChairId, int ShowId){
        this.ReservationId = Reservationid;
        this.ChairId = ChairId;
        this.ShowId = ShowId;
    }

    public int getReservationId(){
        return this.ReservationId;
    }

    public int getChairId(){
        return this.ChairId;
    }

    public int getShowId(){
        return this.ShowId;
    }
}
