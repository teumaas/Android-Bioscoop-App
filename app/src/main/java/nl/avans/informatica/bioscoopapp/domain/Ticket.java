package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 24-3-2018.
 */

public class Ticket {
    private int TicketId;
    private String Email;
    private String QRCode;
    private int ShowId;
    private int ChairId;

    public Ticket(int TicketId, String Email, String QRCode, int ShowId, int ChairId){
        this.TicketId = TicketId;
        this.Email = Email;
        this.QRCode = QRCode;
        this.ShowId = ShowId;
        this.ChairId = ChairId;
    }

    public int getTicketId(){
        return this.TicketId;
    }

    public String getEmail(){
        return this.Email;
    }

    public String getQRCode(){
        return this.QRCode;
    }

    public int getShowId(){
        return this.ShowId;
    }

    public int getChairId(){
        return this.ChairId;
    }
}
