package com.example.vjezba5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    static private Integer requestCodeForLocation = 1;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private LocationRequest locationRequest;

    private TextView coordinates;
    private TextView updatedLocation;
    private TextView addressText;
    private TextView city;
    private TextView country;

    private String longtitude;
    private String altitude;

    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationCallback == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    updatedLocation = findViewById(R.id.updateLocationTextView);
                    //Log.d("provjera", updatedLocation.toString());
                    updatedLocation.setText(location.toString());
                }
            }
        };

        locationTask(fusedLocationClient);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void updateLocation() {
        geocoder = Geocoder.isPresent() ? new Geocoder(this) : null;
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                TextView textView = (TextView) findViewById(R.id.coordTextView);
                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                textView.setText(Double.toString(latitude) + "  " + Double.toString(longitude));

                if (geocoder != null) {
                    try {
                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);
                        if (addressList.size() >= 1) {
                            Address address = addressList.get(0);
                            city = findViewById(R.id.cityTextView);
                            city.setText(address.getAddressLine(0));

                            addressText = findViewById(R.id.adressTextView);
                            addressText.setText(address.getLocality());

                            country = findViewById(R.id.countryTextView);
                            country.setText(address.getCountryName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 100, locationListener);

    }

    private void setupLocationTracking(FusedLocationProviderClient fusedLocationClient) {
        if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null) {
                                longtitude = Double.toString(location.getLongitude());
                                altitude = Double.toString(location.getAltitude());

                                coordinates = findViewById(R.id.coordTextView);
                                coordinates.setText(" Longtitude: " + longtitude + "\n Altitude:    " + altitude);
                            }
                        }
                    });
            updateLocation();
        }
    }

    private Boolean hasLocationPermission(){
        return EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void locationTask(FusedLocationProviderClient fusedLocationClient) {
        if (hasLocationPermission()){
            setupLocationTracking(fusedLocationClient);
        }
        else
        {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_location),
                    requestCodeForLocation,
                    Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }
}