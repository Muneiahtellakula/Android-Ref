package com.muneiah.example.loginandregfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muneiah.example.loginandregfirebase.model.User;

import java.util.ArrayList;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTxtName, eTxtEmail, eTxtPassword;
    Button btnRegister;

    User user;

    FirebaseAuth auth;
    FirebaseUser fbUser;
    FirebaseFirestore db;

    ProgressDialog progressDialog;

    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initViews();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        if( v == btnRegister){
            // We need to create user in Google Firebase Platform
            user = new User();
            user.name = eTxtName.getText().toString();
            user.email = eTxtEmail.getText().toString();
            user.password = eTxtPassword.getText().toString();

            registerUser();

        }
    }
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.button);
        btnRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }
    void saveUserInDB(){
        String docId = fbUser.getUid();
        // set is to add or update
        db.collection("users").document(docId).set(user)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(RegisterUserActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(RegisterUserActivity.this,"Please Try Again !!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    void deleteDataFromDB(){
        String docId = fbUser.getUid();
        db.collection("users").document(docId).delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    void fetchDataFromDB(){

        users = new ArrayList<>();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User uRef = document.toObject(User.class);
                                users.add(uRef);
                            }

                            // Show the fetched data on RecyclerView
                        } else {

                        }
                    }
                });
    }

    void registerUser(){
        progressDialog.show();

        auth.createUserWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            fbUser = task.getResult().getUser();
                            saveUserInDB();
                        }else{
                            Toast.makeText(RegisterUserActivity.this,"Please Try Again !!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}