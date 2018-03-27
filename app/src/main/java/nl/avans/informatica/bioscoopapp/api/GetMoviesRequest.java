package nl.avans.informatica.bioscoopapp.api;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import nl.avans.informatica.bioscoopapp.domain.Movie;
import nl.avans.informatica.bioscoopapp.util.OnMovieAvailable;

/**
 * Created by sjoer on 23-3-2018.
 */

public class GetMoviesRequest extends AsyncTask<String, Void, String> {
    private final static String TAG = "GetMoviesRequest";
    private OnMovieAvailable listener = null;

    public GetMoviesRequest(OnMovieAvailable listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        int responsCode = -1;
        String movieUrl = params[0];
        String response = "";
        Log.d(TAG, "Variables were made");

        try {
            URL url = new URL(movieUrl);
            URLConnection urlConnection = url.openConnection();
            Log.d(TAG, "Try variables were made");

            if (!(urlConnection instanceof HttpURLConnection)){
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            Log.d(TAG, "Everything was prepared to make the httpConnection");

            httpConnection.connect();
            Log.d(TAG, "httpConnection.connect() was done");

            responsCode = httpConnection.getResponseCode();
            if (responsCode == httpConnection.HTTP_OK){
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.d(TAG, "doInbackground response = " + response);
            } else {
                Log.d(TAG, "Error, invalid response");
            }
        } catch (MalformedURLException e){
            Log.d(TAG, "doInBackground MalformedUrlException " + e.getLocalizedMessage());
            return null;
        } catch (IOException e){
            Log.d(TAG, "doInbackground IOException " + e.getLocalizedMessage());
            return null;
        }
        return response;
    }

    public void onPostExecute(String response) {
        Log.d(TAG, "onPostExecute was called");
        if (response == null || response == ""){
            return;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);

            JSONArray movies = jsonObject.getJSONArray("Movies");

            for(int idx = 0; idx < movies.length(); idx++){
                JSONObject Movie = new JSONObject(movies.getString(idx));

                String MovieId = Movie.getString("MovieId");
                String Title = Movie.getString("Title");
                String Year = Movie.getString("Year");
                String Plot = Movie.getString("Plot");
                String Language = Movie.getString("Language");
                String Genre = Movie.getString("Genre");
                String Image = Movie.getString("Image");

                Movie MovieObject = new Movie(MovieId, Title, Year, Language, Genre);
                MovieObject.setImage(Image);
                MovieObject.setPlot(Plot);

                listener.onMovieAvailable(MovieObject);
            }
        } catch (JSONException e) {
            Log.d(TAG, "onPostExecute JSONException " + e.getLocalizedMessage());
        }
    }

    private String getStringFromInputStream(InputStream inputStream){
        Log.d(TAG, "getStringFromInputStream was called");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}


