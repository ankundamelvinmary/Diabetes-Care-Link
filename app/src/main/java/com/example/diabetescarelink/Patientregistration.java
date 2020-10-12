package com.example.diabetescarelink;

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

import androidx.appcompat.app.AppCompatActivity;

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

public class Patientregistration extends AppCompatActivity {

    TextView register_link,header,patient_reg;
    EditText fullnames,age,sex,district,village,maritalstatus,occupation,contact,password;
    Button submit;
    private static String SUBMIT_URL = "http://192.168.1.104/dcl/patientreg.php";
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientregistration);

        register_link = findViewById(R.id.register_link);
        header = findViewById(R.id.header);
        patient_reg = findViewById(R.id.patient_reg);
        fullnames = findViewById(R.id.fullnames);
        age = findViewById(R.id.age);
        sex = findViewById(R.id.sex);
        district = findViewById(R.id.district);
        village = findViewById(R.id.village);
        maritalstatus = findViewById(R.id.maritalstatus);
        occupation = findViewById(R.id.occupation);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_link = new Intent(getApplicationContext(),Login.class);
                startActivity(register_link);
            }
        });
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Sendpatientreg();
               fullnames.setText("");
               age.setText("");
               sex.setText("");
               contact.setText("");
               village.setText("");
               district.setText("");
               maritalstatus.setText("");
               occupation.setText("");
               password.setText("");
            }
        });



    }

    private void Sendpatientreg() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("sending_request please wait.....");
        loading.show();
        final String fullnames = this.fullnames.getText().toString().trim();
        final String age = this.age.getText().toString().trim();
        final String sex = this.sex.getText().toString().trim();
        final String contact = this.contact.getText().toString().trim();
        final String village = this.village.getText().toString().trim();
        final String district = this.district.getText().toString().trim();
        final String maritalstatus = this.maritalstatus.getText().toString().trim();
        final String occupation = this.occupation.getText().toString().trim();
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
                                Intent login = new Intent(getApplicationContext(),Login.class);
                                startActivity(login);
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
                params.put("age",age);
                params.put("sex",sex);
                params.put("contact",contact);
                params.put("village",village);
                params.put("district",district);
                params.put("maritalstatus",maritalstatus);
                params.put("occupation",occupation);
                params.put("password",password);
                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}