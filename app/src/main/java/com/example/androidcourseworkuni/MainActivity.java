package com.example.androidcourseworkuni;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidcourseworkuni.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int playerTurn = 1;

    private int totalSelectedBoxes = 1;

    private String playerOneName;
    private String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        playerOneName = getIntent().getStringExtra("playerOne");
        playerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOneName.setText(playerOneName);
        binding.playerTwoName.setText(playerTwoName);

        setBoxClickListener(binding.box1, 0);
        setBoxClickListener(binding.box2, 1);
        setBoxClickListener(binding.box3, 2);
        setBoxClickListener(binding.box4, 3);
        setBoxClickListener(binding.box5, 4);
        setBoxClickListener(binding.box6, 5);
        setBoxClickListener(binding.box7, 6);
        setBoxClickListener(binding.box8, 7);
        setBoxClickListener(binding.box9, 8);
    }

    private void setBoxClickListener(ImageView box, int boxPosition) {
        box.setOnClickListener(v -> {
            if (isBoxSelectable(boxPosition)) {
                performAction(box, boxPosition);
            }
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPositions) {

        boxPositions[selectedBoxPositions] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);

            checkWinAndChangePlayerTurn(playerOneName, 2);
        } else {
            imageView.setImageResource(R.drawable.oimage);

            checkWinAndChangePlayerTurn(playerTwoName, 1);
        }
    }

    private void checkWinAndChangePlayerTurn(String playerName, int nextPlayer) {
        if (checkPlayerWin()) {
            createAndShowDialog(playerName + " has won the match!");
            return;
        }

        if (totalSelectedBoxes == 9) {
            createAndShowDialog("It is a draw!");
            return;
        }

        changePlayerTurn(nextPlayer);
        totalSelectedBoxes++;
    }

    private void createAndShowDialog(String message) {
        WinDialog winDialog = new WinDialog(MainActivity.this,
                message,
                MainActivity.this);

        winDialog.setCancelable(false);
        winDialog.show();
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;

        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.round_back_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.round_back_gray);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.round_back_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.round_back_gray);
        }
    }

    private boolean checkPlayerWin() {

        //rows
        if (boxPositions[0] == playerTurn && boxPositions[1] == playerTurn && boxPositions[2] == playerTurn
                || boxPositions[3] == playerTurn && boxPositions[4] == playerTurn && boxPositions[5] == playerTurn
                || boxPositions[6] == playerTurn && boxPositions[7] == playerTurn && boxPositions[8] == playerTurn) {
            return true;
        }

        //cols
        if (boxPositions[0] == playerTurn && boxPositions[3] == playerTurn && boxPositions[6] == playerTurn
                || boxPositions[1] == playerTurn && boxPositions[4] == playerTurn && boxPositions[7] == playerTurn
                || boxPositions[2] == playerTurn && boxPositions[5] == playerTurn && boxPositions[8] == playerTurn) {
            return true;
        }

        //diags
        if (boxPositions[0] == playerTurn && boxPositions[4] == playerTurn && boxPositions[8] == playerTurn
                || boxPositions[2] == playerTurn && boxPositions[4] == playerTurn && boxPositions[6] == playerTurn) {
            return true;
        }

        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn = 1;

        totalSelectedBoxes = 1;

        resetImageView(binding.box1);
        resetImageView(binding.box2);
        resetImageView(binding.box3);
        resetImageView(binding.box4);
        resetImageView(binding.box5);
        resetImageView(binding.box6);
        resetImageView(binding.box7);
        resetImageView(binding.box8);
        resetImageView(binding.box9);
    }

    private void resetImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.transparent_picture);
    }
}