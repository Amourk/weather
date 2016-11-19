package com.example.weather;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阳瑞 on 2016/11/5.
 */
public class Util {
    public static String[] txt = {"txt_d1","txt_d2","txt_d3","txt_d4","txt_d5","txt_d6","txt_d7"};
    public static String[] max = {"max1","max2","max3","max4","max5","max6","max7"};
    public static String[] min = {"min1","min2","min3","min4","min5","min6","min7"};
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Map<String, Object>> getInformation(String jonString)
            throws Exception {
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        Log.i("info", "jonString= "+jonString);
        JSONObject jsonObject = new JSONObject(jonString);
        JSONArray js = jsonObject.getJSONArray("HeWeather data service 3.0");
        JSONObject objects = js.getJSONObject(0);


        String city = objects.getJSONObject("basic").getString("city");
        String weather = objects.getJSONObject("now").getJSONObject("cond").getString("txt");
        String temp = objects.getJSONObject("now").getString("tmp");
        String dir = objects.getJSONObject("now").getJSONObject("wind").getString("dir");
        String sc = objects.getJSONObject("now").getJSONObject("wind").getString("sc");
        String aqi = objects.getJSONObject("now").getString("fl");

        String comf1 = objects.getJSONObject("suggestion").getJSONObject("comf").getString("brf");
        String comf2 = objects.getJSONObject("suggestion").getJSONObject("comf").getString("txt");

        String cw1 = objects.getJSONObject("suggestion").getJSONObject("cw").getString("brf");
        String cw2 = objects.getJSONObject("suggestion").getJSONObject("cw").getString("txt");

        String drsg1 = objects.getJSONObject("suggestion").getJSONObject("drsg").getString("brf");
        String drsg2 = objects.getJSONObject("suggestion").getJSONObject("drsg").getString("txt");

        String flu1 = objects.getJSONObject("suggestion").getJSONObject("flu").getString("brf");
        String flu2 = objects.getJSONObject("suggestion").getJSONObject("flu").getString("txt");

        String sport1 = objects.getJSONObject("suggestion").getJSONObject("sport").getString("brf");;
        String sport2 = objects.getJSONObject("suggestion").getJSONObject("sport").getString("txt");



//        Log.i("info", "name =  "+objects.getJSONObject("basic").getString("city") );
//        Log.i("info","txt = "+ objects.getJSONObject("now").getJSONObject("cond").getString("txt"));
//        Log.i("info", "tmp= "+objects.getJSONObject("now").getString("tmp"));
//        Log.i("info", "comf ="+objects.getJSONObject("suggestion").getJSONObject("comf").getString("txt"));
        JSONArray daily_forecast = objects.getJSONArray("daily_forecast");
        for (int i = 0;i<daily_forecast.length();i++)
        {
            JSONObject tq = daily_forecast.getJSONObject(i);
            map.put(txt[i],tq.getJSONObject("cond").getString("txt_d"));
            map.put(max[i],tq.getJSONObject("tmp").getString("max"));
            map.put(min[i],tq.getJSONObject("tmp").getString("min"));

        }

        map.put("comf1",comf1);
        map.put("comf2",comf2);
        map.put("cw1",cw1);
        map.put("cw2",cw2);
        map.put("drsg1",drsg1);
        map.put("drsg2",drsg2);
        map.put("flu1",flu1);
        map.put("flu2",flu2);
        map.put("sport1",sport1);
        map.put("sport2",sport2);


        map.put("cityName", city);
        map.put("weather", weather);
        map.put("temp", temp);
        map.put("dir", dir);
        map.put("sc", sc);
        map.put("aqi",aqi);
        all.add(map);

        return all;

    }
}
