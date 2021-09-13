package com.example.foodapp.FragmentsFolder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentMenuBinding;
import com.example.foodapp.detailedMenuActivity;

public class MenuFragment extends Fragment {

    FragmentMenuBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater,container,false);

        Intent intent = new Intent(getContext(), detailedMenuActivity.class);
        binding.NonVegMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Non_veg");
                startActivity(intent);
            }
        });

        binding.veganMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Vegan");
                startActivity(intent);
            }
        });

        binding.beveragesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Beverages");
                startActivity(intent);
            }
        });

        binding.mainDishMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Main_dish");
                startActivity(intent);
            }
        });

        binding.breakfastMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Breakfast");
                startActivity(intent);
            }
        });

        binding.dessertsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Desserts");
                startActivity(intent);
            }
        });

        binding.specialMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Special");
                startActivity(intent);
            }
        });

        binding.fastFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Fastfoods");
                startActivity(intent);
            }
        });

        binding.dinnerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Dinner");
                startActivity(intent);
            }
        });

        binding.healthyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu_name","Healthy");
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}