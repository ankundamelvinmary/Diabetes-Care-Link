package com.example.diabetescarelink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class dietitianregistration extends AppCompatActivity {
    TextView header,patient_reg,register_link;
    EditText fullnames,location,password,contact,email;
    Button register_btn;
    private ProgressBar loading;
    private String fullnames_val,email_val,contact_val,location_val,password_val;
    private static String URL_REGISTER = "http://192.168.1.104/dcl/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietitianregistration);

        loading = findViewById(R.id.loading);
        header = findViewById(R.id.header);
        patient_reg = findViewById(R.id.patient_reg);
        register_link = findViewById(R.id.register_link);
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_link = new Intent(getApplicationContext(),Dietitianlogin.class);
                startActivity(register_link);
            }
        });
        fullnames = findViewById(R.id.fullnames);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        register_btn = findViewById(R.id.submit);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_btn = new Intent(getApplicationContext(),Dietitianhome.class);
                startActivity(register_btn);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fullnames_val = fullnames.getText().toString().trim().toLowerCase();
                email_val = email.getText().toString().trim().toLowerCase();
                location_val = location.getText().toString().trim().toLowerCase();
                contact_val = contact.getText().toString().trim().toLowerCase();
                password_val = password.getText().toString().trim().toLowerCase();

            if (!fullnames_val.isEmpty() || !email_val.isEmpty() || !contact_val.isEmpty() || !location_val.isEmpty() ||
            !password_val.isEmpty()){

                Register();
            }
            else{

                fullnames.setError("Enter full names");
                email.setError("Enter Email Address");
                contact.setError("Enter Phone no");
                location.setError("Enter your location");
                password.setError("Enter password");

            }

            }
        });
    }

    private void Register(){

        loading.setVisibility(View.VISIBLE);
        register_btn.setVisibility(View.GONE);
        final String names = this.fullnames.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String location = this.location.getText().toString().trim();
        final String contact = this.contact.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr","["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                register_btn.setVisibility(View.VISIBLE);

                            }

                            else if (success.equals("2")) {
                                Toast.makeText(getApplicationContext(), "Either email or contact Already Exists.Check and try again!", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                register_btn.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Registration Error" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            register_btn.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Registration Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        register_btn.setVisibility(View.VISIBLE);
                    }
                }
        )
        {
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String>params = new HashMap<>();
                params.put("names",names);
                params.put("email",email);
                params.put("location",location);
                params.put("contact",contact);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley .newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}