package com.example.foodapp.FragmentsFolder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.databinding.FragmentBottomSheetBinding;
import com.example.foodapp.models.Foods;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomSheetFragment extends BottomSheetDialogFragment {


    FragmentBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(inflater,container,false);

//        String food_name = getActivity().getIntent().getExtras().getString("food_name");

//

        Bundle mArgs = getArguments();
        String food_name = mArgs.getString("food_name");
        int food_img = mArgs.getInt("food_img");
        String food_price = mArgs.getString("food_price");
        String food_category = mArgs.getString("food_category");

        binding.bottomSheetFoodName.setText(food_name);
        binding.bottomSheetImg.setImageResource(food_img);
        binding.bottomSheetPrice.setText(food_price);


        binding.bottomSheetPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Quantity = Integer.parseInt(binding.bottomSheetQuantity.getText().toString()) ;
                if(Quantity < 50){
                   Quantity++;
                    binding.bottomSheetQuantity.setText(String.valueOf(Quantity));
                    binding.bottomSheetPrice.setText(String.valueOf(Integer.parseInt(food_price)*Quantity));
                }

            }
        });

        binding.bottomSheetMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Quantity = Integer.parseInt(binding.bottomSheetQuantity.getText().toString()) ;
                if(Quantity > 1){
                   Quantity--;
                    binding.bottomSheetQuantity.setText(String.valueOf(Quantity));
                    binding.bottomSheetPrice.setText(String.valueOf(Integer.parseInt(food_price)*Quantity));
                }


            }
        });



        binding.bottomSheetAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Quantity = Integer.parseInt(binding.bottomSheetQuantity.getText().toString());

                DbHelper dbHelper = new DbHelper(getContext());
//
                Foods foods = new Foods();
                foods.setQuantity(Quantity);
                foods.setFood_name(food_name);
                foods.setFood_price(String.valueOf(Integer.parseInt(food_price)*Quantity));
                foods.setFood_category(food_category);
                foods.setFood_img(food_img);

                dbHelper.insertOrder(foods);
                Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show();

                dismissTheSheet();
            }
        });



        return  binding.getRoot();
    }

    public void dismissTheSheet(){
        dismiss();
    }
}