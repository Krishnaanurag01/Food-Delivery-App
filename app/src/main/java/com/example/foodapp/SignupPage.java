package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivitySignupPageBinding;
import com.example.foodapp.models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPage extends AppCompatActivity {
    TextView alreadyHaveLogin;
    // database defining.
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;

    ActivitySignupPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alreadyHaveLogin = findViewById(R.id.alreadyhavelogin);
        alreadyHaveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(SignupPage.this , loginactivity.class);
                startActivity(intent4);
            }
        });

        // progress dialog.
        progressDialog = new ProgressDialog(SignupPage.this);
        progressDialog.setTitle("Creating Account!");
        progressDialog.setMessage("Hang tight!, You're about to rock.");


        // getting instances of database.

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.getText().toString() , binding.editTextTextPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    Users user = new Users(binding.Name.getText().toString() , binding.editTextNumber.getText().toString(),binding.editTextTextEmailAddress.getText().toString(),binding.editTextTextPassword.getText().toString());

                                    String UID = task.getResult().getUser().getUid();
//                                    user.setUID(task.getResult().getUser().getUid());
                                    firebaseDatabase.getReference().child("Users").child(UID).setValue(user);
                                    Toast.makeText(SignupPage.this, "Account created successfully !,Now please login with your Account ", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignupPage.this,loginactivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(SignupPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
//
                            }
                        });
            }
        });




        // google sign in methods.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // google button click listner.

        binding.googleSIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    }
    // google sign in methods outside oncreate function.
    int RC_SIGN_IN = 65;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            // taking data so that we can store it in database as well
                            Users userModel = new Users();
                            userModel.setUID(user.getUid());
                            userModel.setImage(user.getPhotoUrl().toString());
                            userModel.setName(user.getDisplayName());
                            userModel.setMail(user.getEmail());
                            userModel.setPhoneNumber(user.getPhoneNumber());

                            // now storing data to the firebase database.
                            firebaseDatabase.getReference().child("Users").child(user.getUid()).setValue(userModel);

                            // now going to main screen

                            Intent intent = new Intent(SignupPage.this,MainScreen.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Tag", "signInWithCredential:failure", task.getException());
//                            updateUI(null);
                        }
                    }
                });
    }

    // google sign in methods ending.
}