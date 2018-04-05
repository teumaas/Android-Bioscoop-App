package nl.avans.informatica.bioscoopapp;

import org.junit.Test;

import nl.avans.informatica.bioscoopapp.domain.Movie;

import static junit.framework.Assert.assertEquals;

/**
 * Created by sjoer on 5-4-2018.
 */

public class MovieTester {
    private final static String TAG = "MovieTester";
    private Movie m = new Movie("tt1365519", "The TestMovie", "69min", "2012", "Tom Smits en Sjoerd Schepers", "Dutch", "Action");

    @Test
    public void testApiKeyLengthValidity() {
        int actual = m.getMovieId().length();
        int expected = 9;
        assertEquals((TAG + ": MovieId length invalid"), expected, actual, 0.001);
    }

    @Test
    public void testTitleValueValidity() {
        String actual = m.getTitle();
        String expected = "The TestMovie";
        assertEquals((TAG + ": Titles do not match"), expected, actual);
    }

    @Test
    public void testYearValidity() {
        String actual = m.getYear();
        String expected = "2012";
        assertEquals((TAG + ": Years do not match"), expected, actual);
    }
}
