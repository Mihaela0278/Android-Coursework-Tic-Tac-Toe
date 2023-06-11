package com.example.androidcourseworkuni;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidcourseworkuni.databinding.ActivityAddPlayersBinding;

public class AddPlayersActivity extends AppCompatActivity {

    private ActivityAddPlayersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPlayersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startGameBtn.setOnClickListener(view -> {

            final String playerOneName = binding.playerOneName.getText().toString();
            final String playerTwoName = binding.playerTwoName.getText().toString();

            if (playerOneName.isBlank() || playerTwoName.isBlank()) {
                Toast.makeText(AddPlayersActivity.this, "Please enter player names", Toast.LENGTH_SHORT).show();
            } else {
                redirectToMainActivity(playerOneName, playerTwoName);
            }
        });
    }

    private void redirectToMainActivity(String playerOneName, String playerTwoName) {
        Intent intent = new Intent(AddPlayersActivity.this, MainActivity.class);
        intent.putExtra("playerOne", playerOneName);
        intent.putExtra("playerTwo", playerTwoName);
        startActivity(intent);
    }
}