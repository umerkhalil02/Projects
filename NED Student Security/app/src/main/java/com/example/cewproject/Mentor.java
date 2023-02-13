package com.example.cewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mentor extends AppCompatActivity {
    ImageView miss, board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        miss = findViewById(R.id.image_miss);
        board = findViewById(R.id.image_board);
        miss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animation = ObjectAnimator.ofFloat(miss, "translationX", 300f);
                animation.setDuration(500);
                animation.start();
                Toast.makeText(Mentor.this,"STUDENTS!",Toast.LENGTH_SHORT).show();
                board.setImageResource(R.drawable.board);
            }
        });

    }
}
