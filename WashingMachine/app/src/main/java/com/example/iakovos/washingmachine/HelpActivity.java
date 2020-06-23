package com.example.iakovos.washingmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    private Button close;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        close = (Button) findViewById(R.id.closeBtn);
        txt = (TextView) findViewById(R.id.textView46);
        String t = "1) ? Button : Help 2)Reset options button : delete all my saved programs 3)Predetermined settings button: set the temperature 60 Celsius degrees and spins to 600 4) My programs button: View all your saved programs 5)Now button: Start the fuction of washing machine in current time 6) Set time button: set time in order to start the function of washing machine 7) Open door button: It cannot be pushed unless the user has been pressed the button PAUSE 8) PAUSE button : pause the function of washing machine 9)Finish button: End the function of washing machine";
        txt.setText(t);

        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}
