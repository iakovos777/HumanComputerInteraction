package com.example.iakovos.washingmachine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity5 extends AppCompatActivity {

    private Button resetBtn;
    private MyDBHandler db;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button backBtn;
    private ImageButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        db = new MyDBHandler(this, null, null, 1);
        resetBtn = (Button) findViewById(R.id.button13);
        btn1 = (Button) findViewById(R.id.button10);
        btn2 = (Button) findViewById(R.id.button11);
        btn3 = (Button) findViewById(R.id.button12);
        backBtn = (Button) findViewById(R.id.button14);
        help = (ImageButton) findViewById(R.id.imageButton4);

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity5.this,HelpActivity.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(Activity5.this, Activity10.class);
                Bundle bundle = new Bundle();
                bundle.putString("duration","60");
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity5.this,Activity6.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity5.this,Activity8.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity5.this,Activity4.class));
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder alert = new AlertDialog.Builder(Activity5.this);
                alert.setTitle("Delete all my programs");
                alert.setMessage("Are you sure that you want to delete all your programs?");
                alert.setCancelable(false);
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteAll();
                        String message = "All your programs have been deleted...";
                        Toast.makeText(Activity5.this, message, Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog a = alert.create();
                a.show();
            }
        });

    }


}
