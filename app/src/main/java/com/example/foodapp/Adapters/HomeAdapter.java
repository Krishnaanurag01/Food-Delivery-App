package com.example.foodapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.FragmentsFolder.BottomSheetFragment;
import com.example.foodapp.R;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.models.Foods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<Foods> foodsArrayList;

    public HomeAdapter(Context context, ArrayList<Foods> foodsArrayList) {
        this.context = context;
        this.foodsArrayList = foodsArrayList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_food_home , parent , false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Foods foods = foodsArrayList.get(position);
        holder.food_price.setText(foods.getFood_price());
        holder.food_category.setText(foods.getFood_category());
        holder.food_name.setText(foods.getFood_name());
        holder.food_image.setImageResource(foods.getFood_img());
//        Picasso.get().load(foods.getFood_img()).placeholder(R.drawable.food_not_available).into(holder.food_image);

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("food_name", foods.getFood_name());
                args.putInt("food_img",foods.getFood_img());
                args.putString("food_price",foods.getFood_price());
                args.putString("food_category",foods.getFood_category());

                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.setArguments(args);
                bottomSheetFragment.show(((FragmentActivity)context).getSupportFragmentManager(),bottomSheetFragment.getTag());

//                Intent intent = new Intent(context,BottomSheetFragment.class);
//                intent.putExtra("food_name",foods.getFood_name());
//                intent.putExtra("food_image",foods.getFood_img());
//                context.startActivity(intent);

//                DbHelper dbHelper = new DbHelper(context);
//                dbHelper.insertOrder(foods);
//
//                Toast.makeText(context, "Added!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView food_image;
        TextView food_name , food_category , food_price;
        TextView addToCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.CardView_Image);
            food_name = itemView.findViewById(R.id.CardView_Dish_Name);
            food_category = itemView.findViewById(R.id.CardView_Category);
            food_price = itemView.findViewById(R.id.CardView_Money);
            addToCart = itemView.findViewById(R.id.add_TO_cart);
        }
    }
}
