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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

        private EditText mEmailField;
        private EditText mPasswordField;
        private TextView linkRegister;

        private Button mLoginBtn;

        private ProgressDialog progressDialog;

        private FirebaseAuth mAuth;

        private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth=FirebaseAuth.getInstance();
        mEmailField=(EditText)findViewById(R.id.EmailField);
        mPasswordField=(EditText)findViewById(R.id.PasswordField);
        linkRegister = (TextView) findViewById(R.id.link_signup);

        mLoginBtn=(Button)findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);

        mAuthListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
            }
        };

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignIn();

            }
        });
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open SignupActivity

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email) ){
            Toast.makeText(LoginActivity.this,"Please Enter email", Toast.LENGTH_SHORT).show();
            return;

        }else if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Please enter Password", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Sign In Problem", Toast.LENGTH_LONG).show();

                    }
                }
            });


        }
        progressDialog.setMessage("chill kiasi..");
        progressDialog.show();


    }

}
