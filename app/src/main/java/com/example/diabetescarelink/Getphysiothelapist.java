package com.example.diabetescarelink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diabetescarelink.Adapters.PhysiothelapistsAdapter;
import com.example.diabetescarelink.Models.PhysiothelapistsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Getphysiothelapist extends AppCompatActivity {
    private static final String PHYSIOTHELAPISTS_LIST_URL = "http://192.168.1.104/dcl/getphysiothelapists.php";
    RecyclerView recyclerView;
    List<PhysiothelapistsModel> mData;
    PhysiothelapistsAdapter adapter;
    TextView error_message,physiothelapisttest;
    BottomNavigationView navigation;

    String physiothelapistLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphysiothelapist);

        error_message = findViewById(R.id.error_message);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        physiothelapisttest = findViewById(R.id.physiothelapisttest);

        //get string
        physiothelapistLocation = getIntent().getStringExtra("physiothelapistlocation");
        physiothelapisttest.setText(physiothelapistLocation);


        //handle recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        mData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PhysiothelapistsAdapter(this, mData);
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
        loadphysiothelapist();

    }

    private void loadphysiothelapist() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PHYSIOTHELAPISTS_LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]");
                            JSONArray physiothelapists = new JSONArray(response);
                            for (int i = 0; i < physiothelapists.length(); i++) {
                                JSONObject object = physiothelapists.getJSONObject(i);
                                String id = object.getString("id");
                                String fullnames = object.getString("fullnames");
                                String contact = object.getString("contact");
                                String email = object.getString("email");
                                String location = object.getString("location");

                                PhysiothelapistsModel physiothelapistsModel = new PhysiothelapistsModel(id,fullnames,email,contact,location);
                                mData.add(physiothelapistsModel);
                                error_message.setVisibility(View.GONE);
                                //Orders_Model orders_model = new Orders_Model(food_name, food_description, food_price, id);
                                // mData.add(orders_model);
                            }

                            adapter = new PhysiothelapistsAdapter(Getphysiothelapist.this, mData);
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
                Toast.makeText(Getphysiothelapist.this, "could not load, please check your internet connection and try again", Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
//    protected Map<String, String> getParams() {
//        Map<String, String>params= new HashMap<>();
//        params.put("district",physiothelapistLocation);
////                params.put("quantity",quantity);
////                params.put("cost",cost);
////                params.put("pharmacy",pharmacy);
//
//
//        return  params;
//
//    }
//}
