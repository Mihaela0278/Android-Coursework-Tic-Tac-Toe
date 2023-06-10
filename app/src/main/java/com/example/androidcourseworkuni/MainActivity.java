package com.example.androidcourseworkuni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidcourseworkuni.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final List<int[]> combinationsList = new ArrayList<>();

    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int playerTurn = 1;

    private int totalSelectedBox = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String playerOneName = getIntent().getStringExtra("playerOne");
        String playerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOneName.setText(playerOneName);
        binding.playerTwoName.setText(playerTwoName);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 6, 3});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        binding.box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)) {
                    performAction((ImageView) view, 0);
                }
            }
        });

        binding.box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)) {
                    performAction((ImageView) view, 1);
                }
            }
        });

        binding.box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)) {
                    performAction((ImageView) view, 2);
                }
            }
        });

        binding.box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)) {
                    performAction((ImageView) view, 3);
                }
            }
        });

        binding.box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)) {
                    performAction((ImageView) view, 4);
                }
            }
        });

        binding.box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)) {
                    performAction((ImageView) view, 5);
                }
            }
        });

        binding.box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)) {
                    performAction((ImageView) view, 6);
                }
            }
        });

        binding.box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)) {
                    performAction((ImageView) view, 7);
                }
            }
        });

        binding.box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)) {
                    performAction((ImageView) view, 8);
                }
            }
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPositions) {

        boxPositions[selectedBoxPositions] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);

            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        binding.playerOneName.getText().toString() + " has won the match",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (totalSelectedBox == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is draw!",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(2);

                totalSelectedBox++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);

            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        binding.playerTwoName.getText().toString() + " has won the match",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (selectedBoxPositions == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is draw!",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(1);

                totalSelectedBox++;
            }
        }
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
        boolean response = false;

        for (int i = 0; i < combinationsList.size(); i++) {

            final int[] combination = combinationsList.get(i);

            if (boxPositions[combination[0]] == playerTurn
                    && boxPositions[combination[1]] == playerTurn
                    && boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {

        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn = 1;

        totalSelectedBox = 1;

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