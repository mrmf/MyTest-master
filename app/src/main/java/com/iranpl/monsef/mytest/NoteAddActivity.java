package com.iranpl.monsef.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteAddActivity extends AppCompatActivity {

    EditText titleEdit,textEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEdit = findViewById(R.id.noteTitle);
        textEdit = findViewById(R.id.noteText);

        Button btnShowNotesActivity = findViewById(R.id.btnShowNotesActivity);
        btnShowNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tit = titleEdit.getText().toString();
                String txt = textEdit.getText().toString();

                if(tit == "" || txt == "") {
                    Toast.makeText(NoteAddActivity.this, "فیلدهای عنوان و متن یادداشت نباید خالی باشد", Toast.LENGTH_LONG).show();
                }
                else {

                    Intent intent = getIntent();
                    intent.putExtra("ntitle", tit);
                    intent.putExtra("ntext", txt);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        Button btnCancelShowNotesActivity = findViewById(R.id.btnCancelShowNotesActivity);
        btnCancelShowNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(Activity.RESULT_CANCELED, i);
                finish();
            }
        });

    }
}
