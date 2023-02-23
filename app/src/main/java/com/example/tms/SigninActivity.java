package com.example.tms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tms.databinding.ActivitySigninBinding;
import com.example.tms.databinding.ActivitySignupBinding;

public class SigninActivity extends AppCompatActivity {
    private String email,password;
    private ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

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
            if(email.equals("admin@gmail.com")&&password.equals("1234")){
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SigninActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}