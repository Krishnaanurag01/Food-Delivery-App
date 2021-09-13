package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodapp.databinding.ActivityMyOrderBinding;
import com.example.foodapp.models.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class my_order_activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase ;
    ActivityMyOrderBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase.getReference().child("Users")
                .child(firebaseAuth.getUid())
                .child("My orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Orders orders = snapshot.getValue(Orders.class);
                       binding.customerName.setText(orders.getFull_name());
                       binding.orderName.setText(orders.getFood_name());
                       binding.totalPrice.setText(String.valueOf(orders.getTotal_price()));
                       binding.number.setText(orders.getPhone_number());
                       binding.address.setText(orders.getBuilding() + orders.getColony() + orders.getLandmark());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });







    }
}