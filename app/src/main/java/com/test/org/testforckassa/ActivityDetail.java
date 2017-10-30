package com.test.org.testforckassa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

public class ActivityDetail extends AppCompatActivity {
    private EditText editTextHead;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonDelete;
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

    }
}
