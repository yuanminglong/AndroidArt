package com.yuan.androidart.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.yuan.androidart.R;

import java.util.Date;
import java.util.List;

/**
 *
 <uses-permission android:name="android.permission.ACCESS_MOCK_LOCATION" />
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 */
public class MockLocationActivity extends AppCompatActivity {
    private static final String TAG = MockLocationActivity.class.getSimpleName();
    String [] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_CODE_PERMISSION = 100;
    List<String> allProviders;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M) {
           requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        }
        registerTestLocationProvider();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    setLocation();
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        
    }

    private void setLocation() {
        Location location;
        for ( String providerName : allProviders){
            if (providerName.equals("passive")){
                continue;
            }

            location = new Location(providerName);
            location.setLatitude(30.543068);  // 维度（度）
            location.setLongitude(104.067131); // 经度（度）
            location.setAltitude(30);  // 高程（米）
            location.setBearing(180);  // 方向（度）
            location.setSpeed(10);  //速度（米/秒）
            location.setAccuracy(0.1f);  // 精度（米）
            location.setTime(new Date().getTime());  // 本地时间
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                // Elapsed time can also be set using
                // mockLocation.setElapsedRealtimeNanos(System.nanoTime());
                // Elapsed time can be disregarded using
                // mockLocation.makeComplete();
            }
            locationManager.setTestProviderLocation(providerName,location);
        }

    }

    /**
     *             @NonNull String name, boolean requiresNetwork, boolean requiresSatellite,
     *             boolean requiresCell, boolean hasMonetaryCost, boolean supportsAltitude,
     *             boolean supportsSpeed, boolean supportsBearing, int powerRequirement, int accuracy)
     */

    private void registerTestLocationProvider() {
        setContentView(R.layout.activity_mock_location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       // locationManager.clearTestProviderLocation();
        //locationManager.addTestProvider();
        allProviders = locationManager.getAllProviders();
        for (String providerName: allProviders){
            LocationProvider provider = locationManager.getProvider(providerName);
            Log.d(TAG,providerName+ ": provider is null "  + (provider == null) );
            if ("passive".equals(providerName)) {
                continue;
            }
            try {
                locationManager.removeTestProvider(providerName);
            }catch (Exception e){
                e.printStackTrace();
            }

            if (provider != null ){
                locationManager.addTestProvider(provider.getName(),
                        provider.requiresNetwork(),
                        provider.requiresSatellite(),
                        provider.requiresCell(),
                        provider.hasMonetaryCost(),
                        provider.supportsAltitude(),
                        provider.supportsSpeed(),
                        provider.supportsBearing(),
                        provider.getPowerRequirement(),
                        provider.getAccuracy()
                );
                locationManager.setTestProviderEnabled(provider.getName(),true);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0 ;i < permissions.length; i ++ ){

        }
        registerTestLocationProvider();
    }
}