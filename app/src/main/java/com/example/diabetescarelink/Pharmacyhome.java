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

public class Pharmacyhome extends AppCompatActivity {

    LinearLayout ll;
    CardView nurselink,patient,orders;
    TextView header;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pharmacyhome);

        ll = findViewById(R.id.ll);
        header = findViewById(R.id.header);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        Intent intent = getIntent();
        String fullnames = intent.getStringExtra("fullnames");
        header.setText("Welcome home " +fullnames);

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
        orders =  findViewById(R.id.orders);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orders = new Intent(Pharmacyhome.this,Getorders.class);
                startActivity(orders);
            }
        });

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
                        Intent settings = new Intent(getApplicationContext(),pharmsettings.class);
                        startActivity(settings);
                        break;

                    case R.id.ic_logout:
                        Intent logout = new Intent(getApplicationContext(),pharmlogin.class);
                        startActivity(logout);
                        break;
                }



                return false;
            }
        });
    }
}