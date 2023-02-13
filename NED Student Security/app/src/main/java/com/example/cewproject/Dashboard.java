package com.example.cewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    CircleImageView about, mentor, lout, help;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    Switch aSwitch, aSwitch1;
    FusedLocationProviderClient fusedLocationProviderClient;
    String userID;
    String n1;
    String t1;
    String t2;
    String t3;
    String ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        aSwitch = findViewById(R.id.switch1);
        aSwitch1 = findViewById(R.id.switch2);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ActivityCompat.checkSelfPermission(Dashboard.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Dashboard.this, "Location Checking", Toast.LENGTH_SHORT).show();
                } else
                    ActivityCompat.requestPermissions(Dashboard.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                if (ActivityCompat.checkSelfPermission(Dashboard.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Dashboard.this, "Location ON", Toast.LENGTH_SHORT).show();
                } else
                    ActivityCompat.requestPermissions(Dashboard.this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }
        });
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ActivityCompat.checkSelfPermission(Dashboard.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Dashboard.this, "SMS Service is ON", Toast.LENGTH_SHORT).show();
                } else
                    ActivityCompat.requestPermissions(Dashboard.this,
                            new String[]{Manifest.permission.SEND_SMS}, 100);
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        lout = findViewById(R.id.lout);
        help = findViewById(R.id.help);
        about = findViewById(R.id.aboutus);
        mentor = findViewById(R.id.mentor);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fauth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                   if (error == null){
                       n1 = documentSnapshot.getString("Name");
                       t1 = documentSnapshot.getString("Priority 1");
                       t2 = documentSnapshot.getString("Priority 2");
                       t3 = documentSnapshot.getString("Priority 3");
                   }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        help.setImageResource(R.drawable.help1);
                    }
                }, secondsDelayed * 500);
                help.setImageResource(R.drawable.help2);

                            }
                        }
                    });
                }else
                    Toast.makeText(Dashboard.this, "Location Service is turned OFF", Toast.LENGTH_SHORT).show();
            }
        });
        lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fauth.signOut();
                startActivity(new Intent(Dashboard.this,Login.class));
                finish();
                Toast.makeText(getApplicationContext(), "Logged Out!", Toast.LENGTH_SHORT).show();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,About.class));
            }
        });
        mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Mentor.class));
            }
        });
    }
    public void sendmessage() {
        if (ActivityCompat.checkSelfPermission(Dashboard.this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(Dashboard.this, "SMS Permission Checked", Toast.LENGTH_SHORT).show();
            Toast.makeText(Dashboard.this, "Message about to send ", Toast.LENGTH_SHORT).show();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(t1, null, n1 + " needs your help! His last location was " + ADDRESS, null, null);
            smsManager.sendTextMessage(t2, null,n1 + " needs your help! His last location was " + ADDRESS, null, null);
            smsManager.sendTextMessage(t3, null,n1 + " needs your help! His last location was " + ADDRESS, null, null);
            Toast.makeText(Dashboard.this, "Message Sent!", Toast.LENGTH_SHORT).show();
            Toast.makeText(Dashboard.this, ADDRESS, Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(Dashboard.this, "SMS Service is OFF", Toast.LENGTH_SHORT).show();
    }

}