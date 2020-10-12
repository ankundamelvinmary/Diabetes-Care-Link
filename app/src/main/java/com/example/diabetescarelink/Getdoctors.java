package com.example.diabetescarelink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diabetescarelink.Adapters.DoctorsAdapter;
import com.example.diabetescarelink.Models.DoctorsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Getdoctors extends AppCompatActivity  {
    private static final String DOCTORS_LIST_URL = "http://192.168.1.104/dcl/getdoctors.php";
    RecyclerView recyclerView;
    List<DoctorsModel> mData;
    DoctorsAdapter adapter;
    TextView error_message,doctest;
    BottomNavigationView navigation;

String docLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_getdoctors);


        doctest = findViewById(R.id.doctest);
        error_message = findViewById(R.id.error_message);
      Toolbar toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      //get string
        docLocation = getIntent().getStringExtra("doclocation");
        doctest.setText(docLocation);

        //handle recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        mData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoctorsAdapter(this, mData);
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

            loaddoctors();


    }


    ////address and location

    private void loaddoctors() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DOCTORS_LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]");
                            JSONArray doctors = new JSONArray(response);

                            for (int i = 0; i < doctors.length(); i++) {
                                JSONObject object = doctors.getJSONObject(i);

                                String id = object.getString("id");
                                String name = object.getString("name");
                                String age = object.getString("age");
                                String sex = object.getString("sex");

                                String contact = object.getString("contact");
                                String email = object.getString("email");
                                String district = object.getString("district");
                                String hospital = object.getString("hospital");
                               // String occupation = object.getString("occupation");

                                DoctorsModel doctorsModel = new DoctorsModel(id, name, age, sex, contact, email, district,hospital);
                                mData.add(doctorsModel);
                                error_message.setVisibility(View.GONE);
                                //Orders_Model orders_model = new Orders_Model(food_name, food_description, food_price, id);
                                // mData.add(orders_model);
                            }

                            adapter = new DoctorsAdapter(Getdoctors.this, mData);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            error_message.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), " error" + error.toString(), Toast.LENGTH_SHORT).show();
//
            }
        }
        )
        {
            protected Map<String, String> getParams() {
                Map<String, String>params= new HashMap<>();
                params.put("district",docLocation);
//                params.put("quantity",quantity);
//                params.put("cost",cost);
//                params.put("pharmacy",pharmacy);


                return  params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}

