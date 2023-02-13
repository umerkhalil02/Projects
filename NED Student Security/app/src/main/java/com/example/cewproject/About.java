package com.example.cewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {
    ImageView farrukh, faizan, umer, hamza;
    TextView far, fai, ham, um, tap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        fai = findViewById(R.id.text_fai);
        far = findViewById(R.id.textView_far);
        ham = findViewById(R.id.text_ham);
        um = findViewById(R.id.text_um);
        faizan = findViewById(R.id.image_faizan);
        farrukh = findViewById(R.id.image_farrukh);
        hamza = findViewById(R.id.image_hamza);
        umer = findViewById(R.id.image_umer);
        tap = findViewById(R.id.textView_tap);
        farrukh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                um.setText("UMER");
                ham.setText("HAMZA");
                fai.setText("FAIZAN");
                far.setText("");
                tap.setText("HELLO! I am Farrukh Siddiqui.");
            }
        });

        faizan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                far.setText("FARRUKH");
                um.setText("UMER");
                ham.setText("HAMZA");
                fai.setText("");
                tap.setText("HELLO! I am Muhammad Faizan .");
            }
        });
        hamza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fai.setText("FAIZAN");
                far.setText("FARRUKH");
                um.setText("UMER");
                ham.setText("");
                tap.setText("HELLO! I am Hamza Naveed.");
            }
        });
        umer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ham.setText("HAMZA");
                fai.setText("FAIZAN");
                far.setText("FARRUKH");
                um.setText("");
                tap.setText("HELLO! I am Umer Khalil.");
            }
        });
    }
}