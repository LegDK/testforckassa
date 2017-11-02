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
import android.widget.Toast;

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
        String action = intent.getStringExtra("Action");
        switch (action){
            case "Read":
                editTextHead.setInputType(InputType.TYPE_NULL);
                editTextDescription.setInputType(InputType.TYPE_NULL);
                editTextHead.setText(intent.getStringExtra("Head"));
                editTextDescription.setText(intent.getStringExtra("Description"));
                buttonSave.setEnabled(false);
                buttonDelete.setEnabled(false);
                break;
            case "Insert":
                editTextHead.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextDescription.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextHead.setText("");
                editTextDescription.setText("");
                buttonSave.setEnabled(true);
                buttonDelete.setEnabled(true);
                break;
            case "Update":
                editTextHead.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextDescription.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextHead.setText(intent.getStringExtra("Head"));
                editTextDescription.setText(intent.getStringExtra("Description"));
                buttonSave.setEnabled(true);
                buttonDelete.setEnabled(true);
                break;
        }
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String head = editTextHead.getText().toString();
                String description = editTextDescription.getText().toString();
                if (head.equals("")||description.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Пожалуйста заполните все поля",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbHelp =new DBHelp(ActivityDetail.this);
                    dbHelp.insertIntoDB(head,description);
                    finish();
//                    mainActivity.onCreateMainList();
                }
//                contentValues.put("head", head);
//                contentValues.put("description", description);
//                sqLiteDatabase.insert("lists",null,contentValues);

            }
        });
        buttonDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonSave:

                break;
            case R.id.buttonDelete:

                break;
        }
    }
}
