package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.foodapp.Adapters.HomeAdapter;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.databinding.ActivityDetailedMenuBinding;
import com.example.foodapp.models.Foods;

import java.util.ArrayList;

public class detailedMenuActivity extends AppCompatActivity {

    ActivityDetailedMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Foods> detailMenuItems = new ArrayList<>();



        DbHelper dbHelper = new DbHelper(detailedMenuActivity.this);



        String menuName = getIntent().getStringExtra("menu_name");

        ArrayList<Foods> dbList = dbHelper.getDataByCategory(menuName);



        for (Foods f: dbList) {

//            Log.d("Category", "onCreate: " +cat);
//            if (cat.equals("Non_veg")) {
                detailMenuItems.add(f);

        }


        HomeAdapter homeAdapter = new HomeAdapter(detailedMenuActivity.this , detailMenuItems );

        binding.detailedMenuRecyclerView.setAdapter(homeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        binding.detailedMenuRecyclerView.setLayoutManager(linearLayoutManager);



    }
}