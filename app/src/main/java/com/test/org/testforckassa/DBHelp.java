package com.test.org.testforckassa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 28.10.2017.
 */

public class DBHelp extends SQLiteOpenHelper {

   public static final int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME = "listDB";
   public static final String TABLE_LIST = "lists";
   public static final String KEY_ID = "_id";
   public static final String KEY_NAME = "name";
   public static final String KEY_DESCRIPTION = "description";
    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table" + TABLE_LIST + "(" + KEY_ID + " integer primary key," + KEY_NAME +
        " text, " + KEY_DESCRIPTION + " text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_LIST);

        onCreate(sqLiteDatabase);
    }
}
