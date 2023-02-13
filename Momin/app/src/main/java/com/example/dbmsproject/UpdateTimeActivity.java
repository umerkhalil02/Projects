package com.example.dbmsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateTimeActivity extends AppCompatActivity {

    TextView name, ftime, ztime, atime, mtime, itime, jtime;
    String mosque_id, mosque_name;
    ImageView imageView;
    Button update;
    BottomNavigationView bottomNavigationView;
    public static final String URL = "http://192.168.48.141/db/API_Update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time);
        Bundle bundle = getIntent().getExtras();
        mosque_id = bundle.getString("id");
        mosque_name = bundle.getString("name");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.update_timings);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_mosque:
                        startActivity(new Intent(getApplicationContext(),AddLoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.update_timings:
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
        name = findViewById(R.id.mos_name);
        name.setText(mosque_name);
        ftime = findViewById(R.id.FajrTime);
        ztime = findViewById(R.id.ZuhrTime);
        atime = findViewById(R.id.AsrTime);
        mtime = findViewById(R.id.MaghribTime);
        itime = findViewById(R.id.IshaTime);
        jtime = findViewById(R.id.JummaTime);
        ftime.setPaintFlags(ftime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ztime.setPaintFlags(ztime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        atime.setPaintFlags(atime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mtime.setPaintFlags(mtime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        itime.setPaintFlags(itime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        jtime.setPaintFlags(jtime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        timePicker(ftime);
        timePicker(ztime);
        timePicker(atime);
        timePicker(mtime);
        timePicker(itime);
        timePicker(jtime);
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

    }
    private void timePicker(TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
    public void updateTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        String fajr = ftime.getText().toString();
        String zuhr = ztime.getText().toString();
        String asr = atime.getText().toString();
        String maghrib = mtime.getText().toString();
        String isha =  itime.getText().toString();
        String jumma = jtime.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateTimeActivity.this,response,Toast.LENGTH_LONG).show();
                if(response.equals("Timings updated successfully!")){
                    startActivity(new Intent(UpdateTimeActivity.this,UpdateActivity.class));
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
                para.put("mosqueId",mosque_id);
                para.put("Fajr",fajr);
                para.put("Zuhr",zuhr);
                para.put("Asr",asr);
                para.put("Maghrib",maghrib);
                para.put("Isha",isha);
                para.put("Jumma",jumma);
                para.put("Last_Updated",date);
                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}