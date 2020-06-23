package com.example.iakovos.washingmachine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Activity6 extends AppCompatActivity {


    private String time;
    private AutoCompleteTextView auto1;
    private AutoCompleteTextView auto3;
    private AutoCompleteTextView auto4;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ImageButton help;
    private final Context context=this;
    private MyDBHandler db;
    private Program p;
    private String[] sp;
    private String[] dur;
    private String[] t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        auto1 = (AutoCompleteTextView) findViewById(R.id.editText);
        auto3 = (AutoCompleteTextView) findViewById(R.id.editText4);
        auto4 = (AutoCompleteTextView) findViewById(R.id.editText5);
        auto1.setSelectAllOnFocus(true);
        auto3.setSelectAllOnFocus(true);
        auto4.setSelectAllOnFocus(true);
        btn1= (Button) findViewById(R.id.button15);
        btn2= (Button) findViewById(R.id.button16);
        btn3= (Button) findViewById(R.id.button17);
        db = new MyDBHandler(this,null,null,2);
        sp = getResources().getStringArray(R.array.spins_array);
        dur = getResources().getStringArray(R.array.duration_array);
        t = getResources().getStringArray(R.array.temperature_array);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sp);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dur);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,t);

        auto1.setThreshold(0);
        auto3.setThreshold(0);
        auto4.setThreshold(0);

        auto1.setAdapter(adapter2);
        auto4.setAdapter(adapter1);
        auto3.setAdapter(adapter3);




        help = (ImageButton) findViewById(R.id.imageButton5);
        String d = auto1.getText().toString();
        String sp = auto4.getText().toString();
        String t = auto3.getText().toString();
        if(d.matches("")){
            Toast.makeText(this,"You did not enter duration",Toast.LENGTH_LONG).show();
        }

        if(sp.matches("")){
            Toast.makeText(this,"You did not enter spins",Toast.LENGTH_LONG).show();
        }

        if(t.matches("")){
            Toast.makeText(this,"You did not enter temperature",Toast.LENGTH_LONG).show();
        }

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity6.this,HelpActivity.class));
            }
        });


        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                    startActivity(new Intent(Activity6.this,Activity5.class));
            }
        });



        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(Activity6.this, Activity10.class);
                Bundle bundle = new Bundle();
                bundle.putString("duration",auto1.getText().toString());
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String duration = auto1.getText().toString();
                String spins = auto4.getText().toString();
                String temp = auto3.getText().toString();
                p = new Program();
                p.setDuration(duration);
                p.setSpins(spins);
                p.setTemperature(temp);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Name of your program");
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = input.getText().toString();
                        p.setProgramName(name);
                        db.addProgram(p);
                        dialog.cancel();
                        String message = "Your program has been saved...";
                        Toast.makeText(Activity6.this, message, Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog a = builder.create();
                a.show();

            }
        });
    }


}
