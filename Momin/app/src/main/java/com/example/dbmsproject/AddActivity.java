package com.example.dbmsproject;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    String email, address, city;
    Double lat, longi;
    Button add;
    ImageView imageView;
    EditText mosque_name;
    TextView fajr_time, zuhr_time, asr_time, maghrib_time, isha_time, jumma_time;
    RadioGroup radioGroup;
    RadioButton radioButton;
    BottomNavigationView bottomNavigationView;
    public static final String URL = "http://192.168.48.141/db/API_Add.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("Email");
        lat = bundle.getDouble("Latitude");
        longi = bundle.getDouble("Longitude");
        address = bundle.getString("Address");
        city = bundle.getString("City");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.add_mosque);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_mosque:
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
        imageView  =findViewById(R.id.imageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
        mosque_name = findViewById(R.id.mosquename);
        fajr_time= findViewById(R.id.editTextFajrTime);
        zuhr_time = findViewById(R.id.editTextZuhrTime);
        asr_time = findViewById(R.id.editTextAsrTime);
        maghrib_time = findViewById(R.id.editTextMaghribTime);
        isha_time = findViewById(R.id.editTextIshaTime);
        jumma_time = findViewById(R.id.editTextJummaTime);
        timePicker(fajr_time);
        timePicker(zuhr_time);
        timePicker(asr_time);
        timePicker(maghrib_time);
        timePicker(isha_time);
        timePicker(jumma_time);
        radioGroup = findViewById(R.id.radiogroup);
        add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMosque();
            }
        });
    }
    private void timePicker(TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":"+ minute;
                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                        Date date = null;
                        try{
                            date = fmt.parse(time);
                        }catch (ParseException p){
                            Log.e("Add Activity", "onTimeSet: "+p.getMessage());
                        }
                        SimpleDateFormat fmtout = new SimpleDateFormat("hh:mm aa");
                        String formattedtime = fmtout.format(date).toUpperCase(Locale.ROOT);
                        textView.setText(formattedtime);
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });
    }
    private void addMosque(){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddActivity.this,response,Toast.LENGTH_LONG).show();
                if(response.equals("Mosque added successfully!")){
                    startActivity(new Intent(AddActivity.this,MapsActivity.class));
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> para = new HashMap<String,String>();
                para.put("Name",mosque_name.getText().toString());
                para.put("Registered",date);
                para.put("imamId",email);
                para.put("Sect",radioButton.getText().toString());
                para.put("Address",address);
                para.put("City",city);
                para.put("Latitude",String.valueOf(lat));
                para.put("Longitude",String.valueOf(longi));
                para.put("Fajr",fajr_time.getText().toString());
                para.put("Zuhr",zuhr_time.getText().toString());
                para.put("Asr",asr_time.getText().toString());
                para.put("Maghrib",maghrib_time.getText().toString());
                para.put("Isha",isha_time.getText().toString());
                para.put("Jumma",jumma_time.getText().toString());
                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}