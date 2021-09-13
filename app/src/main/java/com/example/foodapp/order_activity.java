package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.databinding.ActivityOrderBinding;
import com.example.foodapp.models.Address;
import com.example.foodapp.models.Foods;
import com.example.foodapp.models.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class order_activity extends AppCompatActivity {

    ActivityOrderBinding binding ;
    FirebaseDatabase firebaseDatabase ;
    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DbHelper dbHelper = new DbHelper(order_activity.this);
        ArrayList<Foods> dbData = dbHelper.getOrders();

        int total_price = 0;

        String food_name = "";

        int quantity = 0 ;

        for (Foods f: dbData) {
            food_name += f.getFood_name() +" => " + f.getQuantity() +" , ";
            total_price += Integer.parseInt( f.getFood_price() );
        }

        final String fname_and_quant = food_name ;
        final int total_price_of_order = total_price ;



        binding.totalCost.setText(String.valueOf(total_price));

        if(total_price > 199){
            binding.deliveryCharge.setText("0");
            binding.taxes.setText("7");
        }
        else{
            binding.deliveryCharge.setText("40");
        }

        double gt = total_price + Integer.parseInt(binding.deliveryCharge.getText().toString())
                +  ( total_price * Integer.parseInt(binding.taxes.getText().toString()) )/100 ;
        binding.grandTotal.setText(String.valueOf(gt));


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        binding.orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (! binding.editTextName.getText().toString().equalsIgnoreCase(""))
                {
                    if (! binding.editTextNumber.getText().toString().equalsIgnoreCase("")){
                        if (!binding.editTextAdd.getText().toString().equalsIgnoreCase("")){
                            if (!binding.editTextColony.getText().toString().equalsIgnoreCase("")){
                                if (!binding.editTextLandmark.getText().toString().equalsIgnoreCase("")){

                                    Orders orders = new Orders() ;
                                    orders.setFood_name(fname_and_quant);
                                    orders.setTotal_price(total_price_of_order);
                                    orders.setFull_name(binding.editTextName.getText().toString());
                                    orders.setBuilding(binding.editTextAdd.getText().toString());
                                    orders.setColony(binding.editTextColony.getText().toString());
                                    orders.setLandmark(binding.editTextLandmark.getText().toString());
                                    orders.setPhone_number(binding.editTextNumber.getText().toString());

                                    firebaseDatabase.getReference().child("Orders")
                                            .child(firebaseAuth.getUid())
                                            .setValue(orders);

                                    Toast.makeText(order_activity.this,"Order Successful",Toast.LENGTH_SHORT).show();


//                                     saving the orders.


                                     firebaseDatabase.getReference().child("Users")
                                            .child(firebaseAuth.getUid())
                                            .child("My orders")
                                             .setValue(orders);







                                    // saving address ;

                                   if( binding.checkBox.isChecked() ) {
                                       Address address = new Address() ;
                                       address.setBuilding(binding.editTextAdd.getText().toString());
                                       address.setColony(binding.editTextColony.getText().toString());
                                       address.setLandmark(binding.editTextLandmark.getText().toString());
                                       address.setNumber(binding.editTextNumber.getText().toString());
                                       address.setName(binding.editTextName.getText().toString());

                                       firebaseDatabase.getReference().child("Users")
                                               .child(firebaseAuth.getUid())
                                               .child("Saved Address")
                                               .setValue(address);
                                   }

                                    Intent intent = new Intent(order_activity.this , MainScreen.class);
                                    startActivity(intent);

                                }
                                else {
                                    binding.editTextLandmark.setError("Can't be empty.");
                                }
                            }
                            else {
                                binding.editTextColony.setError("Can't be empty.");
                            }

                        }
                        else {

                            binding.editTextAdd.setError("Can't be empty.");
                        }
                    }
                    else {
                        binding.editTextNumber.setError("Can't be empty.");
                    }

                }
                else {
                    binding.editTextName.setError("Can't be empty.");
            }
            }
        });



    }
}