package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.Reservation;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnReservationAvailable {
    void onReservationAvailable(Reservation reservation);
}
