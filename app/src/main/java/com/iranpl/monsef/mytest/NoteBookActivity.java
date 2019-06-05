package com.iranpl.monsef.mytest;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import static com.orhanobut.hawk.Hawk.get;

public class NoteBookActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NoteDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notebook_with_recycler);

        helper = new NoteDbHelper(NoteBookActivity.this, "Notes", null, 1);
        ArrayList<NoteModel> list = null;
        list = helper.getNotes();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView home = (ImageView) findViewById(R.id.act_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });
        TextView textView = findViewById(R.id.link1);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NoteBookActivity.this, HTTP_Test_Activity.class);
                startActivity(intent);
            }
        });
        ImageView addnoteView = (ImageView) findViewById(R.id.note_add);
        addnoteView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2 = new Intent(NoteBookActivity.this, NoteAddActivity.class);
                startActivityForResult(intent2,330);
            }
        });
        RecyclerView recycler = findViewById(R.id.recycler);

        //ArrayList<String> al = Hawk.get("Lists");
        NotesAdapter adapter = new NotesAdapter(list, new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View item) {

                Intent intent3 = new Intent(NoteBookActivity.this, NoteShowActivity.class);
                TextView tv = item.findViewById(R.id.txtnoteid);
                intent3.putExtra("nid",tv.getText());
                startActivityForResult(intent3,331);
                //Toast.makeText(NoteBookActivity.this, item.getText(), Toast.LENGTH_LONG).show();
            }
        });

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 330) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String reg_title = data.getStringExtra("ntitle");
                String reg_text = data.getStringExtra("ntext");
                helper.insertData(reg_title, reg_text);
                String alert = "ثبت اطلاعات با موفقیت انجام شد";
                Toast.makeText(this, alert, Toast.LENGTH_LONG).show();
                ArrayList<NoteModel> list = helper.getNotes();
                RecyclerView recycler = findViewById(R.id.recycler);

                //ArrayList<String> al = Hawk.get("Lists");
                NotesAdapter adapter = new NotesAdapter(list, new NotesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View item) {
                        //action(item,arrays);
                        Intent intent3 = new Intent(NoteBookActivity.this, NoteShowActivity.class);
                        TextView tv = item.findViewById(R.id.txtnoteid);
                        intent3.putExtra("nid", tv.getText());
                        startActivityForResult(intent3, 331);
                        //   Toast.makeText(NoteBookActivity.this, tv.getText(), Toast.LENGTH_LONG).show();
                    }
                });

                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

            } else {
                Toast.makeText(this, "ثبت اطلاعات لغو شد!", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == 331) {

                if (resultCode == RESULT_OK) {
                    String alert = "حذف اطلاعات انجام شد";
                    Toast.makeText(this, alert, Toast.LENGTH_LONG).show();
                    ArrayList<NoteModel> list = helper.getNotes();
                    RecyclerView recycler = findViewById(R.id.recycler);

                    NotesAdapter adapter = new NotesAdapter(list, new NotesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View item) {
                            Intent intent3 = new Intent(NoteBookActivity.this, NoteShowActivity.class);
                            TextView tv = item.findViewById(R.id.txtnoteid);
                            intent3.putExtra("nid", tv.getText());
                            startActivityForResult(intent3, 331);
                            //   Toast.makeText(NoteBookActivity.this, tv.getText(), Toast.LENGTH_LONG).show();
                        }
                    });

                    recycler.setAdapter(adapter);
                    recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                }
            }
        }
    }

