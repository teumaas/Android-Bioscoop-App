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

import nl.avans.informatica.bioscoopapp.domain.Ticket;
import nl.avans.informatica.bioscoopapp.util.interfaces.OnTicketAvailable;

/**
 * Created by sjoer on 24-3-2018.
 */

public class GetTicketsRequest extends AsyncTask<String, Void, String> {
    private final static String TAG = "GetTicketsRequest";
    private OnTicketAvailable listener = null;

    public GetTicketsRequest(OnTicketAvailable listener){
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

            JSONArray tickets = jsonObject.getJSONArray("Tickets");

            for(int idx = 0; idx < tickets.length(); idx++){
                JSONObject Ticket = new JSONObject(tickets.getString(idx));

                int TicketId = Ticket.getInt("TicketId");
                String Email = Ticket.getString("Email");
                String QRCode = Ticket.getString("QRCode");
                int ShowId = Ticket.getInt("ShowId");
                int ChairId = Ticket.getInt("ChairId");

                Ticket TicketObject = new Ticket(TicketId, Email, QRCode, ShowId, ChairId);

                listener.onTicketAvailable(TicketObject);
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
