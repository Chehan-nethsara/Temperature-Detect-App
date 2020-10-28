package com.example.temperature_detect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView roomtemp, roomhuminity, bodypulse, bodytemp;
    private DatabaseReference mDatabase;
    static ConstraintLayout container;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_temp);


//        Firebase.setAndroidContext(this);
        mDatabase = FirebaseDatabase.getInstance().getReference("heat-stroke-device");

        roomtemp = (TextView) findViewById(R.id.txt_room_temp);
        roomhuminity = (TextView) findViewById(R.id.txt_room_huminity);
        bodypulse = (TextView) findViewById(R.id.txt_body_pulse);
        bodytemp = (TextView) findViewById(R.id.txt_body_temp);
        container =findViewById(R.id.tempo);

        mDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Detail detail = postSnapshot.getValue(Detail.class);

                    String rtemp = detail.getRoomtemp();
                    String rhumidity = detail.getRoomhumidity();
                    String bpulse = detail.getBodypulse();
                    String btemp = detail.getBodytemp();

                    roomtemp.setText(rtemp);
                    roomhuminity.setText(rhumidity);
                    bodypulse.setText(bpulse);
                    bodytemp.setText(btemp);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                 [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });



    }
}