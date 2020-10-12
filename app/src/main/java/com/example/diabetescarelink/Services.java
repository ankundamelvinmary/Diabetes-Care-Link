package com.example.diabetescarelink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Services extends AppCompatActivity {

    TextView contact;
    TextInputEditText nameholder,districtholder,ageholder,occupationholder,bmiholder,glucoseholder;
    Button submit;
    private static String SUBMIT_URL = "http://192.168.1.104/dcl/services.php";

    String cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        contact = findViewById(R.id.contact);

        nameholder = findViewById(R.id.nameholda);
        districtholder = findViewById(R.id.districtholda);
        ageholder = findViewById(R.id.ageholda);
        occupationholder = findViewById(R.id.occupationholda);
        bmiholder = findViewById(R.id.bmiholda);
        glucoseholder = findViewById(R.id.glucoseholda);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sendpatientreq();
                nameholder.setText("");
                districtholder.setText("");
                ageholder.setText("");
                occupationholder.setText("");
                bmiholder.setText("");
                glucoseholder.setText("");
            }
        });


        contact.setText(getIntent().getStringExtra("contact"));
cont = contact.getText().toString();
    }

    private void Sendpatientreq() {
        final String name = this.nameholder.getText().toString().trim();
        final String age = this.ageholder.getText().toString().trim();
        final String district = this.districtholder.getText().toString().trim();
        final String occupation = this.occupationholder.getText().toString().trim();
        final String glucose = this.glucoseholder.getText().toString().trim();
        final String bmi = this.bmiholder.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Request sent successfully", Toast.LENGTH_LONG).show();
                                Intent login = new Intent(getApplicationContext(),Getnurses.class);
                                startActivity(login);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Request not sent" + e, Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Request not sent" + error, Toast.LENGTH_LONG).show();

                    }
                })

        {
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("age",age);
                params.put("district",district);
                params.put("occupation",occupation);
                params.put("glucose",glucose);
                params.put("bmi",bmi);
                params.put("contact",cont);

                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}