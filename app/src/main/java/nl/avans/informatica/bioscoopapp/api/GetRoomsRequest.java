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

import nl.avans.informatica.bioscoopapp.domain.Room;
import nl.avans.informatica.bioscoopapp.util.OnRoomAvailable;

/**
 * Created by sjoer on 24-3-2018.
 */

public class GetRoomsRequest extends AsyncTask<String, Void, String> {
    private final static String TAG = "GetRoomsRequest";
    private OnRoomAvailable listener = null;

    public GetRoomsRequest(OnRoomAvailable listener){
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

            JSONArray rooms = jsonObject.getJSONArray("Rooms");

            for(int idx = 0; idx < rooms.length(); idx++){
                JSONObject Room = new JSONObject(rooms.getString(idx));

                int RoomId = Room.getInt("RoomId");
                String Name = Room.getString("Name");

                Room RoomObject = new Room(RoomId, Name);

                listener.onRoomAvailable(RoomObject);
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
