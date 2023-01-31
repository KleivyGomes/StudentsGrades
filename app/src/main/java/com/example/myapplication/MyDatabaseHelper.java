package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_Version = 1;

    private static final String TABLE_NAME = "Mydatabase";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TEST_1 = "test1";
    private static final String COLUMN_TEST_2 = "test2";
    private static final String COLUMN_HOMEWORK = "homework";
    private static final String COLUMN_OEE = "oee";
    private static final String COLUMN_EVALUATE = "EVELUATION";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TEST_1 + " TEXT, " +
                COLUMN_TEST_2 + " TEXT, " +
                COLUMN_HOMEWORK + " TEXT, " +
                COLUMN_OEE + " TEXT, " +
                COLUMN_EVALUATE + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean add(String name, String test1, String test2, String homework,
                String oee, String evaluate){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TEST_1, test1);
        cv.put(COLUMN_TEST_2, test2);
        cv.put(COLUMN_HOMEWORK, homework);
        cv.put(COLUMN_OEE, oee);
        cv.put(COLUMN_EVALUATE, evaluate);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1){
            return false;
        }

        return true;
    }

    Cursor ReadAll(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    boolean delete(Integer row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(row_id)});
        if(result == -1){
            return false;
        }
        return true;
    }
}
