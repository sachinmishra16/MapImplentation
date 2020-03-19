package com.blueapple.map_implementation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

 class MyLocationListener implements LocationListener {

     Context context;
     private GoogleMap mMap;
     double lat,longi;
     GoogleMap googleMap;

     public MyLocationListener(Context context) {
         this.context=context;
     }

     public void mapDetails(GoogleMap googleMap)
     {
         this.googleMap=googleMap;
     }

     @Override
    public void onLocationChanged(Location loc) {
        //editLocation.setText("");
        //pb.setVisibility(View.INVISIBLE);
        Toast.makeText(context
                ,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();

        longi=loc.getLongitude();
        Log.d("longitude", longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        lat=loc.getLatitude();
        Log.d("latitude", latitude);

         onMapReady(googleMap);


         /*------- To get city name from coordinates -------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                + cityName;
        // editLocation.setText(s);

         Log.d("current_address",s);

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

     public void onMapReady(GoogleMap googleMap) {
         mMap = googleMap;

         // Add a marker in Sydney and move the camera


         Log.d("lat_map", String.valueOf(lat));
         Log.d("long_map", String.valueOf(longi));

         if (mMap!=null) {

             LatLng noida = new LatLng(lat, longi);

             mMap.addMarker(new MarkerOptions().position(noida).title("Marker in India"));
             mMap.moveCamera(CameraUpdateFactory.newLatLng(noida));
         }
     }
}