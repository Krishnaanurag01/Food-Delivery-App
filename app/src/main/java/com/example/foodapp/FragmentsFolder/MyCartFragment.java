package com.example.foodapp.FragmentsFolder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.Adapters.CartAdapter;
import com.example.foodapp.R;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.databinding.FragmentMyCartBinding;
import com.example.foodapp.models.Foods;
import com.example.foodapp.order_activity;

import java.util.ArrayList;

public class MyCartFragment extends Fragment {


    FragmentMyCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyCartBinding.inflate(inflater,container,false);

        ArrayList<Foods> cart_arrayList = new ArrayList<>();
        CartAdapter cartAdapter = new CartAdapter(getContext(),cart_arrayList);

        binding.CartRecyclerView.setAdapter(cartAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.CartRecyclerView.setLayoutManager(linearLayoutManager);


        DbHelper dbHelper = new DbHelper(getContext());

        ArrayList<Foods> dbData = dbHelper.getOrders();

//        int total_Price = 0;

        for (Foods f: dbData) {
            cart_arrayList.add(f);

//           total_Price += Integer.parseInt( f.getFood_price() );
        }

//        cart_arrayList.clear();
        cartAdapter.notifyDataSetChanged();


        Bundle bundle = this.getArguments();

        if(bundle != null) {
            int p = bundle.getInt("total");
//            binding.totalCost.setText(String.valueOf(p));
        }







//        binding.totalCost.setText(String.valueOf(total_Price));


        binding.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutManager.getItemCount() > 0 ) {


                    Intent intent = new Intent(getContext(), order_activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "Select food first you dumbass!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if(linearLayoutManager.getItemCount() > 0){
            binding.emptyCartText.setVisibility(View.INVISIBLE);
        }






        return binding.getRoot();
    }
}