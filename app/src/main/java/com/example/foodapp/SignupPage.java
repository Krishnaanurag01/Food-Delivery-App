package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupPage extends AppCompatActivity {
    TextView alreadyHaveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        alreadyHaveLogin = findViewById(R.id.alreadyhavelogin);
        alreadyHaveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(SignupPage.this , loginactivity.class);
                startActivity(intent4);
            }
        });
    }
}