package com.example.iakovos.washingmachine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Calendar;

public class Activity9 extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private ImageButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);
        btn1 = (Button) findViewById(R.id.button27);
        btn2 = (Button) findViewById(R.id.button28);
        help = (ImageButton) findViewById(R.id.imageButton8);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity9.this,MainActivity.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar c = Calendar.getInstance();
                int hours = c.get(Calendar.HOUR_OF_DAY) ;
                int minutes = c.get(Calendar.MINUTE);
                minutes = minutes+3;
                c.set(Calendar.HOUR_OF_DAY, hours);
                c.set(Calendar.MINUTE, minutes);
                Intent intent2 = new Intent(Activity9.this, MainActivity.class);
                AlertDialog.Builder alert = new AlertDialog.Builder(Activity9.this);
                alert.setTitle("WAIT!!!");
                alert.setMessage("Wait until washing machine strain the clothes ");
                alert.setCancelable(false);
                AlertDialog a = alert.create();
                a.show();
                PendingIntent pendingIntent = PendingIntent.getActivity(Activity9.this, 0, intent2, PendingIntent.FLAG_ONE_SHOT);
                ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

            }
        });
    }
}
