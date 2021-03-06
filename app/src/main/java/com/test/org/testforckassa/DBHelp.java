package com.test.org.testforckassa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.org.testforckassa.models.ListItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 28.10.2017.
 */

public class DBHelp extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "notes";
    private static final String DATABASE_NAME = "notesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String CR_TABLE = "create table " + TABLE_NAME +
            "(_id integer primary key autoincrement, head TEXT ,description TEXT)";

    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertIntoDB(String head, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("head", head);
        contentValues.put("description", description);
        sqLiteDatabase.insert("notes", null, contentValues);
        sqLiteDatabase.close();
    }
    public List<ListItem> getDataFromDB() {
        List<ListItem> listItems = new ArrayList<ListItem>();
        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ListItem listItem = new ListItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listItems.add(listItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return listItems;
    }

    public void deleteARow(Integer id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "_id=" + id, null);
        sqLiteDatabase.close();
    }

    public void updateARow(Integer id, String head, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("head", head);
        contentValues.put("description", description);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "_id = " + id, null);
        sqLiteDatabase.close();
    }


}
