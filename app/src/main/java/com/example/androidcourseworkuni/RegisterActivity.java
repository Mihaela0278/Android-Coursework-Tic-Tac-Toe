package com.example.androidcourseworkuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidcourseworkuni.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginHere.setOnClickListener(v -> redirectToLogin());
    }

    private void redirectToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}