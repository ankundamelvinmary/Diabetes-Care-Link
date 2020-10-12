package com.example.diabetescarelink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class Shop extends AppCompatActivity {
    TextView drug,drugholder,pharm,pharmacyholder,quantities,quantityholder,costholder,seeprice,testing, testing2;
    ImageView add,minus;
    Button submit;
    int quantityAmount = 1;
    CardView patient_card;

    String drugname,quantity,cost,pharmacy;
    private static final String URL_SENDING = "http://192.168.1.104/dcl/selectdrug.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        drug = findViewById(R.id.drug);
        drugholder = findViewById(R.id.drugholderss);
        pharm = findViewById(R.id.pharm);
        pharmacyholder = findViewById(R.id.pharmacyholderss);
       quantities = findViewById(R.id.quantities);
        quantityholder = findViewById(R.id.quantityholderss);
        costholder = findViewById(R.id.costholderss);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        seeprice = findViewById(R.id.seeprice);
        testing = findViewById(R.id.testing);
        testing2 = findViewById(R.id.testing2);

        patient_card = findViewById(R.id.patient_card);
//
//        String boxes;
//        boxes= drug.getText().toString();
//        testing.setText(boxes);
//
//
//        String boxes2;
//        boxes2= drugholder.getText().toString();
//        testing2.setText(boxes2);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String testbox = testing.getText().toString();
//                String testbox2 = testing2.getText().toString();
//
//
//                Intent intent =new Intent(getApplicationContext(),Shop.class);
//
//                intent.putExtra("boxname",testbox);
//                intent.putExtra("boxname2",testbox2);
//
//                startActivity(intent);
//                Toast.makeText(Shop.this, "Your order has been sent. Wait for the confirmation notification", Toast.LENGTH_LONG).show();

                sending();
            }
        });

        drugholder.setText(getIntent().getStringExtra("drug"));
        costholder.setText(getIntent().getStringExtra("cost"));
        pharmacyholder.setText(getIntent().getStringExtra("name"));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityAdd();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityReduce();
            }
        });

        drugname = drugholder.getText().toString();
        pharmacy = pharmacyholder.getText().toString();
        cost = costholder.getText().toString();
        quantity = quantityholder.getText().toString();



    }


    private void sending(){




        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SENDING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]"); /*debuging tool*/
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {

                                Toast.makeText(getApplicationContext(), "Order successfully sent.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Shop.this, Getdrugs.class);
                                startActivity(intent);
                                finish();
                            }

                            else if (success.equals("2")) {

                                Toast.makeText(getApplicationContext(), "adddddeeedddddd ddddddddddddddddddddddnot , check and try again" , Toast.LENGTH_SHORT).show();
//                                loading.setVisibility(View.VISIBLE);
//                                signup_btn.setVisibility(View.VISIBLE);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), " errorsssssssssssssssssssss" + e.toString(), Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.VISIBLE);
//                            signup_btn.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
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
                params.put("drugname",drugname);
                params.put("quantity",quantity);
                params.put("cost",cost);
                params.put("pharmacy",pharmacy);


                return  params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }







    private void quantityReduce() {
        final String ucostreduce = costholder.getText().toString();
        if (ucostreduce.equals("1")) {
            costholder.setText(getIntent().getStringExtra("cost"));
        } else {
            if (quantityAmount > 1) {
                quantityAmount--;
                quantityholder.setText(String.valueOf(quantityAmount));
                int cost = Integer.parseInt(quantityholder.getText().toString()) * Integer.parseInt(costholder.getText().toString());
                seeprice.setText(String.valueOf(cost));
            }
        }

//        //getnthe actual cost
////        final String ucostreduce = costholder.getText().toString();
////        //hold the current int number
////        String intnumber = String.valueOf(quantityAmount);
////
////        //do the math
////        if (quantityAmount > 0) {
////            quantityAmount--;
////            quantityholder.setText(intnumber);
////            int sum = Integer.parseInt(quantityholder.getText().toString()) * Integer.parseInt(costholder.getText().toString());
////            //display the total
////            quantityholder.setText(String.valueOf(sum));
////        }

//        quantityAmount++;
//        quantityholder.setText(intnumber);
    }

    private void quantityAdd() {
        final String ucost = costholder.getText().toString();
        if (ucost.equals("0")) {
            costholder.setText(getIntent().getStringExtra("cost"));
        } else {
            quantityAmount++;
            quantityholder.setText(String.valueOf(quantityAmount));
            int cost = Integer.parseInt(quantityholder.getText().toString()) * Integer.parseInt(costholder.getText().toString());
            seeprice.setText(String.valueOf(cost));
        }
    }





}