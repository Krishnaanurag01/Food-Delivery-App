package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodapp.FragmentsFolder.HomeFragment;
import com.example.foodapp.FragmentsFolder.MenuFragment;
import com.example.foodapp.FragmentsFolder.MyCartFragment;
import com.example.foodapp.FragmentsFolder.MyProfileFragment;
import com.example.foodapp.databinding.ActivityMainScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    ActivityMainScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();


//         home fragment.
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentReplacer,homeFragment);
        fragmentTransaction.commit();


        // naviagtion bar.

        bottomNavigationView = findViewById(R.id.nav_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){

                    case R.id.navbar_home:
                        fragmentTransaction.replace(R.id.fragmentReplacer,new HomeFragment());
                        break;

                    case R.id.navbar_menu:
                        fragmentTransaction.replace(R.id.fragmentReplacer, new MenuFragment());
                        break;

                    case R.id.navbar_cart:
                        fragmentTransaction.replace(R.id.fragmentReplacer, new MyCartFragment());
                        break;

                    case R.id.navbar_profile:
                        fragmentTransaction.replace(R.id.fragmentReplacer, new MyProfileFragment());
                        break;

                    default:
                        Toast.makeText(MainScreen.this, "Watchu trying to do?", Toast.LENGTH_SHORT).show();
                }
                fragmentTransaction.commit();

                return true;
            }
        });











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
                Intent intent = new Intent(MainScreen.this,MainActivity.class);
                startActivity(intent);
                break;


            default:
                Toast.makeText(MainScreen.this, "Watchu trying to do ? dumbo!", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }

//
//    public void switchToFragment1(){
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentReplacer , new HomeFragment()).commit();
//    }
//    public void switchToFragment2(){
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentReplacer , new MenuFragment()).commit();
//    }
//    public void switchToFragment3(){
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentReplacer , new MyCartFragment()).commit();
//    }
//    public void switchToFragment4(){
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentReplacer , new MyProfileFragment()).commit();
//    }
}

