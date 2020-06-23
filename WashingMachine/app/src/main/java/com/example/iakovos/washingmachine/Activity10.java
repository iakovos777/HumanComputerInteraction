package com.example.iakovos.washingmachine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

public class Activity10 extends AppCompatActivity {

    private Button back;
    private Button now;
    private Button set;
    private ImageButton help;
    private String duration;
    private Calendar dateTime = Calendar.getInstance();
    private int hours, minutes;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);
        help = (ImageButton) findViewById(R.id.imageButton9);
        set = (Button) findViewById(R.id.button25);
        now = (Button) findViewById(R.id.button24);
        back = (Button) findViewById(R.id.button26);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle.isEmpty()){

            return;
        }
        duration=bundle.getString("duration");

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity10.this,HelpActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity10.this,Activity5.class));
            }
        });

        now.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                time = "now";
                Intent intent2 = new Intent(Activity10.this, Activity7.class);
                Bundle bundle = new Bundle();
                bundle.putString("start",time);
                bundle.putString("duration",duration);
                intent2.putExtras(bundle);
                startActivity(intent2);

            }
        });


        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                takeTime();
            }
        });
    }

    private void takeTime(){
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int i, int il) {
            hours = i;
            minutes = il;
            int sec = 0;
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hours);
            c.set(Calendar.MINUTE, minutes);
            c.set(Calendar.SECOND,sec);
            String h  = Integer.toString(hours);
            String m = Integer.toString(minutes);
            time = h+":"+m;
            Intent intent2 = new Intent(Activity10.this, Activity7.class);
            Bundle bundle = new Bundle();
            //bundle.putString("start",time);
            bundle.putString("duration",duration);
            intent2.putExtras(bundle);
            AlertDialog.Builder alert = new AlertDialog.Builder(Activity10.this);
            alert.setTitle("PLEASE WAIT");
            alert.setMessage("The washing maching will start in time you set");
            alert.setCancelable(false);
            AlertDialog a = alert.create();
            a.show();
            PendingIntent pendingIntent = PendingIntent.getActivity(Activity10.this, 0, intent2, PendingIntent.FLAG_ONE_SHOT);
            ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);


        }
    };


}
