package nl.avans.informatica.bioscoopapp.domain;

/**
 * Created by sjoer on 26-3-2018.
 */

public class SQLResult {
    private boolean wasSuccessful;

    public SQLResult(boolean wasSuccessful){
        this.wasSuccessful = wasSuccessful;
    }

    public boolean getWasSuccessful(){
        return this.wasSuccessful;
    }

    @Override
    public String toString(){
        return "Successvalue: " + this.wasSuccessful;
    }
}
