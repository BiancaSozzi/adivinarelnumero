package com.bianca.adivinaelnumero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button guessNum, chooseNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loadUIElements();
        onClick();
    }

    private void onClick(){

        final Intent game = new Intent(getApplicationContext(), GameActivity.class);
        chooseNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(game);
                SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("game_option",1);
                editor.apply();
            }
        });

        guessNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(game);
                SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("game_option",2);
                editor.apply();
            }
        });
    }

    private void loadUIElements(){
        chooseNum = findViewById(R.id.choose_num_btn);
        guessNum = findViewById(R.id.guess_num_btn);
    }


}
