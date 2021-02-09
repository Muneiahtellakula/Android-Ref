package com.muneiah.example.loginandregfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muneiah.example.loginandregfirebase.model.User;

public class LoginUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eTxtEmail, eTxtPassword;
    Button btnLogin;
    TextView txtRegister;

    User user;

    ProgressDialog progressDialog;

    FirebaseAuth auth;
    FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        getSupportActionBar().setTitle("Login");
        initViews();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            // We need to authenticate user in Google Firebase Platform
            user = new User();
            user.email = eTxtEmail.getText().toString();
            user.password = eTxtPassword.getText().toString();
            loginUser();
        }

        if (v == txtRegister){
            Intent intent = new Intent(LoginUserActivity.this,RegisterUserActivity.class);
            startActivity(intent);
        }
    }


    void initViews(){
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.button);
        txtRegister = findViewById(R.id.textViewRegister);

        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }
    void loginUser(){

        progressDialog.show();

        auth.signInWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginUserActivity.this,MainActivity.class);
                            startActivity(intent);
                            fbUser = task.getResult().getUser();
                        }else{
                            Toast.makeText(LoginUserActivity.this,"Please Try Again !!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}