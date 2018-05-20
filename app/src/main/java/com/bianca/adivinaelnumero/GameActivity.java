package com.bianca.adivinaelnumero;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView titleText, numberText, messageText;
    ImageButton higherBtn, lowerBtn, correctNumBtn;
    int game_option;
    int number;
    int min =1;
    int max = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        game_option = preferences.getInt("game_option", 0);
        Log.i("game_option", String.valueOf(game_option));

        loadUIElements();
        hideAllElements();
        onClick();

        switch (game_option){
            case 1:
                number = findNumber(min, max);
                numberText.setText(String.valueOf(number));
                chooseNumber();
                break;
            case 2:
                guessNumber();
                break;
            default:
                Toast.makeText(getApplicationContext(), R.string.error_message_game_option, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadUIElements(){
        titleText = findViewById(R.id.title_text);
        numberText = findViewById(R.id.number_text);
        messageText = findViewById(R.id.message_text);

        higherBtn = findViewById(R.id.higher_button);
        lowerBtn = findViewById(R.id.lower_button);
        correctNumBtn = findViewById(R.id.correct_number_button);

    }

    private void hideAllElements(){
        titleText.setVisibility(View.GONE);
        numberText.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);
        higherBtn.setVisibility(View.GONE);
        lowerBtn.setVisibility(View.GONE);
        correctNumBtn.setVisibility(View.GONE);
    }
    private void onClick(){
        higherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = number +1;
                number = findNumber(min, max);
                numberText.setText(String.valueOf(number));
            }
        });

        lowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                max = number-1;
                number = findNumber(min, max);
                numberText.setText(String.valueOf(number));
            }
        });

        correctNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ha ganado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chooseNumber(){
        //Toast.makeText(getApplicationContext(), "MODO 1 - ELEGIR NUMERO", Toast.LENGTH_SHORT).show();
        titleText.setVisibility(View.VISIBLE);
        numberText.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);
        higherBtn.setVisibility(View.VISIBLE);
        lowerBtn.setVisibility(View.VISIBLE);
        correctNumBtn.setVisibility(View.VISIBLE);

        titleText.setText("Elige un número entre 1 y 100");
        messageText.setText("Este es su numero?");

    }

    private void guessNumber(){
        //Toast.makeText(getApplicationContext(), "MODO 2 - ADIVINAR NUMERO", Toast.LENGTH_SHORT).show();
    }

    private int findNumber(int min, int max){

        int middle = (min+max)/2;
        return middle;
    }
}
