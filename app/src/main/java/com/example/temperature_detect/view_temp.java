package com.example.temperature_detect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class view_temp extends AppCompatActivity {
    private view_temp viewTemp;
    public Detail detail;
    private TextView roomtemp, roomhuminity, bodypulse, bodytemp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_temp);


        roomtemp = (TextView) findViewById(R.id.txt_room_temp);
        roomhuminity = (TextView) findViewById(R.id.txt_huminity_temp);
        bodypulse = (TextView) findViewById(R.id.txt_body_pulse);
        bodytemp = (TextView) findViewById(R.id.txt_body_temp);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RoomHumi", dataSnapshot.child("RoomHumi").getValue(Double.class).toString());
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.getKey();
                    double value = ds.getValue(Double.class);
                    Log.d("TAG" , name + "/" + value);

                    String rtemp =  dataSnapshot.child("RoomHumi").getValue(Double.class).toString();
                    String rhumidity = dataSnapshot.child("RoomTemp").getValue(Double.class).toString();
                    String bpulse = dataSnapshot.child("bodyPulse").getValue(Double.class).toString();
                    String btemp = dataSnapshot.child("bodyTemp").getValue(Double.class).toString();
                    roomtemp.setText(rtemp);
                    roomhuminity.setText(rhumidity);
                    bodypulse.setText(bpulse);
                    bodytemp.setText(btemp);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        };
        rootRef.addListenerForSingleValueEvent(valueEventListener);
    }


}