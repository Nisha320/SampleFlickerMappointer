package com.example.com.sampleflickermappointer;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        progressBar=(ProgressBar)findViewById(R.id.progress);

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


         new place_makers().execute();


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                String st=marker.getId();
               int pos= Integer.parseInt(st.substring(1));

                Toast.makeText(MapsActivity.this,""+MainActivity.modelArrayList.get(pos).getTitle(),Toast.LENGTH_SHORT).show();


                return false;
            }
        });

    }


    public  class place_makers extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {

            MapsActivity.this.runOnUiThread(new Runnable() {
                public void run() {

            for(int i=0;i<MainActivity.modelArrayList.size();i++){

                LatLng sydney = new LatLng(Double.parseDouble(MainActivity.modelArrayList.get(i).getLatitude()), Double.parseDouble(MainActivity.modelArrayList.get(i).getLongitude()));
                mMap.addMarker(new MarkerOptions().position(sydney).title(MainActivity.modelArrayList.get(i).getOwnername()));

            }

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
        }
    }


}
