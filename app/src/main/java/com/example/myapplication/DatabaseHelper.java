package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9978314560,'VAISHNAVI',494210.00,'vaishnavi9021@gmail.com','XXXXXXXXXXXX7130','UCOB5643190')");
        db.execSQL("insert into user_table values(9651284602,'PURVA',38901.00,'dolphinlover45@gmail.com','XXXXXXXXXXXX8875','MAHB9061238')");
        db.execSQL("insert into user_table values(9456601233,'AMAN',435970.00,'shutterbug@gmail.com','XXXXXXXXXXXX3026','SBIN7654120')");
        db.execSQL("insert into user_table values(9341052139,'AACHAL',375000.50,'aachal451@gmail.com','XXXXXXXXXXXX2981','HDFC4561230')");
        db.execSQL("insert into user_table values(9103623892,'ADWAIT',261190.00,'adwait10@gmail.com','XXXXXXXXXXXX2472','CNRB9876412')");
        db.execSQL("insert into user_table values(9051274891,'KSHIPRA',67230.50,'krajguru3124.03@gmail.com','XXXXXXXXXXXX9761','ICIB1360912')");
        db.execSQL("insert into user_table values(8901345812,'SACHI ',78123.00,'sachi.burde@gmail.com','XXXXXXXXXXXX6139','punb0065170')");
        db.execSQL("insert into user_table values(8524481920,'AISHWARYA',418570.50,'renu.ar13@gmail.com','XXXXXXXXXXXX6974','UTIB0967541')");
        db.execSQL("insert into user_table values(7912903583,'MANISH',500000.00,'manish2021@gmail.com','XXXXXXXXXXXX5012','UNIB1245890')");
        db.execSQL("insert into user_table values(7058834166,'SHARDUL',28900.50,'ironmanshardul83@gmail.com','XXXXXXXXXXXX1392','ARNB9012457')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
