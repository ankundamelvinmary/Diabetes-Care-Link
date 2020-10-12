package com.example.diabetescarelink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nurselink extends AppCompatActivity {

    TextView header;
    LinearLayout ll;
    CardView nurselink,patient,pharmacy,followup;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurselink);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent home = new Intent(getApplicationContext(),Flash.class);
                        startActivity(home);
                        break;

                    case R.id.ic_help:
                        Intent help = new Intent(getApplicationContext(),Help.class);
                        startActivity(help);
                        break;

                    case R.id.ic_list:
                        Intent list = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(list);
                        break;

                    case R.id.ic_settings:
                        Intent settings = new Intent(getApplicationContext(),Settings.class);
                        startActivity(settings);
                        break;

                    case R.id.ic_logout:
                        Intent logout = new Intent(getApplicationContext(),Login.class);
                        startActivity(logout);
                        break;
                }



                return false;
            }
        });

        header = findViewById(R.id.header);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(),Home.class);
                startActivity(back);
            }
        });
        ll = findViewById(R.id.ll);
        nurselink = findViewById(R.id.nurselink);
        nurselink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nurselink = new Intent(getApplicationContext(),Doctorlink.class);
                startActivity(nurselink);
            }
        });
        patient = findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patient = new Intent(getApplicationContext(),Dietitianlink.class);
                startActivity(patient);
            }
        });
        pharmacy = findViewById(R.id.pharmacy);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pharmacy = new Intent(getApplicationContext(),Getpharmacies.class);
                startActivity(pharmacy);
            }
        });
        followup = findViewById(R.id.followup);
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followup = new Intent(getApplicationContext(),Physiothelapylink.class);
                startActivity(followup);
            }
        });
    }
}