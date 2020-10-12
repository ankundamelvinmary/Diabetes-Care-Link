package com.example.diabetescarelink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    TextView header,create_header,login_link;
    EditText names,email,contact,username,password;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        header = findViewById(R.id.header);
        create_header = findViewById(R.id.create_header);
        login_link = findViewById(R.id.login_link);
        names = findViewById(R.id.names);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register_btn = findViewById(R.id.submit);

        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

    }
}