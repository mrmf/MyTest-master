package com.iranpl.monsef.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteEditActivity extends AppCompatActivity {
    String noteId;
    EditText titleEdit,texEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        final NoteDbHelper helper = new NoteDbHelper(NoteEditActivity.this, "Notes", null, 1);
        Intent intent = getIntent();
        noteId = intent.getStringExtra("nid");
        titleEdit = findViewById(R.id.noteTitle);
        texEdit = findViewById(R.id.noteText);
        NoteModel nm = helper.getNote(noteId);
        if (nm != null){
            titleEdit.setText(nm.getTitle());
            texEdit.setText(nm.getText());
        }

        Button btnEditNotesActivity = findViewById(R.id.btnEditNotesActivity);
        btnEditNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tit = titleEdit.getText().toString();
                String txt = texEdit.getText().toString();

                if(tit == "" || txt == "") {
                    Toast.makeText(NoteEditActivity.this, "فیلدهای عنوان و متن یادداشت نباید خالی باشد", Toast.LENGTH_LONG).show();
                }
                else {

                    Intent intent = getIntent();
                    //intent.putExtra("nid", noteId);
                    helper.update(noteId, tit, txt);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        Button btnCancelEditNotesActivity = findViewById(R.id.btnCancelEditNotesActivity);
        btnCancelEditNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                //i.putExtra("nid", noteId);
                setResult(Activity.RESULT_CANCELED, i);
                finish();
            }
        });

    }
}
