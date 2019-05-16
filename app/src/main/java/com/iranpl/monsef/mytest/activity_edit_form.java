package com.iranpl.monsef.mytest;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.regex.Pattern;

import static com.orhanobut.hawk.Hawk.*;
import static java.lang.Integer.*;

public class activity_edit_form extends AppCompatActivity {

    EditText firstName,lastName, emailAddr, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);

        firstName = findViewById(R.id.firName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        emailAddr = findViewById(R.id.email);
        Button btnShowActivity2 = findViewById(R.id.btnShowActivity2);

        btnShowActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = firstName.getText().toString();
                String family = lastName.getText().toString();
                String email = emailAddr.getText().toString();
                int num = 0;
                try {
                   num = Integer.parseInt(age.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(activity_edit_form.this, "سن باید از نوع عدد صحیح باشد ", Toast.LENGTH_LONG).show();
                }
                if(fname.length() < 3 || family.length() < 3) {
                    Toast.makeText(activity_edit_form.this, "تعداد حروف نام و نام خانوادگی نباید کمتر از 3 باشد", Toast.LENGTH_LONG).show();
                }
                else if (num > 999){
                    Toast.makeText(activity_edit_form.this, "سن باید حداکثر 3 رقم باشد ", Toast.LENGTH_LONG).show();
                }
                else if ( !checkEmailFormat(email) ){
                    Toast.makeText(activity_edit_form.this, "فرمت ایمیل صحیح نمی باشد ", Toast.LENGTH_LONG).show();
                }
                else {

                    Intent intent = new Intent(activity_edit_form.this, show_form_activity.class);
                    intent.putExtra("fname", fname);
                    intent.putExtra("lname", family);
                    intent.putExtra("age", (String.valueOf(num)));
                    intent.putExtra("email", email);
                    startActivityForResult(intent, 329);

                }
            }
            final Pattern rfc2822 = Pattern.compile ("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

            public boolean checkEmailFormat (String address)
            {
                if (rfc2822.matcher(address).matches()) return true;

                return false;
            }
        });
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_edit_form.this, "It is OK.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        firstName = findViewById(R.id.firName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        emailAddr = findViewById(R.id.email);

        if (requestCode == 329) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String userName = PreferenceManager.getDefaultSharedPreferences(this).getString("firstname", "!!!!")+
                        " "+ PreferenceManager.getDefaultSharedPreferences(this).getString("lastname", "!!!!") + " " + "ثبت اطلاعات با موفقیت انجام شد";
                userName = get("firstname")+ " " + get("lastname") + " " + "ثبت اطلاعات با موفقیت انجام شد";
                Toast.makeText(this, userName , Toast.LENGTH_SHORT).show();
                firstName.setText("");
                lastName.setText("");
                age.setText("");
                emailAddr.setText("");

            }
            else {
                Toast.makeText(this, "ثبت اطلاعات لغو شد!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
