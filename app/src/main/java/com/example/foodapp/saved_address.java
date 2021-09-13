package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivitySavedAddressBinding;
import com.example.foodapp.models.Address;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class saved_address extends AppCompatActivity {

    ActivitySavedAddressBinding binding ;
    FirebaseDatabase database ;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        database.getReference().child("Users")
                .child(firebaseAuth.getUid())
                .child("Saved Address")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Address address = snapshot.getValue(Address.class);

                        binding.editTextAdd.setText(address.getBuilding());
                        binding.editTextColony.setText(address.getColony());
                        binding.editTextLandmark.setText(address.getLandmark());
                        binding.editTextName.setText(address.getName());
                        binding.editTextNumber.setText(address.getNumber());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });












        binding.updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = new Address() ;
                address.setBuilding(binding.editTextAdd.getText().toString());
                address.setColony(binding.editTextColony.getText().toString());
                address.setLandmark(binding.editTextLandmark.getText().toString());
                address.setNumber(binding.editTextNumber.getText().toString());
                address.setName(binding.editTextName.getText().toString());

                database.getReference().child("Users")
                        .child(firebaseAuth.getUid())
                        .child("Saved Address")
                        .setValue(address);

                Toast.makeText(saved_address.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(saved_address.this,MainScreen.class);
                startActivity(intent);
            }
        });




    }
}