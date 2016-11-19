package com.example.weather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TextView city,weather,temp,dir,sc,aqi;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    private TextView max1,max2,max3,max4,max5,max6,max7;
    private TextView min1,min2,min3,min4,min5,min6,min7;
    private TextView comf1,comf2;
    private TextView cw1,cw2;
    private TextView drsg1,drsg2;
    private TextView flu1,flu2;
    private TextView sport1,sport2;
    String jonString;
    ProgressDialog progressDialog;
    private static final int SET = 1;
    private Handler handler = new Handler(){

        public void handleMessage(Message msg)
        {
            switch (msg.what) {
                case SET:
                    Util util = new Util();
                    try {
                        List<Map<String, Object>> all = util
                                .getInformation(msg.obj.toString());
//                                .getInformation(msg.obj.toString());
                        Iterator<Map<String, Object>> iterator = all.iterator();
                        while (iterator.hasNext()) {
                            Map<String, Object> map = iterator.next();
                            city.setText(map.get("cityName")
                                    .toString());
                            weather.setText(map.get("weather").toString());
                            temp.setText(map.get("temp").toString());
                            dir.setText(map.get("dir").toString());
                            sc.setText(map.get("sc").toString());
                            aqi.setText(map.get("aqi").toString());

                            txt1.setText( map.get(Util.txt[0]).toString());
                            txt2.setText( map.get(Util.txt[1]).toString());
                            txt3.setText( map.get(Util.txt[2]).toString());
                            txt4.setText( map.get(Util.txt[3]).toString());
                            txt5.setText( map.get(Util.txt[4]).toString());
                            txt6.setText( map.get(Util.txt[5]).toString());
                            txt7.setText( map.get(Util.txt[6]).toString());

                            min1.setText(map.get(Util.min[0]).toString());
                            min2.setText(map.get(Util.min[1]).toString());
                            min3.setText(map.get(Util.min[2]).toString());
                            min4.setText(map.get(Util.min[3]).toString());
                            min5.setText(map.get(Util.min[4]).toString());
                            min6.setText(map.get(Util.min[5]).toString());
                            min7.setText(map.get(Util.min[6]).toString());

                            max1.setText(map.get(Util.max[0]).toString());
                            max2.setText(map.get(Util.max[1]).toString());
                            max3.setText(map.get(Util.max[2]).toString());
                            max4.setText(map.get(Util.max[3]).toString());
                            max5.setText(map.get(Util.max[4]).toString());
                            max6.setText(map.get(Util.max[5]).toString());
                            max7.setText(map.get(Util.max[6]).toString());

                            comf1.setText(map.get("comf1").toString());
                            comf2.setText(map.get("comf2").toString());
                            cw1.setText(map.get("cw1").toString());
                            cw2.setText(map.get("cw2").toString());
                            drsg1.setText(map.get("drsg1").toString());
                            drsg2.setText(map.get("drsg2").toString());
                            flu1.setText(map.get("flu1").toString());
                            flu2.setText(map.get("flu2").toString());
                            sport1.setText(map.get("sport1").toString());
                            sport2.setText(map.get("sport2").toString());


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.but);
        editText = (EditText) findViewById(R.id.content);
        city = (TextView) findViewById(R.id.city);
        weather = (TextView) findViewById(R.id.weather);
        temp = (TextView) findViewById(R.id.temp);
        dir = (TextView) findViewById(R.id.dir);
        sc = (TextView) findViewById(R.id.sc);
        aqi = (TextView) findViewById(R.id.aqi);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);
        txt7 = (TextView) findViewById(R.id.txt7);

        max1 = (TextView) findViewById(R.id.max1);
        max2 = (TextView) findViewById(R.id.max2);
        max3 = (TextView) findViewById(R.id.max3);
        max4 = (TextView) findViewById(R.id.max4);
        max5 = (TextView) findViewById(R.id.max5);
        max6 = (TextView) findViewById(R.id.max6);
        max7 = (TextView) findViewById(R.id.max7);

        min1 = (TextView) findViewById(R.id.min1);
        min2 = (TextView) findViewById(R.id.min2);
        min3 = (TextView) findViewById(R.id.min3);
        min4 = (TextView) findViewById(R.id.min4);
        min5 = (TextView) findViewById(R.id.min5);
        min6 = (TextView) findViewById(R.id.min6);
        min7 = (TextView) findViewById(R.id.min7);

        comf1 = (TextView) findViewById(R.id.comf1);
        comf2 = (TextView) findViewById(R.id.comf2);

        cw1 = (TextView) findViewById(R.id.cw1);
        cw2 = (TextView) findViewById(R.id.cw2);

        drsg1 = (TextView) findViewById(R.id.drsg1);
        drsg2 = (TextView) findViewById(R.id.drsg2);

        flu1 = (TextView) findViewById(R.id.flu1);
        flu2 = (TextView) findViewById(R.id.flu2);

        sport1 = (TextView) findViewById(R.id.sport1);
        sport2 = (TextView) findViewById(R.id.sport2);




        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new Thread(new NewThread()).start();
            }
        });
    }
    private class NewThread implements Runnable {
        public void run() {
            String address = "http://apis.baidu.com/heweather/weather/free?city="
                    + editText.getText().toString();
            Dowm httpDownloader = new Dowm();
            String jonString = httpDownloader.download1(address);
//            Log.i("info", "jonString= "+jonString);
            Message msg = MainActivity.this.handler
                    .obtainMessage(MainActivity.SET, jonString);
            MainActivity.this.handler.sendMessage(msg);
        }
    }
}
