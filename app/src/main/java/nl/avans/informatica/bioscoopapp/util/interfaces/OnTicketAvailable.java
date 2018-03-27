package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.Ticket;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnTicketAvailable {
    void onTicketAvailable(Ticket ticket);
}
