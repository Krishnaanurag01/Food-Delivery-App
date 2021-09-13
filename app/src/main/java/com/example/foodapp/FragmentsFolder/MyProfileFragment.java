package com.example.foodapp.FragmentsFolder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivitySavedAddressBinding;
import com.example.foodapp.databinding.FragmentMyProfileBinding;
import com.example.foodapp.models.Users;
import com.example.foodapp.my_order_activity;
import com.example.foodapp.order_activity;
import com.example.foodapp.saved_address;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MyProfileFragment extends Fragment {

    FragmentMyProfileBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(inflater,container,false);

        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseDatabase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();


        firebaseDatabase.getReference()
                .child("Users")
                .child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users user = snapshot.getValue(Users.class);
                        Picasso.get()
                                .load(user.getImage())
                                .placeholder(R.drawable.placeholder_img)
                                .into(binding.profilePhoto);
                        binding.profileUsername.setText(user.getName());
                        binding.profileMail.setText(user.getMail());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });







        // reach us intents :

        binding.contactFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        binding.contactGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "Krishna.anurag02@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Through Contact!");
                    intent.putExtra(Intent.EXTRA_TEXT, "Thanks for the food.");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(getContext(), "We are facing some errors with your phone.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.conatctNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });



        binding.uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });


        // my order activity start :


        binding.myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , my_order_activity.class);
                startActivity(intent);
            }
        });














        // my address touch listener .

        binding.profileSavedAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , saved_address.class);
                startActivity(intent);
            }
        });











        return binding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){

            Uri file = data.getData();
            binding.profilePhoto.setImageURI(file);

            final StorageReference storageReference = storage.getReference().child("Profile Picture")
                    .child(FirebaseAuth.getInstance().getUid());

            storageReference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("image")
                                    .setValue(uri.toString());
                            Toast.makeText(getContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }
}