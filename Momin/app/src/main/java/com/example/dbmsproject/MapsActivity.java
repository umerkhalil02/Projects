package com.example.dbmsproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, CustomWindow.CustomWindowListener {
    BottomNavigationView bottomNavigationView;
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    public static final int REQUEST_CODE = 101;
    FusedLocationProviderClient fusedLocationProviderClient;
    boolean mLocationPermissions = false;
    ImageView about, update, add;
    TextView update_tv, add_tv;
    public static final String URL = "http://192.168.48.141/db/API_Show.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getLocationPermissions();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_mosque:
                        startActivity(new Intent(getApplicationContext(),AddLoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.update_timings:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about_us:
                        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    private void getDeviceLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissions) {
                Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location current_loc = (Location) task.getResult();
                            moveCamera(new LatLng(current_loc.getLatitude(), current_loc.getLongitude()), 16f);
                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: Security Exception: " + e.getMessage());
        }
    }
    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
    private void initMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);
    }
    private void getLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE);
                mLocationPermissions = true;
            } else {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissions = false;
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissions = true;
                    initMap();
                    return;
                }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray mosques = new JSONArray(response);
                                for (int i = 0; i< mosques.length();i++){
                                    JSONObject mosque = mosques.getJSONObject(i);
                                    String mosque_id =mosque.getString("mosqueId");
                                    String mosque_name = mosque.getString("Name");
                                    Double mosque_latitude = Double.parseDouble(mosque.getString("Latitude"));
                                    Double mosque_longitude = Double.parseDouble(mosque.getString("Longitude"));
                                    String sect = mosque.getString("Sect");
                                    LatLng LatLng = new LatLng(mosque_latitude, mosque_longitude);
                                    MarkerOptions marker = new MarkerOptions().position(LatLng).title(mosque_name).snippet(mosque_id);
                                    if(sect.equals("Hanafi Barelvi")) {
                                        marker.icon(bitmapDescriptorFrontVector(getApplicationContext(), R.drawable.ic_barelvi));
                                    }
                                    else if(sect.equals("Hanafi Deobandi")) {
                                        marker.icon(bitmapDescriptorFrontVector(getApplicationContext(), R.drawable.ic_deobandi));
                                    }
                                    else {
                                        marker.icon(bitmapDescriptorFrontVector(getApplicationContext(), R.drawable.ic_shia));
                                    }
                                    mMap.addMarker(marker);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng,16f));
                                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        @Override
                                        public boolean onMarkerClick(@NonNull Marker marker) {
                                            openDialog(marker);
                                            return false;
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                Log.d(TAG, "onResponse: "+ e.toString());
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MapsActivity.this, error.toString().toString(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(MapsActivity.this);
            requestQueue.add(stringRequest);
        }catch (Exception e){
            Log.d(TAG, "onMapReady:"+ e.toString());
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
        if(mLocationPermissions){
            getDeviceLocation();
            mMap.setMyLocationEnabled(true);
        }
    }
    public void openDialog(Marker marker) {
        CustomWindow customWindow = new CustomWindow();
        customWindow.show(getSupportFragmentManager(),"Custom Window");
        customWindow.name = marker.getTitle();
        customWindow.mosqueId = marker.getSnippet();
    }
    @Override
    public void applyTexts(TextView textView, String string) {
        textView.setText(string);
    }
    private BitmapDescriptor bitmapDescriptorFrontVector(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}





