package com.test.org.testforckassa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

public class ActivityDetail extends AppCompatActivity {
    private EditText editTextHead;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonDelete;
    private DBHelp dbHelp;
    private MainActivity mainActivity;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextHead = (EditText) findViewById(R.id.editHead);
        editTextDescription = (EditText) findViewById(R.id.editDescription);
        
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        final Intent intent = getIntent();
        final String action = intent.getStringExtra("Action");
        final Integer Id =intent.getIntExtra("Id",0);
        switch (action){
            case "Read":
                editTextHead.setRawInputType(0);
                editTextDescription.setRawInputType(0);
                editTextHead.setText(intent.getStringExtra("Head"));
                editTextDescription.setText(intent.getStringExtra("Description"));
                buttonSave.setEnabled(false);
                buttonDelete.setEnabled(false);
                break;
            case "Insert":
                editTextHead.setText("");
                editTextDescription.setText("");
                buttonSave.setEnabled(true);
                buttonDelete.setEnabled(true);
                break;
            case "Update":
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
                    switch (action){
                        case "Insert":
                            dbHelp = new DBHelp(ActivityDetail.this);
                            dbHelp.insertIntoDB(head, description);
                            dbHelp.close();
                            finish();
                            break;
                        case "Update":
                            dbHelp = new DBHelp(ActivityDetail.this);
                            dbHelp.updateARow(Id,head,description);
                            dbHelp.close();
                            finish();
                            break;
                    }

//                    if (action=="Update"){
//
//                    }
                }


            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelp = new DBHelp(ActivityDetail.this);
                dbHelp.deleteARow(Id);
                dbHelp.close();
                finish();
            }
        });



    }

}
