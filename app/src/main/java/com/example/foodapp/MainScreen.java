package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivityMainScreenBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    ActivityMainScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
    }

    // creting menu.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // menu option .
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout_menu:
                mAuth.signOut();
                Intent intent = new Intent(MainScreen.this,loginactivity.class);
                startActivity(intent);
                break;

            default:
                Toast.makeText(MainScreen.this, "Watchu trying to do ? dumbo!", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }
}