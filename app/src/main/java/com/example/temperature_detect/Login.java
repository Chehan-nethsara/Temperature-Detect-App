package com.example.temperature_detect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "LOGIN ACTIVITY";

    EditText editTextEmail;
    EditText editTextPassword;

    private Button btn_Login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.login_user_name);
        editTextPassword = findViewById(R.id.login_password);
        btn_Login = (Button) findViewById(R.id.btn_login) ;

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(email.isEmpty()){
                    editTextEmail.setError("Email Required!");
                }else{
                    if(password.isEmpty()){
                        editTextPassword.setError("Password Required!");
                    }else{
                        if(password.length() < 6){
                            editTextPassword.setError("Minimum 6 characters required!");
                        }else{
                            signInExistingUsers(email,password);
                        }
                    }
                }
            }
        });
    }

    private void signInExistingUsers(String email,String password ){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Login.this, "Login success.",
                                    Toast.LENGTH_SHORT).show();
                            // FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.login, new PendingOrders()).addToBackStack(null).commit();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        }

    @Override
    public void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Login Failed!,Try again",Toast.LENGTH_LONG).show();
        }
    }

}