package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class Chair {
    private int ChairId;
    private int Row;
    private int Number;
    private int RoomId;

    public Chair(int ChairId, int Row, int Number, int RoomId){
        this.ChairId = ChairId;
        this.Row = Row;
        this.Number = Number;
        this.RoomId = RoomId;
    }

    public int getChairId(){
        return this.ChairId;
    }

    public int getRow(){
        return this.Row;
    }

    public int getNumber(){
        return this.Number;
    }

    public int getRoomId(){
        return this.RoomId;
    }

}
