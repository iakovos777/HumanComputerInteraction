package com.example.iakovos.washingmachine;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =2;
    private static final String DATABASE_NAME = "program.db";
    public static final String TABLE_PROGRAM = "program";
    public static final String _id = "_id";
    public static final String COLUMN_PROGRAMNAME = "programName";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_TEMPERATURE = "temperature";
    public static final String KEY_SPINS = "spins";
    private static int count =0 ;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PROGRAM + "(" +
               _id + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                COLUMN_PROGRAMNAME + " TEXT, " +
                KEY_DURATION + " TEXT, " +
                KEY_TEMPERATURE + " TEXT, " +
                KEY_SPINS + " TEXT " +")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAM);
        onCreate(db);
    }

    //Add your program to the database
    public void addProgram(Program p){
        count++;
        ContentValues values = new ContentValues();
        //values.put(_id, p.getId());
        values.put(COLUMN_PROGRAMNAME, p.getProgramName());
        values.put(KEY_DURATION, p.getDuration());
        values.put(KEY_TEMPERATURE, p.getTemperature());
        values.put(KEY_SPINS, p.getSpins());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PROGRAM, null, values);
        db.close();
    }

    //Delete program from database
    public void deleteProgram(Integer idPr){
        SQLiteDatabase db = getWritableDatabase();
        String idProgram = Integer.toString(idPr);
        db.execSQL("DELETE FROM " + TABLE_PROGRAM + " WHERE " + _id + "=\"" + idProgram + "\";");
    }

    //Search specific program

    public String getKeyDuration(Integer idPr){
        SQLiteDatabase db =  getReadableDatabase();
        String idProgram = Integer.toString(idPr);
        //String q = "SELECT "+KEY_DURATION+" FROM "+TABLE_PROGRAM+" WHERE "+ _id +" = ? ";
        Cursor res = db.rawQuery("SELECT "+KEY_DURATION+" FROM "+TABLE_PROGRAM+" WHERE "+ _id +" =  "+idProgram+";", null);
        res.moveToFirst();
        String d =  res.getString(res.getColumnIndex("duration"));
        return d;
    }

    //RESET ALL DATABASE

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT COUNT(*) FROM " + TABLE_PROGRAM ;
        if(!count.equals("0")) {
            String query = "DELETE FROM " + TABLE_PROGRAM;
            db.execSQL(query);
        }
        else
            return;

    }

    //Print out the database as a string
    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db =  getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PROGRAM + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("programName"))!=null){
                dbString += c.getString(c.getColumnIndex("programName"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }
    //Print out the database as a string
    public String[] dbToStringArray(){
        if(getCount()!=0) {
            String dbString = "";

            int i = 0;
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_PROGRAM + " WHERE 1";

            //Cursor point to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("programName")) != null) {
                    i++;
                }
            }
            String[] str = new String[i];

           for (int x=0;x<i;x++) {
                if (c.getString(x)!= null) {
                    str[x] = c.getString(c.getColumnIndex("programName"));


                }
            }
            db.close();
            return str;
        }
        else{
            return null;
        }
    }

    public int getCount(){
        return this.count;
    }

    public Cursor getAllPrograms(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] str = new String[] {_id,
                COLUMN_PROGRAMNAME, KEY_DURATION, KEY_TEMPERATURE, KEY_SPINS};
        Cursor res = db.query(TABLE_PROGRAM, new String[] {},
                null, null, null, null, null);;
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int num = (int) DatabaseUtils.queryNumEntries(db, TABLE_PROGRAM);
        return num;
    }
}