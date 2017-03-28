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
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        boolean error = false;
        String error_message = "";

        mMap = googleMap;

        Geocoder gc = new Geocoder(this);
        try {
            List<Address> addressList = gc.getFromLocationName("1858 Neil Ave, Columbus, OH 43210",1);
            if(addressList.size()>0) {
                Address address = addressList.get(0);
                LatLng berry = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(berry).title("Berry Cafe(Thompson Library)"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(berry));
            } else {
                Log.d("Yep", "OnMapReady: Failed to find Berry Cafe");
                error = true;
                error_message = "Failed to find Berry Cafe or map center";
            }
            gc = new Geocoder(this);
            addressList = gc.getFromLocationName("2000 Tuttle Park Place, Columbus, OH 43220",1);
            if(addressList.size()>0) {
                Address address = addressList.get(0);
                LatLng oxleys = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(oxleys).title("Oxleys"));
            } else {
                Log.d("Yep", "OnMapReady: Failed to find Oxleys or my house");
                error = true;
                if(error_message!="") {
                    error_message+="\n";
                }
                error_message += "Failed to find Oxleys";
            }

            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        } catch (IOException e) {
            e.printStackTrace();
            error = true;
            error_message = "The map could not be loaded";
            Log.d("Yep", "onMapReady: Failed on map creation");
        }
        if(error) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Error creating map")
                    .setMessage(error_message)
                    .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).create();
            dialog.show();
        }
    }
}
