package com.iranpl.monsef.mytest;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HTTP_Test_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        final ArrayList<String> arrays = new ArrayList<String>();
        Button btnShowActivityCity = findViewById(R.id.btnShowActivityCity);
        RecyclerView rv = findViewById(R.id.recycler);
        Hawk.init(HTTP_Test_Activity.this).build();
        btnShowActivityCity.setOnClickListener(new View.OnClickListener() {
            final TextView city = findViewById(R.id.cityName);
            String cityStr = "";

            @Override
            public void onClick(View v) {
                action(city,arrays);

            }
        });

    }
    public void action(TextView city,ArrayList<String> lists){
        final ArrayList<String> arrays = lists;
        final String cityStr = city.getText().toString();
        final TextView fajr = findViewById(R.id.txtfajr);
        final TextView sunrise = findViewById(R.id.txtsunrise);
        final TextView zohr = findViewById(R.id.txtzohr);
        final TextView asr = findViewById(R.id.txtasr);
        final TextView maghrib = findViewById(R.id.txtmaghrib);
        final TextView isha = findViewById(R.id.txtisha);
        String url = "http://api.aladhan.com/v1/timingsByCity?city="+cityStr+"&country=Iran&method=8";
        AsyncHttpClient Ahc = new AsyncHttpClient();
        Ahc.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                Gson gson = new Gson();
                com.iranpl.monsef.mytest.PrayTimesClass ptc = gson.fromJson(response.toString(), com.iranpl.monsef.mytest.PrayTimesClass.class);
                //String result = response.getString("data");
                //JSONObject object1 = new JSONObject(result);
                //String resulttimings = object1.getString("timings");
                //JSONObject object2 = new JSONObject(resulttimings);
                //AzanModel azantimings = new AzanModel(cityStr, object2.getString("Fajr"),
                //object2.getString("Sunrise"),object2.getString("Dhuhr"),
                //object2.getString("Asr"),object2.getString("Maghrib"),
                //object2.getString("Isha"));
                AzanModel azantimings = new AzanModel(cityStr, ptc.getData().getTimings().getFajr(),
                        ptc.getData().getTimings().getSunrise(), ptc.getData().getTimings().getDhuhr(),
                        ptc.getData().getTimings().getAsr(), ptc.getData().getTimings().getMaghrib(),
                        ptc.getData().getTimings().getIsha());
                fajr.setText("اذان صبح :" + azantimings.result1);
                sunrise.setText("طلوع آفتاب :" + azantimings.result2);
                zohr.setText("اذان ظهر :" + azantimings.result3);
                asr.setText("وقت عصر :" + azantimings.result4);
                maghrib.setText("اذان مغرب :" + azantimings.result5);
                isha.setText("وقت عشا :" + azantimings.result6);


                if (arrays.contains(cityStr)) {
                    Hawk.delete(cityStr);
                    Hawk.put(cityStr, azantimings);
                } else {
                    arrays.add(cityStr);
                    Hawk.put(cityStr, azantimings);
                }
                if(Hawk.get("Lists") != null){
                    Hawk.delete("lists");
                    Hawk.put("Lists",arrays);
                }
                else Hawk.put("Lists",arrays);
                    RecyclerView recycler = findViewById(R.id.recycler);
                    ArrayList<String> al = Hawk.get("Lists");
                    TestAdapterLocation adapter = new TestAdapterLocation(arrays, new TestAdapterLocation.OnItemClickListener() {
                        @Override
                        public void onItemClick(TextView item) {
                            //city.setText(item.getText());
                            action(item,arrays);
                            Toast.makeText(HTTP_Test_Activity.this, item.getText(), Toast.LENGTH_LONG).show();
                        }
                    });
                    recycler.setAdapter(adapter);
                    recycler.setLayoutManager(new LinearLayoutManager(HTTP_Test_Activity.this, RecyclerView.HORIZONTAL, false));


                }catch(Exception e){
                    e.printStackTrace();
                }

                }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    fajr.setText("");
                    sunrise.setText("");
                    zohr.setText("");
                    asr.setText("");
                    maghrib.setText("");
                    isha.setText("");
                    }
        });
    }
}

