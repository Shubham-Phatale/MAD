package com.example.placementportal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    String create_table = "create table user(s_id integer primary key autoincrement,s_pass text not null)";
    public DBHelper(Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user ");
    }

    public boolean insert(String id,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("s_id",id);
        cv.put("s_pass",password);
        long ins = db.insert("user",null,cv);
        if (ins==-1) return false;
        else return true;
    }
    public Boolean checkid (String stdid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where s_id=?", new String[]{stdid});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
    public Boolean idpass(String id, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where s_id=? and s_pass=?", new String[]{id,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
