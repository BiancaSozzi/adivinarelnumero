package com.bianca.adivinaelnumero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    TextView titleText, numberText, messageText, infoBox;
    ImageButton higherBtn, lowerBtn, correctNumBtn;
    EditText possibleNumber;
    Button verifyButton;
    int game_option;
    int number, min =1, max = 100, my_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        game_option = preferences.getInt("game_option", 0);
        Log.i("game_option", String.valueOf(game_option));

        switch (game_option){
            case 1:
                setContentView(R.layout.activity_game);
                loadUIElements();
                hideAllElements();
                onClick();
                number = findNumber(min, max);
                numberText.setText(String.valueOf(number));
                chooseNumber();
                break;
            case 2:
                setContentView(R.layout.activity_game_option_2);
                my_number = (int) (Math.random()* 100) +1;
                Log.i("random Num", String.valueOf(my_number));
                loadUIElements();
                hideAllElements();
                onClick();
                guessNumber();
                break;
            default:
                Toast.makeText(getApplicationContext(), R.string.error_message_game_option, Toast.LENGTH_SHORT).show();
        }



    }

    private void loadUIElements(){

        if(game_option == 1){
            titleText = findViewById(R.id.title_text);
            numberText = findViewById(R.id.number_text_1);
            messageText = findViewById(R.id.message_text);

            higherBtn = findViewById(R.id.higher_button);
            lowerBtn = findViewById(R.id.lower_button);
            correctNumBtn = findViewById(R.id.correct_number_button);
        }else{
            titleText = findViewById(R.id.title_text);
            messageText = findViewById(R.id.message_text);
            possibleNumber = findViewById(R.id.number_text_2);
            verifyButton = findViewById(R.id.check_number_button);
            infoBox = findViewById(R.id.info_box);
        }

    }

    private void hideAllElements(){
        if(game_option== 1){
            titleText.setVisibility(View.GONE);
            numberText.setVisibility(View.GONE);
            messageText.setVisibility(View.GONE);
            higherBtn.setVisibility(View.GONE);
            lowerBtn.setVisibility(View.GONE);
            correctNumBtn.setVisibility(View.GONE);
        }else{
            titleText.setVisibility(View.GONE);
            possibleNumber.setVisibility(View.GONE);
            messageText.setVisibility(View.GONE);
            verifyButton.setVisibility(View.GONE);
            infoBox.setVisibility(View.GONE);
        }


    }
    private void onClick(){

        if(game_option==1){
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
                        max = number - 1;
                        if(max>1){
                            number = findNumber(min, max);
                            numberText.setText(String.valueOf(number));
                        }
                }
            });

            correctNumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gamefinished = new Intent(getApplicationContext(), FinishGameActivity.class);
                    startActivity(gamefinished);
                    finish();
                }
            });

        }else{
            verifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    infoBox.setVisibility(View.GONE);
                    if(! (possibleNumber.getText().toString().length() == 0)){
                        number = Integer.parseInt(possibleNumber.getText().toString());
                    }
                    if(!(number <1 || number>100)){
                        if(number < my_number){
                            messageText.setText(R.string.message_text_up);
                            possibleNumber.setText("");
                        }else if(number> my_number){
                            messageText.setText(R.string.message_text_down);
                            possibleNumber.setText("");
                        }else{
                            messageText.setText(R.string.message_text_correct_number);
                            Intent gamefinished = new Intent(getApplicationContext(), FinishGameActivity.class);
                            startActivity(gamefinished);
                            finish();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), R.string.not_valid_number_text, Toast.LENGTH_SHORT).show();
                        possibleNumber.setText("");
                    }

                }
            });

        }


    }

    private void chooseNumber(){
        //Toast.makeText(getApplicationContext(), "MODO 1 - ELEGIR NUMERO", Toast.LENGTH_SHORT).show();
        titleText.setVisibility(View.VISIBLE);
        numberText.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);
        higherBtn.setVisibility(View.VISIBLE);
        lowerBtn.setVisibility(View.VISIBLE);
        correctNumBtn.setVisibility(View.VISIBLE);

        titleText.setText(R.string.title_text_gameoption1);
        messageText.setText(R.string.message_text_gameoption1);

    }

    private void guessNumber(){
        //Toast.makeText(getApplicationContext(), "MODO 2 - ADIVINAR NUMERO", Toast.LENGTH_SHORT).show();
        titleText.setVisibility(View.VISIBLE);
        possibleNumber.setVisibility(View.VISIBLE);
        verifyButton.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);
        infoBox.setVisibility(View.VISIBLE);


        titleText.setText(R.string.title_text_gameoption2);

    }

    private int findNumber(int min, int max){

        int middle = (min+max)/2;
        return middle;
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
        finish();
    }
}
