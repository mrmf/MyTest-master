package com.iranpl.monsef.mytest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class show_list_with_recycler extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_with_recycler);


        ArrayList<PersonInfo> list = new ArrayList<>();
        PersonInfo p1 = new PersonInfo("Pouya Heidari", "09127788999", "Tehran", R.drawable.p1);
        PersonInfo p2 = new PersonInfo("Majid Bagheri", "09127788999", "Yazd", R.drawable.p1);
        PersonInfo p3 = new PersonInfo("Zakaria Tajik", "09127788999", "Tabriz", R.drawable.p3);
        PersonInfo p4 = new PersonInfo("Mohammad Jafari", "09127788999", "Tehran", R.drawable.p4);
        PersonInfo p5 = new PersonInfo("Farshad Danaee", "09127788999", "Boushehr", R.drawable.p3);
        PersonInfo p6 = new PersonInfo("Mohammadreza Farahani", "09127788999", "Tehran", R.drawable.p1);
        PersonInfo p7 = new PersonInfo("Mohammadreza Monsef", "09127788999", "Isfahan", R.drawable.p4);
        PersonInfo p8 = new PersonInfo("Mehrshad Mollaafzal", "09127788999", "Yazd", R.drawable.p3);
        PersonInfo p9 = new PersonInfo("Reza Sadeghi", "09127788999", "Tehran", R.drawable.p4);
        PersonInfo p10 = new PersonInfo("Mohsen KarimNezhad", "09127788999", "Fars", R.drawable.p1);
        //PersonInfo p10 = new PersonInfo("Mohsen KarimNezhad", "09127788999", "", new ImageView(this));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);
        list.add(p10);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView home = (ImageView) findViewById(R.id.act_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        RecyclerView recycler = findViewById(R.id.recycler);

        TestAdapter adapter = new TestAdapter(list);

        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


    }


}
    class PersonInfo {
    String name;
    String mobile;
    String Address;
    int img;
    public PersonInfo(String name, String mobile, String Address, int img){
        this.name = name;
        this.mobile = mobile;
        this.Address = Address;
        this.img = img;
    }


    }