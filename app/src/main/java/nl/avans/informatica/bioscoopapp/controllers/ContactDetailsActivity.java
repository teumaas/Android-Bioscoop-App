package nl.avans.informatica.bioscoopapp.controllers;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import nl.avans.informatica.bioscoopapp.R;

public class ContactDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        tv1 = (TextView) findViewById(R.id.contactHeader);
        tv2 = (TextView) findViewById(R.id.contactDescription);
        tv3 = (TextView) findViewById(R.id.contactDetailsHeader);
        tv4 = (TextView) findViewById(R.id.contactDetails);

        tv1.setText(R.string.contact_header);
        tv2.setText(R.string.contact_description);
        tv3.setText(R.string.contact_details_header);
        tv4.setText(R.string.contact_details);

        if(googleServicesAvailable()){
            Toast.makeText(this,"Connected to play services!", Toast.LENGTH_LONG).show();
            initMap();
        }
    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);

        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        } else if (api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    private void initMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocationZoom(51.585899, 4.793249, 16);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
        MarkerOptions options = new MarkerOptions().title("Lovensdijkstraat 61").position(ll);
        mGoogleMap.addMarker(options);
    }
}
