package com.example.androidcourseworkuni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        binding.startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getPlayerOneName = binding.playerOneName.getText().toString();
                final String getPlayerTwoName = binding.playerTwoName.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayersActivity.this, "Please enter player names", Toast.LENGTH_SHORT).show();
                } else {
                    redirectToMainActivity(getPlayerOneName, getPlayerTwoName);
                }
            }
        });
    }

    private void redirectToMainActivity(String getPlayerOneName, String getPlayerTwoName) {
        Intent intent = new Intent(AddPlayersActivity.this, MainActivity.class);
        intent.putExtra("playerOne", getPlayerOneName);
        intent.putExtra("playerTwo", getPlayerTwoName);
        startActivity(intent);
    }
}