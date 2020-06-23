package com.example.iakovos.washingmachine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Activity7 extends AppCompatActivity {
    private Button buttonPause;
    private Button buttonFinish;
    private Button buttonPort;
    private int flag=0;
    private final Context context=this;
    private TextView mTextViewCountDown;
    private int total=0;
    private CountDownTimer countDownTimer;
    private boolean timeRun;
    private long timeLeft;
    private ProgressBar bar;
    private String duration;
    private long timeDur;
    private int metr=0;
    private ImageButton help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        mTextViewCountDown =  findViewById(R.id.timeText);
        buttonFinish = (Button) findViewById(R.id.button19);
        buttonPort = (Button) findViewById(R.id.button20);
        buttonPause = (Button) findViewById(R.id.button18);
        help = (ImageButton) findViewById(R.id.imageButton6);
        bar = (ProgressBar) findViewById(R.id.progressBar5);
        bar.setProgress(total);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle.isEmpty()){
            return;
        }
        duration=bundle.getString("duration");
        int tS = Integer.parseInt(duration);
        timeDur = tS*1000*60;
        timeLeft = timeDur ;
        startTimer();
        if (flag == 0){
            buttonPort.setClickable(false);
         }

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity7.this,HelpActivity.class));
            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(flag==0) {
                    //buttonPause.setText("Resume");
                    pauseTimer();
                    enablePortButton();
                    //flag=1;
                }
                else if(flag==1){
                    //buttonPause.setText("Pause");
                   // flag=0;
                    startTimer();
                    buttonPort.setClickable(false);
                }
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                terminateFunction();

            }
        });
    }

    private void enablePortButton(){
        buttonPort.setClickable(true);
                buttonPort.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        openPort();
                    }
                });
    }

    private void openPort(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Door is open");
        alert.setMessage("Close the door of washing machine and after that press ok and resume in the previous window");
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
               dialog.cancel();
            }
        });
        AlertDialog a = alert.create();
        a.show();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                metr++;
                total = (int)(metr*100/(timeDur/1000));
                bar.setProgress(total);
                updateCountDownText();
                updateText(timeLeft);
            }

            @Override
            public void onFinish() {
                bar.setProgress(100);
                timeRun = false;
                flag=1;
                 buttonPause.setClickable(false);
                startActivity(new Intent(Activity7.this,Activity9.class));

            }
        }.start();
        timeRun =  true;
        buttonPause.setText("Pause");
        flag=0;
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timeRun = false;
        flag=1;
        buttonPause.setText("Resume");

    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeft/1000)/60;
        int seconds = (int) (timeLeft/1000)%60;
        String m = Integer.toString(minutes);
        String s = Integer.toString(seconds);
        String timeLeftFormat = m+":"+s;
        mTextViewCountDown.setText(timeLeftFormat);
    }

    private void updateText(long timeleft){

    }

    private void terminateFunction(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("End washing machine");
        alert.setMessage("Are you sure that you want to end the function of washing machine ?");
        alert.setCancelable(false);
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(Activity7.this,MainActivity.class));
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
}
