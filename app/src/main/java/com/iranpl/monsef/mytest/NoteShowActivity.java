package com.iranpl.monsef.mytest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iranpl.monsef.mytest.R;

public class NoteShowActivity extends AppCompatActivity {
    String noteId;
    TextView titleShow,textShow;
    final NoteDbHelper helper = new NoteDbHelper(NoteShowActivity.this, "Notes", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Intent intent = getIntent();
        noteId = intent.getStringExtra("nid");
        titleShow = findViewById(R.id.noteTitle);
        textShow = findViewById(R.id.noteText);
        NoteModel nm = helper.getNote(noteId);
        if (nm != null){
            titleShow.setText(nm.getTitle());
            textShow.setText(nm.getText());
        }
        Button btnShowNotesActivity = findViewById(R.id.btnCancelShowNotesActivity);
        btnShowNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = getIntent();
                    setResult(Activity.RESULT_CANCELED, intent);
                    finish();
                }
        });
        Button btnEditNotesActivity = findViewById(R.id.btnEditNoteActivity);
        btnEditNotesActivity.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v){
                   Intent intent2 = new Intent(NoteShowActivity.this, NoteEditActivity.class);
                   intent2.putExtra("nid",noteId);
                   startActivityForResult(intent2,332);
                }
            });
        Button btnDelNotesActivity = findViewById(R.id.btnDelNoteActivity);
        btnDelNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                helper.delete(noteId);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 332) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                noteId = data.getStringExtra("nid");
                titleShow = findViewById(R.id.noteTitle);
                textShow = findViewById(R.id.noteText);
                NoteModel nm = helper.getNote(noteId);
                if (nm != null){
                    titleShow.setText(nm.getTitle());
                    textShow.setText(nm.getText());
                }
                Button btnShowNotesActivity = findViewById(R.id.btnCancelShowNotesActivity);
                btnShowNotesActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = getIntent();
                        setResult(Activity.RESULT_CANCELED, intent);
                        finish();
                    }
                });
                Button btnEditNotesActivity = findViewById(R.id.btnEditNoteActivity);
                btnEditNotesActivity.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v){
                        Intent intent2 = new Intent(NoteShowActivity.this, NoteEditActivity.class);
                        intent2.putExtra("nid",noteId);
                        startActivityForResult(intent2,332);
                    }
                });
                Button btnDelNotesActivity = findViewById(R.id.btnDelNoteActivity);
                btnDelNotesActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = getIntent();
                        helper.delete(noteId);
                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });

            }
        }
    }
}
