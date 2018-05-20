package com.bianca.adivinaelnumero;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {


    int game_option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        game_option = preferences.getInt("game_option", 0);
        Log.i("game_option", String.valueOf(game_option));

        switch (game_option){
            case 1:
                chooseNumber();
                break;
            case 2:
                guessNumber();
                break;
            default:
                Toast.makeText(getApplicationContext(), R.string.error_message_game_option, Toast.LENGTH_SHORT).show();
        }
    }

    private void chooseNumber(){
        Toast.makeText(getApplicationContext(), "MODO 1 - ELEGIR NUMERO", Toast.LENGTH_SHORT).show();
    }

    private void guessNumber(){
        Toast.makeText(getApplicationContext(), "MODO 2 - ADIVINAR NUMERO", Toast.LENGTH_SHORT).show();
    }
}
