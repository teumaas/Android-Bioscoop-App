package nl.avans.informatica.bioscoopapp.util.interfaces;

import nl.avans.informatica.bioscoopapp.domain.SQLResult;

/**
 * Created by sjoer on 26-3-2018.
 */

public interface OnSQLResultAvailable {
    void onSQLResultAvailable(SQLResult sqlResult);
}
