package com.example.iakovos.washingmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity2 extends AppCompatActivity {
    private Button button3;
    private Button backButton;
    private ImageButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button3 = (Button)findViewById(R.id.button3);
        backButton = (Button)findViewById(R.id.backButton);
        help = (ImageButton) findViewById(R.id.imageButton);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity2.this,Activity3.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity2.this,MainActivity.class));
            }
        });

       help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity2.this,HelpActivity.class));
            }
        });



    }

}
