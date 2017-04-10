package edu.osu.osutrition;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search_Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        boolean error = false;
        String error_message = "";
        DatabaseHelper db = new DatabaseHelper(this);
        List<ArrayList<String>> locations = db.getLocations();

        mMap = googleMap;

        Geocoder gc = new Geocoder(this);
        try {
            for(ArrayList<String> entry: locations) {
                List<Address> addressList = gc.getFromLocationName(entry.get(1),1);
                if(addressList.size() > 0) {
                    Address address = addressList.get(0);
                    LatLng coord = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(coord).title(entry.get(0)));
                }
            }
            LatLng center = new LatLng(39.9991926, -83.0170512);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        } catch (IOException e) {
            e.printStackTrace();
            error = true;
            error_message = "The map could not be loaded";
            Log.d("Yep", "onMapReady: Failed on map creation");
        }
        if(error) {
            ErrorDisplay.makeError(this, "Error creating map", error_message, false);
        }
    }
}
