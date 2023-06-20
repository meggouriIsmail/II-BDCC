package com.example.tp1_splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView username;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Account Info's");
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        home = findViewById(R.id.backHome);
        Bundle b = getIntent().getExtras();
        String usr = b.getString("username");
        username.setText("Welcome back: " + usr);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}