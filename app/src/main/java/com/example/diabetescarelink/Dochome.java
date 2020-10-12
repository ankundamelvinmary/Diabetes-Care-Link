package com.example.diabetescarelink;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dochome extends AppCompatActivity {

    LinearLayout ll;
    CardView nurselink,patient,pharmacy,followup,appointments,ddd;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flash);
        setContentView(R.layout.activity_dochome);

        ll = findViewById(R.id.ll);
        header = findViewById(R.id.header);

        Intent intent = getIntent();
        String lastname = intent.getStringExtra("lastname");
        header.setText("Welcome home " +lastname);

        nurselink = findViewById(R.id.nurselink);
        nurselink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nurselink = new Intent(getApplicationContext(),Nurselink.class);
                startActivity(nurselink);
            }
        });
        patient = findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patient = new Intent(getApplicationContext(),Getpatients.class);
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
                Intent followup = new Intent(getApplicationContext(),Followup.class);
                startActivity(followup);
            }
        });
        appointments = findViewById(R.id.appointments);
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appointments = new Intent(getApplicationContext(),Appointments.class);
                startActivity(appointments);
            }
        });
        ddd = findViewById(R.id.ddd);
        ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ddd = new Intent(getApplicationContext(), Getorders.class);
                startActivity(ddd);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent home = new Intent(getApplicationContext(),Dochome.class);
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
                        Intent settings = new Intent(getApplicationContext(),docsettings.class);
                        startActivity(settings);
                        break;

                    case R.id.ic_logout:
                        Intent logout = new Intent(getApplicationContext(),doclogin.class);
                        startActivity(logout);
                        break;
                }



                return false;
            }
        });
    }
}