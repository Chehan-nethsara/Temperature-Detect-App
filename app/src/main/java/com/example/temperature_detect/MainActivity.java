package com.example.temperature_detect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CardView cardView_temp;
    private CardView cardView_analyse;
    private CardView cardView_warningss;
    private CardView cardView_doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView_temp = (CardView) findViewById(R.id.cardView_temp);

        cardView_temp.setOnClickListener((v) -> {
            Intent intent= new Intent(MainActivity.this,view_temp.class);
            startActivity(intent);
        });
    }
}