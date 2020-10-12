package com.example.diabetescarelink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diabetescarelink.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Drugs_costs extends AppCompatActivity {

    TextView header;
    EditText drug, cost,place;
    Button submit;
    private ProgressBar loading;
    private String drug_val, cost_val,place_val;
    private static final String URL_DRUGSIGNUP= "http://192.168.1.104/dcl/getdrugs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drugs_costs);

        loading= findViewById(R.id.loading);
        header = findViewById(R.id.header);
        drug = findViewById(R.id.drug);
        cost = findViewById(R.id.cost);
        submit = findViewById(R.id.submit);
        place = findViewById(R.id.phamc);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),this.getClass());
                startActivity(intent);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drug_val = drug.getText().toString().trim().toLowerCase();
                place_val = place.getText().toString().trim().toLowerCase();
                cost_val = cost.getText().toString().trim().toLowerCase();


            if ( !drug_val.isEmpty() || !cost_val.isEmpty() || !place_val.isEmpty()){

                    Register();
                }
                else{

                    drug.setError("Enter Drugs name");
                    cost.setError("Enter Drugs cost");
                    place.setError("Enter Pharmacies with drug");

                }


            }
        });
    }


    private void Register(){

        loading.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        final String drug= this.drug.getText().toString().trim();
        final String cost= this.cost.getText().toString().trim();
        final String pham= this.place.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DRUGSIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr","["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                loading.setVisibility(View.GONE);
                                submit.setVisibility(View.VISIBLE);
//                                Toast.makeText(Drugs_costs.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Drugs_costs.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }

                            else if (success.equals("2")) {
                                Toast.makeText(getApplicationContext(), "Either drug Already Exists.Check and try again!", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                submit.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Registration Error" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Registration Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        submit.setVisibility(View.VISIBLE);
                    }
                }
        )
        {
            protected Map<String, String> getParams() {
                Map<String, String>params= new HashMap<>();
                params.put("drug",drug);
                params.put("cost",cost);
                params.put("place",pham);
                return  params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}