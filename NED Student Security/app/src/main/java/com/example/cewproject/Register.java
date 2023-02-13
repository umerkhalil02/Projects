package com.example.cewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    TextView textView;
    EditText name , email, phone, password, pri1, pri2, pri3;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userID;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.mname);
        textView = findViewById(R.id.textView);
        email = findViewById(R.id.memail);
        password = findViewById(R.id.mpassword);
        phone = findViewById(R.id.mphonenumber);
        pri1 = findViewById(R.id.mphonenumber1);
        pri2 = findViewById(R.id.mphonenumber2);
        pri3 = findViewById(R.id.mphonenumber3);
        register = findViewById(R.id.button);
        progressBar = findViewById(R.id.rprogressBar);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        if (fauth.getCurrentUser() != null ) {
            startActivity(new Intent(Register.this,Login.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Phone = phone.getText().toString();
                String priority1 = pri1.getText().toString();
                String priority2 = pri2.getText().toString();
                String priority3 = pri3.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(Email)){
                    email.setError("Please Enter Your Email");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Please Enter Your Password");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (Password.length()<6){
                    password.setError("Your Password must be of atleast 6 characters");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                fauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                            userID= fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name",Name);
                            user.put("Email",Email);
                            user.put("Phone",Phone);
                            user.put("Priority 1",priority1);
                            user.put("Priority 2",priority2);
                            user.put("Priority 3",priority3);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "User Created for: " + userID);
                                }
                            });
                            startActivity(new Intent(Register.this, Dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });


    }
}