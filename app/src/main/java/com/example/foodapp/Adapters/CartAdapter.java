package com.example.foodapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.FragmentsFolder.MyCartFragment;
import com.example.foodapp.FragmentsFolder.updateBottomSheet;
import com.example.foodapp.R;
import com.example.foodapp.SQL_Data.DbHelper;
import com.example.foodapp.models.Foods;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context ;
    ArrayList<Foods> list ;

    public CartAdapter(Context context, ArrayList<Foods> list) {
        this.context = context;
        this.list = list;
    }

    public CartAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_cart_layout , parent ,false);
        return new ViewHolder(view);
    }

    int total_price = 0;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foods foods = list.get(position);

        holder.Order_Price.setText(foods.getFood_price());
        holder.Order_name.setText(foods.getFood_name());
        holder.Order_image.setImageResource(foods.getFood_img());
        holder.OrderQuantity.setText(String.valueOf(foods.getQuantity()));

        int index= position ;



        DbHelper dbHelper = new DbHelper(context);
        holder.Order_remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete items")
                        .setMessage("Are you sure want to delete this?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                dbHelper.delete_Order_ByID(foods.getFood_id());
                                Toast.makeText(context, "removed!", Toast.LENGTH_SHORT).show();
                                list.remove(index);
                                notifyDataSetChanged();

//                                MyCartFragment myCartFragment = new MyCartFragment();
//                                int p = getTotal_price(list) ;
//                                Bundle args = new Bundle();
//                                args.putInt("total",p);
//
//                                myCartFragment.setArguments(args);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });



        holder.QuantityCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args2 = new Bundle();
                args2.putInt("food_id",foods.getFood_id());
                args2.putString("food_name", foods.getFood_name());
                args2.putInt("food_img",foods.getFood_img());
                args2.putString("food_price",foods.getFood_price());
                args2.putString("food_category",foods.getFood_category());
                args2.putInt("food_quantity",foods.getQuantity());

                updateBottomSheet updateBottomSheet = new updateBottomSheet();
                updateBottomSheet.setArguments(args2);
                updateBottomSheet.show(((FragmentActivity)context).getSupportFragmentManager(),updateBottomSheet.getTag());



            }
        });


        // quantity.
//        int Original_price = Integer.parseInt(foods.getFood_price());



//        holder.Add_More_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////
//                int i = Integer.parseInt(holder.OrderQuantity.getText().toString()) + 1;
//
//                if (i < 50) {
//
//                    holder.OrderQuantity.setText(String.valueOf(i));
//
//                    int price = Integer.parseInt(holder.Order_Price.getText().toString()) + Original_price;
//
//                    holder.Order_Price.setText(String.valueOf(price));
//
//
//                    foods.setQuantity(i);
//
//                    dbHelper.updateOrders(foods);
//
//                }
//            }
//        });

//        holder.Delete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int i = Integer.parseInt(holder.OrderQuantity.getText().toString()) ;
//
//                if (i > 1 ) {
//
//                    i = i-1;
//
//                    holder.OrderQuantity.setText(String.valueOf(i));
//
//                    int price = Integer.parseInt(holder.Order_Price.getText().toString()) - Original_price;
//
//                    holder.Order_Price.setText(String.valueOf(price));
//
//                    foods.setQuantity(i);
//
//                    dbHelper.updateOrders(foods);
//
//                }
//            }
//        });

        // toal price.
//
//        int price = Integer.parseInt(holder.Order_Price.getText().toString()) ;
//
//        total_price += price;



    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public int getTotal_price(ArrayList<Foods> list) {
        int total = 0 ;

        for (Foods f: list) {
            total += Integer.parseInt( f.getFood_price() );
        }

        return  total ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Order_name , Order_Price ;
        ImageView Order_image , Order_remove_btn;
        // add/delete more button.
        TextView OrderQuantity ;
        LinearLayout QuantityCont;
        TextView total_price ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Order_image = itemView.findViewById(R.id.order_image);
            Order_name = itemView.findViewById(R.id.order_name);
            Order_Price = itemView.findViewById(R.id.order_price);
            Order_remove_btn = itemView.findViewById(R.id.removeBtn);
            OrderQuantity = itemView.findViewById(R.id.quantity_cart);
           QuantityCont = itemView.findViewById(R.id.Quantity_container);
//            total_price =  itemView.findViewById(R.id.total_cost);
        }
    }


}
