package com.example.tp1_splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Account");
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usrname);
        password = findViewById(R.id.pswd);
        login = findViewById(R.id.login);
        login.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("username", username.getText().toString());
            Intent i = new Intent(this, HomeActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        });

    }
}