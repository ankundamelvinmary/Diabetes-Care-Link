package com.example.diabetescarelink;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class
Home extends AppCompatActivity implements LocationListener {
    CardView healthcenter,nutrition,tips,bmi,pharmacy,followup,excercise,physiothelapy;
    LinearLayout ll;
    TextView header,cityholder, townholder, villageholder;
    SessionManager sessionManager;
    String names;
    Dialog myDialog;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        checkLocationIsEnabledorNot();
        grantPermission();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        myDialog = new Dialog(this);

        cityholder = findViewById(R.id.cityholder);
        townholder = findViewById(R.id.townholder);
        villageholder = findViewById(R.id.villageholder);


        HashMap<String, String> user = sessionManager.getUserDetail();
        names = user.get(SessionManager.NAME);

        header = findViewById(R.id.header);
        header.setText(names);

        Intent intent = getIntent();
        String fullnames = intent.getStringExtra("fullnames");
        header.setText("Welcome home " +fullnames);


        getLocation();


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
                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                            Home.this);
                    alertDialog2.setMessage("Are you sure you want to Logout?");
                    alertDialog2.setIcon(R.drawable.ic_logout);
                    alertDialog2.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent logout = new Intent(getApplicationContext(),Login.class);
                                    startActivity(logout);
                                    finish();
                                }
                            });
                    alertDialog2.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog2.show();

                        break;
                }



                return false;
            }
        });

        ll= findViewById(R.id.ll);
        healthcenter= findViewById(R.id.healthcenter);
        healthcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locholder = townholder.getText().toString();
                Intent healthcenter = new Intent(getApplicationContext(),Getnurses.class);
                healthcenter.putExtra("nurselocation",locholder);
                startActivity(healthcenter);

            }
        });

        nutrition = findViewById(R.id.nutrition);
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nutrition = new Intent(getApplicationContext(),Nutritionmonitor.class);
                startActivity(nutrition);

            }
        });

        tips= findViewById(R.id.tips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locholder = townholder.getText().toString();
                Intent tips = new Intent(getApplicationContext(),Getdoctors.class);
                tips.putExtra("doclocation",locholder);
                startActivity(tips);

            }
        });

        bmi= findViewById(R.id.bmi);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bmi = new Intent(getApplicationContext(),BMIcalculator.class);
                startActivity(bmi);

            }
        });

        pharmacy= findViewById(R.id.pharmacy);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pharmacy = new Intent(getApplicationContext(),Getdrugs.class);
                startActivity(pharmacy);

            }
        });

        followup= findViewById(R.id.followup);
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followup = new Intent(getApplicationContext(),Followup.class);
                startActivity(followup);

            }
        });

        excercise= findViewById(R.id.excercise);
        excercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent excercise = new Intent(getApplicationContext(),Excercisemonitor.class);
                startActivity(excercise);

            }
        });

        physiothelapy= findViewById(R.id.physiothelapy);
        physiothelapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent physiothelapy = new Intent(getApplicationContext(),Getphysiothelapist.class);
                startActivity(physiothelapy);

            }
        });



    }
    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        }
        catch (SecurityException e){
            e.printStackTrace();

        }
    }

    private void checkLocationIsEnabledorNot() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkenabled = false;


        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        }
        catch (Exception e){
            e.printStackTrace();

        }

        try {
            networkenabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        }
        catch (Exception e){
            e.printStackTrace();

        }

        if (!gpsEnabled && !networkenabled){
            new AlertDialog.Builder(Home.this)
                    .setTitle("Enable gps service")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }).setNegativeButton("Cancle", null)
                    .show();
        }


    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            cityholder.setText(address.get(0).getAdminArea());
            townholder.setText(address.get(0).getSubAdminArea());
            villageholder.setText(address.get(0).getAddressLine(0));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}