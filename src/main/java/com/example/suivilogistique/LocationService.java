package com.example.suivilogistique;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.IBinder;
import android.Manifest;
import android.app.Notification;

import android.content.pm.PackageManager;

import android.location.Location;

import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;



import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationService extends Service {

    public static String codeM = "null";
    FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    DatabaseReference reference;


    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

  /*  protected void createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();

        //new Notification();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createNotificationChanel() ;
        else startForeground(
                1,
                new Notification()
        );*/

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000000);
        locationRequest.setFastestInterval(4000000);
        locationRequest.setMaxWaitTime(4000000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {

                Location location =  locationResult.getLastLocation();
                Toast.makeText(getApplicationContext(),
                        "Lat: "+Double.toString(location.getLatitude()) + '\n' +
                                "Long: " + Double.toString(location.getLongitude()), Toast.LENGTH_LONG).show();
                List<Address> address;
                String ville="";
                String position="";
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault() );
                try {
                    address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(address.get(0).getThoroughfare()!=null) {
                        position = address.get(0).getThoroughfare() + "," + address.get(0).getLocality() + " " + address.get(0).getPostalCode() + "," + address.get(0).getCountryName();
                    }else{
                        position =  address.get(0).getLocality() + " " + address.get(0).getPostalCode() + "," + address.get(0).getCountryName();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                reference = FirebaseDatabase.getInstance().getReference("Database").child("Location");
                LocationUser U = new LocationUser(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),codeM,position);
                reference.push().setValue(U);
                }

        };
        startLocationUpdates();
    }


   /* @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanel() {
        String notificationChannelId = "Location channel id";
        String channelName = "Background Service";

        NotificationChannel chan = new NotificationChannel(
                notificationChannelId,
                channelName,
                NotificationManager.IMPORTANCE_NONE
        );
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = getSystemService(NotificationManager.class);

        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, notificationChannelId);

        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("Location updates:")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}