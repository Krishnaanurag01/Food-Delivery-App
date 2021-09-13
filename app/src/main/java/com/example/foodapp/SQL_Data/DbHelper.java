package com.example.foodapp.SQL_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodapp.Params.param;
import com.example.foodapp.models.Foods;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, param.DB_Name , null , param.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+ param.Table_Name +" ( "
                + param.key_id +" INTEGER PRIMARY KEY ,"+
                param.key_FoodName+" TEXT ," + param.key_image +" INTEGER ,"
                +param.key_Category +" TEXT ,"+param.key_Price+" TEXT" +")";
        db.execSQL(create);
        Log.d("DB", "onCreate: Hell Yeah !!");
//        db.close();

        // order table.

        db.execSQL("CREATE TABLE "+param.Table_Name2 +" ( " + param.key_id +" INTEGER PRIMARY KEY ,"+
                param.key_FoodName+" TEXT ," + param.key_Category +" TEXT ,"+param.key_Price+" TEXT , " + param.key_image +" INTEGER ," + param.key_order_quantity +" INTEGER " +")"  );
        Log.d("DB2", "onCreate: Hell Yeah2 !!");
    }

    public void insertData(Foods foods){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(param.key_Category,foods.getFood_category());
        contentValues.put(param.key_FoodName,foods.getFood_name());
        contentValues.put(param.key_image,foods.getFood_img());
        contentValues.put(param.key_Price,foods.getFood_price());
        db.insert(param.Table_Name , null,contentValues);
        Log.d("DB INSERTION", "insertData: Data Inserted Successfully. ");
        db.close();
    }

    public ArrayList<Foods> getData(){
        ArrayList<Foods> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String readDataQuery = "SELECT * FROM "+param.Table_Name ;
        Cursor cursor = db.rawQuery(readDataQuery,null );

        if (cursor.moveToFirst()) {

            do {

                Foods foods = new Foods();
                foods.setFood_id(Integer.parseInt(cursor.getString(0)));
                foods.setFood_name(cursor.getString(1));
                foods.setFood_img(Integer.parseInt(cursor.getString(2)));
                foods.setFood_category(cursor.getString(3));
                foods.setFood_price(cursor.getString(4));

                list.add(foods);
            }
            while (cursor.moveToNext());

        }
        db.close();


        return list;
    }

    public ArrayList<Foods> getDataByCategory(String category){
        ArrayList<Foods> list = new ArrayList<>();
        String[] columns ={param.key_id,param.key_FoodName,param.key_image, param.key_Category,param.key_Price};

        SQLiteDatabase db = this.getWritableDatabase();
//        String readDataQuery = "SELECT * FROM "+param.Table_Name+" WHERE "+param.key_Category+" = "+ "'Non-veg'" ;
//        Cursor cursor = db.rawQuery(readDataQuery,null );
       Cursor cursor = db.query(param.Table_Name, columns, "Category" +"=?", new String[] { category }, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                Foods foods = new Foods();
                foods.setFood_id(Integer.parseInt(cursor.getString(0)));
                foods.setFood_name(cursor.getString(1));
                foods.setFood_img(Integer.parseInt(cursor.getString(2)));
                foods.setFood_category(cursor.getString(3));
                foods.setFood_price(cursor.getString(4));

                list.add(foods);
            }
            while (cursor.moveToNext());

        }
        db.close();


        return list;
    }



//    public ArrayList<Foods> getOrders(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String getOrdersList = "SELECT * FROM "+param.Table_Name
//    }

    public void deleteDB(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(param.Table_Name,param.key_FoodName +"=?" , new String[]{name});
    }





    // order table function.

    public void insertOrder(Foods foods){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(param.key_Category,foods.getFood_category());
        contentValues.put(param.key_FoodName,foods.getFood_name());
        contentValues.put(param.key_Price,foods.getFood_price());
        contentValues.put(param.key_image,foods.getFood_img());
        contentValues.put(param.key_order_quantity,foods.getQuantity());
        db.insert(param.Table_Name2 , null,contentValues);
        Log.d("Table 2 INSERTION", "insertData: Data Inserted Successfully. ");
        db.close();
    }

    public ArrayList<Foods> getOrders(){
        ArrayList<Foods> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String readDataQuery = "SELECT * FROM "+param.Table_Name2 ;
        Cursor cursor = db.rawQuery(readDataQuery,null );

        if (cursor.moveToFirst()) {

            do {

                Foods foods = new Foods();
                foods.setFood_id(Integer.parseInt(cursor.getString(0)));
                foods.setFood_name(cursor.getString(1));
//                foods.setFood_img(Integer.parseInt(cursor.getString(2)));
                foods.setFood_category(cursor.getString(2));
                foods.setFood_price(cursor.getString(3));
                foods.setFood_img(Integer.parseInt(cursor.getString(4)));
                foods.setQuantity(Integer.parseInt(cursor.getString(5)));

                list.add(foods);
            }
            while (cursor.moveToNext());

        }
        db.close();


        return list;
    }


    public void delete_Order_ByID(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(param.Table_Name2,param.key_id+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int updateOrders(Foods foods){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(param.key_Category,foods.getFood_category());
        contentValues.put(param.key_FoodName,foods.getFood_name());
        contentValues.put(param.key_Price,foods.getFood_price());
        contentValues.put(param.key_image,foods.getFood_img());
        contentValues.put(param.key_order_quantity,foods.getQuantity());
        return db.update(param.Table_Name2,contentValues,param.key_FoodName+"=?",new String[]{foods.getFood_name()});

    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+param.Table_Name);
        db.execSQL("DROP TABLE IF EXISTS "+param.Table_Name2);

        onCreate(db);

    }
}
