package com.test.org.testforckassa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextHead;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonDelete;
    private DBHelp dbHelp;
    private MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextHead = (EditText) findViewById(R.id.editHead);
        editTextDescription = (EditText) findViewById(R.id.editDescription);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Intent intent = getIntent();
        String access = intent.getStringExtra("Access");
        switch (access){
            case "Read":
                editTextHead.setInputType(InputType.TYPE_NULL);
                editTextDescription.setInputType(InputType.TYPE_NULL);
                break;
            case "Write":
                editTextHead.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextDescription.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
        }
        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        ContentValues contentValues = new ContentValues();
        String head = editTextHead.getText().toString();
        String description = editTextDescription.getText().toString();
        SQLiteDatabase sqLiteDatabase = dbHelp.getWritableDatabase();
        switch (view.getId()){
            case R.id.buttonSave:
                contentValues.put("head", head);
                contentValues.put("description", description);
                sqLiteDatabase.insert("lists",null,contentValues);
                break;
            case R.id.buttonDelete:

                break;
        }
    }
}
