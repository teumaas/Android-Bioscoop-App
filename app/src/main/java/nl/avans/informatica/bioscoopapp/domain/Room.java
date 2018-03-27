package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class Room {
    private int RoomId;
    private String Name;

    public Room(int RoomId, String Name){
        this.RoomId = RoomId;
        this.Name = Name;
    }

    public int getRoomId(){
        return this.RoomId;
    }

    public String getName(){
        return this.Name;
    }
}
