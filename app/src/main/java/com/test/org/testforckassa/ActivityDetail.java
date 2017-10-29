package com.test.org.testforckassa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ActivityDetail extends AppCompatActivity {
    private EditText editTextHead;
    private EditText editTextDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextHead = (EditText) findViewById(R.id.editHead);
        editTextDescription = (EditText) findViewById(R.id.editDescription);

    }
}
