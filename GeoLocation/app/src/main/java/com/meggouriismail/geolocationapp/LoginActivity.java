package com.meggouriismail.geolocationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Enregistrement des informations de connexion dans les préférences partagées
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_LOGIN, login);
                editor.putString(KEY_PASSWORD, password);
                editor.apply();

                // Naviguer vers l'écran principal de l'application
                Intent intent = new Intent(LoginActivity.this, FormActivity.class);
                startActivity(intent);
//                finish();
            }
        });

    }
}
