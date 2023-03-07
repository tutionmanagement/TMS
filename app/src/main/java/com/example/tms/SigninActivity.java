package com.example.tms;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tms.databinding.ActivitySigninBinding;
import com.example.tms.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {
    private String email,password;
    private ActivitySigninBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Teachers");
//        binding.edPassword.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                final int DRAWABLE_RIGHT = 2;
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    if(motionEvent.getRawX() >= (binding.edPassword.getRight()-binding.edPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
//                        binding.edPassword.setTransformationMethod(null);
//                        return true;
//                    }else{
//                        binding.edPassword.setTransformationMethod(new PasswordTransformationMethod());
//                    }
//                }
//                return false;
//            }
//        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    public void toSignup(View v){
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
    public void Signin(View v){
        email = binding.edEmail.getText().toString();
        password = binding.edPassword.getText().toString();

        if(email.equals("") || password.equals("")){
            binding.edEmail.setError("This Field Required");
            binding.edPassword.setError("This Field Required");
        }else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String key = databaseReference.child("email").push().getKey();
                                    Log.i("Key",key);
                                    SharedPreferences sharedPreferences = getSharedPreferences("SystemPre",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("isLogin",true);
                                    editor.putString("email",email);
                                    editor.commit();
                                    Intent intent = new Intent(SigninActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SigninActivity.this, "Invalid Email or Password.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        }
    }

}