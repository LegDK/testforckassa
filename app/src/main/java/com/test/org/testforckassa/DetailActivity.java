package com.test.org.testforckassa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private EditText editTextHead;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonDelete;
    private DBHelp dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextHead = (EditText) findViewById(R.id.editHead);
        editTextDescription = (EditText) findViewById(R.id.editDescription);
        dbHelp = new DBHelp(this);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        final Intent intent = getIntent();
        final String action = intent.getStringExtra("Action");
        final Integer Id = intent.getIntExtra("Id", 0);
        switch (action) {
            case "Read":
                editTextHead.setRawInputType(0);
                editTextDescription.setRawInputType(0);
                editTextHead.setText(intent.getStringExtra("Head"));
                editTextDescription.setText(intent.getStringExtra("Description"));
                buttonSave.setVisibility(View.GONE);
                buttonDelete.setVisibility(View.GONE);
                break;
            case "Insert":
                editTextHead.setText("");
                editTextDescription.setText("");
                break;
            case "Update":
                editTextHead.setText(intent.getStringExtra("Head"));
                editTextDescription.setText(intent.getStringExtra("Description"));
                break;
        }
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String head = editTextHead.getText().toString();
                String description = editTextDescription.getText().toString();
                if (head.equals("") || description.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Пожалуйста заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    switch (action) {
                        case "Insert":
                            dbHelp.insertIntoDB(head, description);
                            finish();
                            break;
                        case "Update":
                            dbHelp.updateARow(Id, head, description);
                            finish();
                            break;
                    }
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelp.deleteARow(Id);
                finish();
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
