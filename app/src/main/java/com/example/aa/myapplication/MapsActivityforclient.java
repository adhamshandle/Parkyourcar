package com.example.aa.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityforclient extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapforclient);
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Egypt = new LatLng(55555, 1075);
        mMap.addMarker(new MarkerOptions().position(Egypt).title("Marker in Egypt"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Egypt));
        LatLng sydney = new LatLng(1000,2000);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng Paris = new LatLng(7894,1275);
        mMap.addMarker(new MarkerOptions().position(Paris).title("Marker in Paris"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Paris));
    }
}
