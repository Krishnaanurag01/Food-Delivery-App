package com.example.foodapp.FragmentsFolder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Adapters.HomeAdapter;
import com.example.foodapp.R;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.databinding.FragmentHomeBinding;
import com.example.foodapp.models.Foods;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);


        ArrayList<Foods> foodsArrayList = new ArrayList<>();
        HomeAdapter homeAdapter = new HomeAdapter(getContext(),foodsArrayList);

        binding.HomeRecyclerView.setAdapter(homeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.HomeRecyclerView.setLayoutManager(linearLayoutManager);


//        foodsArrayList.add(new Foods("https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1547&q=80","Burger" ,"Fast food","233"));


//        foodsArrayList.add( );
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));
//        foodsArrayList.add( new Foods("Chesse Pizza" , "Fast Food ,Delicious","450"));

       // SQL data insertion.



        DbHelper dbHelper = new DbHelper(getContext());


//
//        dbHelper.insertData(new Foods(R.drawable.food1,"Chesse Pizza" , "Vegan","450"));
//        dbHelper.insertData(new Foods(R.drawable.food1,"Chesse Pizza" , "Vegan","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food2,"Soup" , "Beverages","450"));
//        dbHelper.insertData(new Foods(R.drawable.food2,"Soup" , "Beverages","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food3,"Chesse Pizza" , "Main_dish","450"));
//        dbHelper.insertData(new Foods(R.drawable.food3,"Chesse Pizza" , "Main_dish","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Breakfast","450"));
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Breakfast","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food5,"Chesse Pizza" , "Desserts","450"));
//        dbHelper.insertData(new Foods(R.drawable.food5,"Chesse Pizza" , "Desserts","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Non_veg","450"));
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Non_veg","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Special","450"));
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Special","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Fastfoods","450"));
//        dbHelper.insertData(new Foods(R.drawable.food_not_available,"Chesse Pizza" , "Fastfoods","450"));
//
//
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Dinner","450"));
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Dinner","450"));
//
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Healthy","450"));
//        dbHelper.insertData(new Foods(R.drawable.food4,"Chesse Pizza" , "Healthy","450"));
//




//






//         deleting food by name
//        dbHelper.deleteDB("Soup");
//        dbHelper.deleteDB("Chesse Pizza");


        // taking data from database and adding it to a list and then sending it further to the adapter.

        ArrayList<Foods> dbData = dbHelper.getData();


        foodsArrayList.clear();


        for (Foods f: dbData) {
            foodsArrayList.add(f);
        }
//        foodsArrayList.clear();


        return binding.getRoot();
    }
}