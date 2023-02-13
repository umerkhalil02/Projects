package com.example.dbmsproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Register Activity";
    Button reg;
    EditText email, password, name;
    ImageView imageView;
    String mail, pass, imam, register;
    BottomNavigationView bottomNavigationView;
    public static final String URL = "http://192.168.48.141/db/API_Register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.add_mosque);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.about_us:
                        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.update_timings:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_mosque:
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
        reg = findViewById(R.id.bt_reg);
        email = findViewById(R.id.email);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        register = sdf.format(new Date());
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString();
                pass = password.getText().toString();
                imam = name.getText().toString();
                if ((TextUtils.isEmpty(mail))) {
                    email.setError("Please Enter an email!");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    password.setError("Please enter a password");
                    return;
                }
                if (pass.length() < 6) {
                    password.setError("Password must contain 6 characters!");
                    return;
                }
                try {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            if(response.equals("Imam created successfully!")){
                                startActivity(new Intent(getApplicationContext(),AddLoginActivity.class));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> para = new HashMap<String,String>();
                            para.put("imamId",mail);
                            para.put("Password",pass);
                            para.put("Name",imam);
                            para.put("Registered",register);
                            return para;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                } catch (Exception e) {
                    Log.e(TAG, "onClick: ", e);
                }
            }
        });
    }
}

