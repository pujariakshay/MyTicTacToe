package com.mytictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewPlayer1,textViewPlayer2,txtDraw;
    private Button[][] buttons = new Button[3][3];

    public int roundCount = 0, player1Points = 0, player2Points = 0, drawCount = 0;
    boolean player1Turn = true;
    private Button reset,playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.txtPlayer1);
        textViewPlayer2 = findViewById(R.id.txtPlayer2);
        txtDraw = findViewById(R.id.txtDraw);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String buttonID = "button_" + row + col;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());

                buttons[row][col] = findViewById(resID);
                buttons[row][col].setOnClickListener(this);
            }
        }

        reset = findViewById(R.id.reset);
        playAgain = findViewById(R.id.playAgain);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
                playAgain.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(((Button) view).getText().toString())) {
            Toast.makeText(this, "Already selected", Toast.LENGTH_SHORT).show();
            return;
        }

        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }

        roundCount++;

        if (roundCount>=5 && checkForWin()){
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
            //playAgain.setVisibility(View.VISIBLE);
        } else if (roundCount == 9) {
            draw();
            //playAgain.setVisibility(View.VISIBLE);
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        //to get all button value
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                field[row][col] = buttons[row][col].getText().toString();
            }
        }

        //for row
        for (int row = 0; row < 3; row++) {
            if (field[row][0].equals(field[row][1]) &&
                    field[row][0].equals(field[row][2]) &&
                    !field[row][0].equals("")) {
                return true;
            }
        }

        //for col
        for (int col = 0; col < 3; col++) {
            if (field[0][col].equals(field[1][col]) &&
                    field[0][col].equals(field[2][col]) &&
                    !field[0][col].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2]) &&
                !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) &&
                field[0][2].equals(field[2][0]) &&
                !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 Wins...", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 Wins...", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void draw() {
        drawCount++;
        txtDraw.setText("Gram Draw : " + drawCount);
        Toast.makeText(this, "Game Draw..", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints() {
        textViewPlayer1.setText("Player 1 : " + player1Points);
        textViewPlayer2.setText("Player 2 : " + player2Points);
        txtDraw.setText("Game Draw : " + drawCount);
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        drawCount = 0;
        updatePoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }

    public void commit1() {
        //commit 1
    }

    public void commit2() {
        //commit 2
    }

    public void commit3() {
        //commit 3
    }

    public void commit4() {
        //commit 4
    }

    public void commit5() {
        //commit 5
    }

    public void commit6() {
        //commit 6
    }

}
