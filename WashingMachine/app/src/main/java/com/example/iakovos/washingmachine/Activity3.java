package com.example.iakovos.washingmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Activity3 extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button backButton2;
    private ImageButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button4);
        button3 = (Button)findViewById(R.id.button5);
        backButton2 = (Button)findViewById(R.id.backButton2);
        help = (ImageButton) findViewById(R.id.imageButton2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity3.this,Activity4.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity3.this,Activity4.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity3.this,Activity4.class));
            }
        });
        backButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity3.this,Activity2.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity3.this,HelpActivity.class));
            }
        });
    }
}
