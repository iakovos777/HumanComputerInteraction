package com.example.iakovos.washingmachine;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class Activity8 extends AppCompatActivity {

    private ListView output;
    private MyDBHandler db;
    private Button btnDelete;
    private Button back;
    private Button ok;
    private String[] dbArray;
    private Integer programId;
    private String dbString;
    private ImageButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);
        btnDelete = (Button) findViewById(R.id.button22);
        back = (Button) findViewById(R.id.button21);
        ok = (Button) findViewById(R.id.button23);
        help = (ImageButton) findViewById(R.id.imageButton7);
        db = new MyDBHandler(this, null, null, 2);
        output = (ListView) findViewById(R.id.listView1);
       final Cursor cursor = db.getAllPrograms();
       if(cursor==null){
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
           alert.setTitle("NULL");
           alert.setCancelable(true);
           alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   dialog.cancel();
               }
           });

           AlertDialog a = alert.create();
           a.show();
       }

       dbArray = new String[]{
               MyDBHandler._id,
               MyDBHandler.COLUMN_PROGRAMNAME,
               MyDBHandler.KEY_DURATION,
               MyDBHandler.KEY_TEMPERATURE,
               MyDBHandler.KEY_SPINS
       };

       int[] widgets = new int[] {
               R.id.id1,
               R.id.name,
               R.id.duration,
               R.id.spins,
               R.id.temperature
       };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.program_info,cursor,dbArray, widgets,0);
        output.setAdapter(cursorAdapter);



        output.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> output, View view, int position, long id ) {
                Cursor itemCursor = (Cursor) Activity8.this.output.getItemAtPosition(position);
                programId = itemCursor.getInt(itemCursor.getColumnIndex(MyDBHandler._id));
                view.setSelected(true);


            }
        });


        btnDelete.setOnClickListener(onDelete);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity8.this,Activity5.class));
            }
        });



        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(Activity8.this, Activity10.class);
                Bundle bundle = new Bundle();
                if(programId.equals(null)) {
                    return;

                }

                String select = db.getKeyDuration(programId);
                bundle.putString("duration",select);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

    }

    private View.OnClickListener onDelete = new View.OnClickListener(){
        // @Override
        public void onClick(View view){
            if(programId.equals(null)) {
                return;

            }
            else{
                deleteItem(programId);
                programId = null;
            }
        }
    };



    public void deleteItem(Integer s){
       final Integer st = s;
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete program");
        builder.setMessage("Are you sure that you want to delete your program ?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                db.deleteProgram(st);
                final Cursor cursor = db.getAllPrograms();
                dbArray = new String[]{
                        MyDBHandler._id,
                        MyDBHandler.COLUMN_PROGRAMNAME,
                        MyDBHandler.KEY_DURATION,
                        MyDBHandler.KEY_TEMPERATURE,
                        MyDBHandler.KEY_SPINS};

                int[] widgets = new int[]{
                        R.id.id1,
                        R.id.name,
                        R.id.duration,
                        R.id.spins,
                        R.id.temperature
                };

                SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(Activity8.this, R.layout.program_info, cursor, dbArray, widgets, 0);
                output.setAdapter(cursorAdapter);
                String message = "Your program has been deleted...";
                Toast.makeText(Activity8.this, message, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.show();




    }
}
