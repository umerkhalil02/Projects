package com.example.dbmsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        bottomNavigationView=  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.about_us);
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
                        return true;
                }
                return false;
            }
        });
        imageView  =findViewById(R.id.imageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
    }
}