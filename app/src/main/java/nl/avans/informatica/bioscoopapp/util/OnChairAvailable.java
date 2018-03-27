package nl.avans.informatica.bioscoopapp.util;

import nl.avans.informatica.bioscoopapp.domain.Chair;

/**
 * Created by sjoer on 24-3-2018.
 */

public interface OnChairAvailable {
    void onChairAvailable(Chair chair);
}
