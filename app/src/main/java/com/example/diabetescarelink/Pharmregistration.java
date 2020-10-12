package com.example.diabetescarelink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class Pharmregistration extends AppCompatActivity {

    TextView header,patient_reg,register_link;
    EditText fullnames,location,contact,email,password;
    Button register_btn;
    private static String SUBMIT_URL = "http://192.168.1.104/dcl/pharm.php";
    protected ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmregistration);

        header = findViewById(R.id.header);
        patient_reg = findViewById(R.id.patient_reg);
        register_link = findViewById(R.id.register_link);
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_link = new Intent(getApplicationContext(),pharmlogin.class);
                startActivity(register_link);
            }
        });
        fullnames = findViewById(R.id.fullnames);
        location = findViewById(R.id.location);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        register_btn = findViewById(R.id.submit);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sendpharmreg();
                fullnames.setText("");
                email.setText("");
                contact.setText("");
                location.setText("");
                password.setText("");
            }
        });
    }

    private void Sendpharmreg() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("sending_request please wait.....");
        loading.show();
        final String fullnames = this.fullnames.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String contact = this.contact.getText().toString().trim();
        final String location = this.location.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBMIT_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Request sent successfully", Toast.LENGTH_SHORT).show();
                            }
                            else  if (success.equals("2")) {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Either email or contact Already Exists.Check and try again!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Request not sent" + e, Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Request not sent" + error, Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                })

        {
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("fullnames",fullnames);
                params.put("email",email);
                params.put("contact",contact);
                params.put("location",location);
                params.put("password",password);
                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }
}