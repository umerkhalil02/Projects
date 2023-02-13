package com.example.dbmsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    String email;
    ListView listView;
    ImageView imageView;
    BottomNavigationView bottomNavigationView;
    CustomAdapter customAdapter;
    public static ArrayList<Mosque> mosque = new ArrayList<>();
    public static final String URL = "http://192.168.48.141/db/API_Mosques.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
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
        listView = findViewById(R.id.listview);
        customAdapter = new CustomAdapter(UpdateActivity.this,mosque);
        retrieveData();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mosque selected = (Mosque) parent.getItemAtPosition(position);
                String mosque_id = selected.id;
                String mosque_name = selected.name;
                Intent intent = new Intent(UpdateActivity.this,UpdateTimeActivity.class);
                intent.putExtra("id", mosque_id);
                intent.putExtra("name", mosque_name);
                startActivity(intent);
            }
        });
    }
    public void retrieveData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                customAdapter.clear();
                mosque.clear();
                try {
                    JSONArray mosques = new JSONArray(response);
                    for(int i=0; i<mosques.length();i++){
                        JSONObject jsonObject = mosques.getJSONObject(i);
                        String id = jsonObject.getString("mosqueId");
                        String name = jsonObject.getString("Name");
                        String address = jsonObject.getString("Address") +", " + jsonObject.getString("City");
                        Mosque mosque_obj = new Mosque(id, name, address);
                        mosque.add(mosque_obj);
                        customAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.e("U", "onResponse: "+e.getMessage());
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
                    para.put("imamId",email);
                    return para;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
    }

}