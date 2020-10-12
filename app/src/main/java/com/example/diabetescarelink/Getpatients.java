package com.example.diabetescarelink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diabetescarelink.Adapters.PatientsAdapter;
import com.example.diabetescarelink.Models.PatientsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Getpatients extends AppCompatActivity {
    private static final String PATIENTS_LIST_URL = "http://192.168.1.104/dcl/getpatients.php";
    RecyclerView recyclerView;
    List<PatientsModel> mData;
    PatientsAdapter adapter;
    TextView error_message;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.getpatients);

        error_message = findViewById(R.id.error_message);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //handle recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        mData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PatientsAdapter(this, mData);
        recyclerView.setAdapter(adapter);
        //bottom navigation part
        navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


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
        loadpatients();

    }

    private void loadpatients() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PATIENTS_LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]");
                            JSONArray patients = new JSONArray(response);

                            for (int i = 0; i < patients.length(); i++) {
                                JSONObject object = patients.getJSONObject(i);
                                String id = object.getString("id");
                                String fullnames = object.getString("fullnames");
                                String age = object.getString("age");
                                String sex = object.getString("sex");

                                String contact = object.getString("contact");
                                String village = object.getString("village");
                                String district = object.getString("district");
                                String maritalstatus = object.getString("maritalstatus");
                                String occupation = object.getString("occupation");

                                PatientsModel jobsModel = new PatientsModel(id, fullnames, age, sex, contact, village, district,maritalstatus,occupation);
                                mData.add(jobsModel);
                                error_message.setVisibility(View.GONE);
                                //Orders_Model orders_model = new Orders_Model(food_name, food_description, food_price, id);
                               // mData.add(orders_model);
                            }

                            adapter = new PatientsAdapter(Getpatients.this, mData);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                           error_message.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               error_message.setVisibility(View.VISIBLE);
               error_message.setVisibility(View.VISIBLE);
                Toast.makeText(Getpatients.this, "could not load, please check your internet connection and try again", Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

}


}