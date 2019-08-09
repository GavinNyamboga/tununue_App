package com.dev.tununue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    //define objects
    private Button buttonRegister;
    private EditText Email;
    private EditText registerPassword;
    private TextView linkLogin;

    private ProgressDialog progressDialog;

    //define firebase object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //initializing the objects
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonRegister=(Button) findViewById(R.id.btn_signup);
        Email = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        linkLogin = (TextView) findViewById(R.id.link_login);

        buttonRegister.setOnClickListener(this);
        linkLogin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = Email.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //if the email field is empty
            Toast.makeText(this,"please enter Email", Toast.LENGTH_SHORT).show();
            //stop further execution of the function
            return;
        }
        if (TextUtils.isEmpty(password)){
            //if the password field is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }


        //if all validations are okay, show progress dialog

        progressDialog.setMessage("chill kiasi..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //user is successfully registered
                            //open homeActivity
                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view==buttonRegister){
            registerUser();


        }
        if (view==linkLogin){
            //open loginActivity
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }
    }
}
