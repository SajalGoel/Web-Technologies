package com.example.hp.hw9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.hw9.adapter.AutoSuggestAdapter;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {
    double lat, lon, temp, humid, wspeed, vis, press, cloud, ozone, minTemp1, minTemp2, minTemp3, minTemp4, minTemp5, minTemp6, minTemp7, minTemp8,
    maxTemp1, maxTemp2, maxTemp3, maxTemp4,maxTemp5, maxTemp6, maxTemp7, maxTemp8, precipitation;
    int t, h, mint1, mint2, mint3, mint4, mint5, mint6, mint7, mint8,
    maxt1, maxt2, maxt3, maxt4, maxt5, maxt6, maxt7, maxt8;
    long date1, date2, date3, date4, date5, date6, date7, date8;
    String city, state, icon_big, summary, card_city, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, dicon, dsumm;
    String d=""; String d2=""; String d3=""; String d4=""; String d5=""; String d6=""; String d7=""; String d8="";
    private TextView desc;
    private AutoCompleteTextView auto;
    private ImageView search;
    private ImageView backView;
    private TextView searchLabelView;
    private ImageView myImgView;
    private TextView tempView;
    private TextView summaryView;
    private TextView cityView;
    private TextView humidView;
    private TextView windView;
    private TextView visView;
    private TextView pressView;
    private TextView dateView1;
    private TextView dateView2;
    private TextView dateView3;
    private TextView dateView4;
    private TextView dateView5;
    private TextView dateView6;
    private TextView dateView7;
    private TextView dateView8;
    private ImageView iconView1;
    private ImageView iconView2;
    private ImageView iconView3;
    private ImageView iconView4;
    private ImageView iconView5;
    private ImageView iconView6;
    private ImageView iconView7;
    private ImageView iconView8;
    private TextView minTempView1;
    private TextView minTempView2;
    private TextView minTempView3;
    private TextView minTempView4;
    private TextView minTempView5;
    private TextView minTempView6;
    private TextView minTempView7;
    private TextView minTempView8;
    private TextView maxTempView1;
    private TextView maxTempView2;
    private TextView maxTempView3;
    private TextView maxTempView4;
    private TextView maxTempView5;
    private TextView maxTempView6;
    private TextView maxTempView7;
    private TextView maxTempView8;
    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        desc = (TextView)findViewById(R.id.description);
        auto = (AutoCompleteTextView)findViewById(R.id.autocomplete);
        search = (ImageView)findViewById(R.id.search_icon);
        backView = (ImageView)findViewById(R.id.back_button);
        searchLabelView = (TextView)findViewById(R.id.search_label);
        myImgView = (ImageView)findViewById(R.id.weather_icon_big);
        tempView = (TextView) findViewById(R.id.card1_temp);
        summaryView = (TextView) findViewById(R.id.card1_summary);
        cityView = (TextView) findViewById(R.id.card1_city);
        humidView = (TextView)findViewById(R.id.card2_humid_val);
        windView = (TextView)findViewById(R.id.card2_wind_val);
        visView = (TextView)findViewById(R.id.card2_vis_val);
        pressView = (TextView)findViewById(R.id.card2_press_val);
        dateView1 = (TextView)findViewById(R.id.card3_date1);
        dateView2 = (TextView)findViewById(R.id.card3_date2);
        dateView3 = (TextView)findViewById(R.id.card3_date3);
        dateView4 = (TextView)findViewById(R.id.card3_date4);
        dateView5 = (TextView)findViewById(R.id.card3_date5);
        dateView6 = (TextView)findViewById(R.id.card3_date6);
        dateView7 = (TextView)findViewById(R.id.card3_date7);
        dateView8 = (TextView)findViewById(R.id.card3_date8);
        iconView1 = (ImageView)findViewById(R.id.card3_icon1);
        iconView2 = (ImageView)findViewById(R.id.card3_icon2);
        iconView3 = (ImageView)findViewById(R.id.card3_icon3);
        iconView4 = (ImageView)findViewById(R.id.card3_icon4);
        iconView5 = (ImageView)findViewById(R.id.card3_icon5);
        iconView6 = (ImageView)findViewById(R.id.card3_icon6);
        iconView7 = (ImageView)findViewById(R.id.card3_icon7);
        iconView8 = (ImageView)findViewById(R.id.card3_icon8);
        minTempView1 = (TextView)findViewById(R.id.card3_minTemp1);
        minTempView2 = (TextView)findViewById(R.id.card3_minTemp2);
        minTempView3 = (TextView)findViewById(R.id.card3_minTemp3);
        minTempView4 = (TextView)findViewById(R.id.card3_minTemp4);
        minTempView5 = (TextView)findViewById(R.id.card3_minTemp5);
        minTempView6 = (TextView)findViewById(R.id.card3_minTemp6);
        minTempView7 = (TextView)findViewById(R.id.card3_minTemp7);
        minTempView8 = (TextView)findViewById(R.id.card3_minTemp8);
        maxTempView1 = (TextView)findViewById(R.id.card3_maxTemp1);
        maxTempView2 = (TextView)findViewById(R.id.card3_maxTemp2);
        maxTempView3 = (TextView)findViewById(R.id.card3_maxTemp3);
        maxTempView4 = (TextView)findViewById(R.id.card3_maxTemp4);
        maxTempView5 = (TextView)findViewById(R.id.card3_maxTemp5);
        maxTempView6 = (TextView)findViewById(R.id.card3_maxTemp6);
        maxTempView7 = (TextView)findViewById(R.id.card3_maxTemp7);
        maxTempView8 = (TextView)findViewById(R.id.card3_maxTemp8);
        card1 = (CardView) findViewById(R.id.card_view);
        String URL ="http://ip-api.com/json";

        auto.setAdapter(new AutoSuggestAdapter(SplashScreen.this, android.R.layout.simple_list_item_1));

        desc.setVisibility(View.VISIBLE);
        auto.setVisibility(View.INVISIBLE);
        backView.setVisibility(View.INVISIBLE);
        searchLabelView.setVisibility(View.INVISIBLE);

        final RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                         Log.e("Rest Response", response.toString());

                         try {
                             JSONObject obj = new JSONObject(response.toString());
                             lat = obj.getDouble("lat");
                             lon = obj.getDouble("lon");
                             city = obj.getString("city");
                             state = obj.getString("regionName");
                             Log.e("Lat :",Double.toString(lat));
                             Log.e("Lon :",Double.toString(lon));
                             Log.e("City :",city);
                             Log.e("State :",state);


                         }catch (JSONException e){
                          e.printStackTrace();
                         }
                         String URL2 = "http://csci571-web-nodejs.us-east-2.elasticbeanstalk.com/api/forcast?latitude="+lat;
                         URL2 += "&longitude="+lon;
                         URL2 += "&city="+city;
                         URL2 += "&state="+state;
                        JsonArrayRequest objectRequest1 = new JsonArrayRequest(
                                Request.Method.GET,
                                URL2,
                                null,
                                new Response.Listener<JSONArray>(){
                                    @Override
                                    public void onResponse(JSONArray response1) {
                                        Log.e("Rest Response1", response1.toString());
                                        try{
                                                JSONArray arr1 = new JSONArray(response1.toString());
                                                JSONObject obj1 = arr1.getJSONObject(0);
                                                icon_big = obj1.getString("icon");
                                                temp = obj1.getDouble("temperature");
                                                 t = (int) Math.round(temp);
                                                 summary = obj1.getString("summary");
                                                 card_city = obj1.getString("city");
                                                 humid = obj1.getDouble("humidity");
                                                 humid = humid*100; h = (int) humid;
                                                 wspeed = obj1.getDouble("windspeed");
                                                 vis = obj1.getDouble("visibility");
                                                 press = obj1.getDouble("pressure");
                                                 cloud = obj1.getDouble("cloudCover");
                                                 ozone = obj1.getDouble("ozone");
                                                 precipitation = obj1.getDouble("precip");
                                                 dicon = obj1.getString("dailyIcon");
                                                 dsumm = obj1.getString("dailySummary");
                                                 date1 = obj1.getLong("time1");
                                                 date2 = obj1.getLong("time2");
                                            date3 = obj1.getLong("time3");
                                            date4 = obj1.getLong("time4");
                                            date5 = obj1.getLong("time5");
                                            date6 = obj1.getLong("time6");
                                            date7 = obj1.getLong("time7");
                                            date8 = obj1.getLong("time8");
                                            icon1 = obj1.getString("icon1");
                                            icon2 = obj1.getString("icon2");
                                            icon3 = obj1.getString("icon3");
                                            icon4 = obj1.getString("icon4");
                                            icon5 = obj1.getString("icon5");
                                            icon6 = obj1.getString("icon6");
                                            icon7 = obj1.getString("icon7");
                                            icon8 = obj1.getString("icon8");
                                            minTemp1 = obj1.getDouble("tempLow1");
                                            mint1 = (int) Math.round(minTemp1);
                                            minTemp2 = obj1.getDouble("tempLow2");
                                            mint2 = (int) Math.round(minTemp2);
                                            minTemp3 = obj1.getDouble("tempLow3");
                                            mint3 = (int) Math.round(minTemp3);
                                            minTemp4 = obj1.getDouble("tempLow4");
                                            mint4 = (int) Math.round(minTemp4);
                                            minTemp5 = obj1.getDouble("tempLow5");
                                            mint5 = (int) Math.round(minTemp5);
                                            minTemp6 = obj1.getDouble("tempLow6");
                                            mint6 = (int) Math.round(minTemp6);
                                            minTemp7 = obj1.getDouble("tempLow7");
                                            mint7 = (int) Math.round(minTemp7);
                                            minTemp8 = obj1.getDouble("tempLow8");
                                            mint8 = (int) Math.round(minTemp8);
                                            maxTemp1 = obj1.getDouble("tempHigh1");
                                            maxt1 = (int) Math.round(maxTemp1);
                                            maxTemp2 = obj1.getDouble("tempHigh2");
                                            maxt2 = (int) Math.round(maxTemp2);
                                            maxTemp3 = obj1.getDouble("tempHigh3");
                                            maxt3 = (int) Math.round(maxTemp3);
                                            maxTemp4 = obj1.getDouble("tempHigh4");
                                            maxt4 = (int) Math.round(maxTemp4);
                                            maxTemp5 = obj1.getDouble("tempHigh5");
                                            maxt5 = (int) Math.round(maxTemp5);
                                            maxTemp6 = obj1.getDouble("tempHigh6");
                                            maxt6 = (int) Math.round(maxTemp6);
                                            maxTemp7 = obj1.getDouble("tempHigh7");
                                            maxt7 = (int) Math.round(maxTemp7);
                                            maxTemp8 = obj1.getDouble("tempHigh8");
                                            maxt8 = (int) Math.round(maxTemp8);
                                            Calendar cal = Calendar.getInstance();
                                            cal.setTimeInMillis(date1*1000);
                                            String m1 = Integer.toString(cal.get(Calendar.MONTH)+1);
                                            String dt1 = Integer.toString(cal.get(Calendar.DATE));
                                            String y1 = Integer.toString(cal.get(Calendar.YEAR));
                                            d += m1 +'/'+ dt1 +'/'+ y1;

                                            Calendar cal2 = Calendar.getInstance();
                                            cal2.setTimeInMillis(date2*1000);
                                            String m2 = Integer.toString(cal2.get(Calendar.MONTH)+1);
                                            String dt2 = Integer.toString(cal2.get(Calendar.DATE));
                                            String y2 = Integer.toString(cal2.get(Calendar.YEAR));
                                            d2 += m2 +'/'+ dt2 +'/'+ y2;

                                            Calendar cal3 = Calendar.getInstance();
                                            cal3.setTimeInMillis(date3*1000);
                                            String m3 = Integer.toString(cal3.get(Calendar.MONTH)+1);
                                            String dt3 = Integer.toString(cal3.get(Calendar.DATE));
                                            String y3 = Integer.toString(cal3.get(Calendar.YEAR));
                                            d3 += m3 +'/'+ dt3 +'/'+ y3;

                                            Calendar cal4 = Calendar.getInstance();
                                            cal4.setTimeInMillis(date4*1000);
                                            String m4 = Integer.toString(cal4.get(Calendar.MONTH)+1);
                                            String dt4 = Integer.toString(cal4.get(Calendar.DATE));
                                            String y4 = Integer.toString(cal4.get(Calendar.YEAR));
                                            d4 += m4 +'/'+ dt4 +'/'+ y4;

                                            Calendar cal5 = Calendar.getInstance();
                                            cal5.setTimeInMillis(date5*1000);
                                            String m5 = Integer.toString(cal5.get(Calendar.MONTH)+1);
                                            String dt5 = Integer.toString(cal5.get(Calendar.DATE));
                                            String y5 = Integer.toString(cal5.get(Calendar.YEAR));
                                            d5 += m5 +'/'+ dt5 +'/'+ y5;

                                            Calendar cal6 = Calendar.getInstance();
                                            cal6.setTimeInMillis(date6*1000);
                                            String m6 = Integer.toString(cal6.get(Calendar.MONTH)+1);
                                            String dt6 = Integer.toString(cal6.get(Calendar.DATE));
                                            String y6 = Integer.toString(cal6.get(Calendar.YEAR));
                                            d6 += m6 +'/'+ dt6 +'/'+ y6;

                                            Calendar cal7 = Calendar.getInstance();
                                            cal7.setTimeInMillis(date7*1000);
                                            String m7 = Integer.toString(cal7.get(Calendar.MONTH)+1);
                                            String dt7 = Integer.toString(cal7.get(Calendar.DATE));
                                            String y7 = Integer.toString(cal7.get(Calendar.YEAR));
                                            d7 += m7 +'/'+ dt7 +'/'+ y7;

                                            Calendar cal8 = Calendar.getInstance();
                                            cal8.setTimeInMillis(date8*1000);
                                            String m8 = Integer.toString(cal8.get(Calendar.MONTH)+1);
                                            String dt8 = Integer.toString(cal8.get(Calendar.DATE));
                                            String y8 = Integer.toString(cal8.get(Calendar.YEAR));
                                            d8 += m8 +'/'+ dt8 +'/'+ y8;

                                            Log.e("Date :",d);


                                        }catch (JSONException e){
                                            e.printStackTrace();
                                        }
                                        if(icon_big.equals("clear-night")){
                                            myImgView.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon_big.equals("clear-day")){
                                            myImgView.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon_big.equals("rain")){
                                            myImgView.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon_big.equals("sleet")){
                                            myImgView.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon_big.equals("snow")){
                                            myImgView.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon_big.equals("wind")){
                                            myImgView.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon_big.equals("fog")){
                                            myImgView.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon_big.equals("cloudy")){
                                            myImgView.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon_big.equals("partly-cloudy-night")){
                                            myImgView.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon_big.equals("partly-cloudy-day")){
                                            myImgView.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }
                                        tempView.setText("" + t);
                                        summaryView.setText(summary);
                                        cityView.setText(card_city);
                                        humidView.setText(""+h);
                                        windView.setText(""+wspeed);
                                        visView.setText(""+vis);
                                        pressView.setText(""+press);
                                        minTempView1.setText(""+mint1);
                                        minTempView2.setText(""+mint2);
                                        minTempView3.setText(""+mint3);
                                        minTempView4.setText(""+mint4);
                                        minTempView5.setText(""+mint5);
                                        minTempView6.setText(""+mint6);
                                        minTempView7.setText(""+mint7);
                                        minTempView8.setText(""+mint8);
                                        maxTempView1.setText(""+maxt1);
                                        maxTempView2.setText(""+maxt2);
                                        maxTempView3.setText(""+maxt3);
                                        maxTempView4.setText(""+maxt4);
                                        maxTempView5.setText(""+maxt5);
                                        maxTempView6.setText(""+maxt6);
                                        maxTempView7.setText(""+maxt7);
                                        maxTempView8.setText(""+maxt8);
                                        dateView1.setText(d);
                                        dateView2.setText(d2);
                                        dateView3.setText(d3);
                                        dateView4.setText(d4);
                                        dateView5.setText(d5);
                                        dateView6.setText(d6);
                                        dateView7.setText(d7);
                                        dateView8.setText(d8);
                                        if(icon1.equals("clear-night")){
                                            iconView1.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon1.equals("clear-day")){
                                            iconView1.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon1.equals("rain")){
                                            iconView1.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon1.equals("sleet")){
                                            iconView1.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon1.equals("snow")){
                                            iconView1.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon1.equals("wind")){
                                            iconView1.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon1.equals("fog")){
                                            iconView1.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon1.equals("cloudy")){
                                            iconView1.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon1.equals("partly-cloudy-night")){
                                            iconView1.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon1.equals("partly-cloudy-day")){
                                            iconView1.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon2.equals("clear-night")){
                                            iconView2.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon2.equals("clear-day")){
                                            iconView2.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon2.equals("rain")){
                                            iconView2.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon2.equals("sleet")){
                                            iconView2.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon2.equals("snow")){
                                            iconView2.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon2.equals("wind")){
                                            iconView2.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon2.equals("fog")){
                                            iconView2.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon2.equals("cloudy")){
                                            iconView2.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon2.equals("partly-cloudy-night")){
                                            iconView2.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon2.equals("partly-cloudy-day")){
                                            iconView2.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon3.equals("clear-night")){
                                            iconView3.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon3.equals("clear-day")){
                                            iconView3.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon3.equals("rain")){
                                            iconView3.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon3.equals("sleet")){
                                            iconView3.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon3.equals("snow")){
                                            iconView3.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon3.equals("wind")){
                                            iconView3.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon3.equals("fog")){
                                            iconView3.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon3.equals("cloudy")){
                                            iconView3.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon3.equals("partly-cloudy-night")){
                                            iconView3.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon3.equals("partly-cloudy-day")){
                                            iconView3.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon4.equals("clear-night")){
                                            iconView4.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon4.equals("clear-day")){
                                            iconView4.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon4.equals("rain")){
                                            iconView4.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon4.equals("sleet")){
                                            iconView4.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon4.equals("snow")){
                                            iconView4.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon4.equals("wind")){
                                            iconView4.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon4.equals("fog")){
                                            iconView4.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon4.equals("cloudy")){
                                            iconView4.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon4.equals("partly-cloudy-night")){
                                            iconView4.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon4.equals("partly-cloudy-day")){
                                            iconView4.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon5.equals("clear-night")){
                                            iconView5.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon5.equals("clear-day")){
                                            iconView5.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon5.equals("rain")){
                                            iconView5.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon5.equals("sleet")){
                                            iconView5.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon5.equals("snow")){
                                            iconView5.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon5.equals("wind")){
                                            iconView5.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon5.equals("fog")){
                                            iconView5.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon5.equals("cloudy")){
                                            iconView5.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon5.equals("partly-cloudy-night")){
                                            iconView5.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon5.equals("partly-cloudy-day")){
                                            iconView5.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon6.equals("clear-night")){
                                            iconView6.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon6.equals("clear-day")){
                                            iconView6.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon6.equals("rain")){
                                            iconView6.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon6.equals("sleet")){
                                            iconView6.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon6.equals("snow")){
                                            iconView6.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon6.equals("wind")){
                                            iconView6.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon6.equals("fog")){
                                            iconView6.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon6.equals("cloudy")){
                                            iconView6.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon6.equals("partly-cloudy-night")){
                                            iconView6.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon6.equals("partly-cloudy-day")){
                                            iconView6.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon7.equals("clear-night")){
                                            iconView7.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon7.equals("clear-day")){
                                            iconView7.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon7.equals("rain")){
                                            iconView7.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon7.equals("sleet")){
                                            iconView7.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon7.equals("snow")){
                                            iconView7.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon7.equals("wind")){
                                            iconView7.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon7.equals("fog")){
                                            iconView7.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon7.equals("cloudy")){
                                            iconView7.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon7.equals("partly-cloudy-night")){
                                            iconView7.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon7.equals("partly-cloudy-day")){
                                            iconView7.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }

                                        if(icon8.equals("clear-night")){
                                            iconView8.setImageResource(R.drawable.weather_night_big);
                                        }
                                        else if(icon8.equals("clear-day")){
                                            iconView8.setImageResource(R.drawable.weather_sunny_big);
                                        }
                                        else if(icon8.equals("rain")){
                                            iconView8.setImageResource(R.drawable.weather_rainy_big);
                                        }
                                        else if(icon8.equals("sleet")){
                                            iconView8.setImageResource(R.drawable.weather_snowy_rainy_big);
                                        }
                                        else if(icon8.equals("snow")){
                                            iconView8.setImageResource(R.drawable.weather_snowy_big);
                                        }
                                        else if(icon8.equals("wind")){
                                            iconView8.setImageResource(R.drawable.weather_windy_variant_big);
                                        }
                                        else if(icon8.equals("fog")){
                                            iconView8.setImageResource(R.drawable.weather_fog_big);
                                        }
                                        else if(icon8.equals("cloudy")){
                                            iconView8.setImageResource(R.drawable.weather_cloudy_big);
                                        }
                                        else if(icon8.equals("partly-cloudy-night")){
                                            iconView8.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                        }
                                        else if(icon8.equals("partly-cloudy-day")){
                                            iconView8.setImageResource(R.drawable.weather_partly_cloudy_big);
                                        }



                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error1) {
                                        Log.e("Rest Response", error1.toString());
                                    }
                                }

                        );
                        requestQueue2.add(objectRequest1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                }

        );
        requestQueue.add(objectRequest);

        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = auto.getText().toString();
                searchLabelView.setVisibility(View.VISIBLE);
                String URL3 = "http://csci571-web-nodejs.us-east-2.elasticbeanstalk.com/api/getPosition?street=";
                URL3 += "&city="+s;
                URL3 += "&state=";
                JsonArrayRequest objectRequest3 = new JsonArrayRequest(
                        Request.Method.GET,
                        URL3,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("Rest Response2", response.toString());
                                try{
                                    JSONArray arr1 = new JSONArray(response.toString());
                                    JSONObject obj1 = arr1.getJSONObject(0);
                                   icon_big = obj1.getString("icon");
                                    temp = obj1.getDouble("temperature");
                                    t = (int) Math.round(temp);
                                    summary = obj1.getString("summary");
                                    card_city = obj1.getString("city");
                                    humid = obj1.getDouble("humidity");
                                    humid = humid*100; h = (int) humid;
                                    wspeed = obj1.getDouble("windspeed");
                                    vis = obj1.getDouble("visibility");
                                    press = obj1.getDouble("pressure");
                                    cloud = obj1.getDouble("cloudCover");
                                    ozone = obj1.getDouble("ozone");
                                    precipitation = obj1.getDouble("precip");
                                    dicon = obj1.getString("dailyIcon");
                                    dsumm = obj1.getString("dailySummary");
                                    date1 = obj1.getLong("time1");
                                    date2 = obj1.getLong("time2");
                                    date3 = obj1.getLong("time3");
                                    date4 = obj1.getLong("time4");
                                    date5 = obj1.getLong("time5");
                                    date6 = obj1.getLong("time6");
                                    date7 = obj1.getLong("time7");
                                    date8 = obj1.getLong("time8");
                                    icon1 = obj1.getString("icon1");
                                    icon2 = obj1.getString("icon2");
                                    icon3 = obj1.getString("icon3");
                                    icon4 = obj1.getString("icon4");
                                    icon5 = obj1.getString("icon5");
                                    icon6 = obj1.getString("icon6");
                                    icon7 = obj1.getString("icon7");
                                    icon8 = obj1.getString("icon8");
                                    minTemp1 = obj1.getDouble("tempLow1");
                                    mint1 = (int) Math.round(minTemp1);
                                    minTemp2 = obj1.getDouble("tempLow2");
                                    mint2 = (int) Math.round(minTemp2);
                                    minTemp3 = obj1.getDouble("tempLow3");
                                    mint3 = (int) Math.round(minTemp3);
                                    minTemp4 = obj1.getDouble("tempLow4");
                                    mint4 = (int) Math.round(minTemp4);
                                    minTemp5 = obj1.getDouble("tempLow5");
                                    mint5 = (int) Math.round(minTemp5);
                                    minTemp6 = obj1.getDouble("tempLow6");
                                    mint6 = (int) Math.round(minTemp6);
                                    minTemp7 = obj1.getDouble("tempLow7");
                                    mint7 = (int) Math.round(minTemp7);
                                    minTemp8 = obj1.getDouble("tempLow8");
                                    mint8 = (int) Math.round(minTemp8);
                                    maxTemp1 = obj1.getDouble("tempHigh1");
                                    maxt1 = (int) Math.round(maxTemp1);
                                    maxTemp2 = obj1.getDouble("tempHigh2");
                                    maxt2 = (int) Math.round(maxTemp2);
                                    maxTemp3 = obj1.getDouble("tempHigh3");
                                    maxt3 = (int) Math.round(maxTemp3);
                                    maxTemp4 = obj1.getDouble("tempHigh4");
                                    maxt4 = (int) Math.round(maxTemp4);
                                    maxTemp5 = obj1.getDouble("tempHigh5");
                                    maxt5 = (int) Math.round(maxTemp5);
                                    maxTemp6 = obj1.getDouble("tempHigh6");
                                    maxt6 = (int) Math.round(maxTemp6);
                                    maxTemp7 = obj1.getDouble("tempHigh7");
                                    maxt7 = (int) Math.round(maxTemp7);
                                    maxTemp8 = obj1.getDouble("tempHigh8");
                                    maxt8 = (int) Math.round(maxTemp8);
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(date1*1000);
                                    String m1 = Integer.toString(cal.get(Calendar.MONTH)+1);
                                    String dt1 = Integer.toString(cal.get(Calendar.DATE));
                                    String y1 = Integer.toString(cal.get(Calendar.YEAR));
                                    d = m1 +'/'+ dt1 +'/'+ y1;

                                    Calendar cal2 = Calendar.getInstance();
                                    cal2.setTimeInMillis(date2*1000);
                                    String m2 = Integer.toString(cal2.get(Calendar.MONTH)+1);
                                    String dt2 = Integer.toString(cal2.get(Calendar.DATE));
                                    String y2 = Integer.toString(cal2.get(Calendar.YEAR));
                                    d2 = m2 +'/'+ dt2 +'/'+ y2;

                                    Calendar cal3 = Calendar.getInstance();
                                    cal3.setTimeInMillis(date3*1000);
                                    String m3 = Integer.toString(cal3.get(Calendar.MONTH)+1);
                                    String dt3 = Integer.toString(cal3.get(Calendar.DATE));
                                    String y3 = Integer.toString(cal3.get(Calendar.YEAR));
                                    d3 = m3 +'/'+ dt3 +'/'+ y3;

                                    Calendar cal4 = Calendar.getInstance();
                                    cal4.setTimeInMillis(date4*1000);
                                    String m4 = Integer.toString(cal4.get(Calendar.MONTH)+1);
                                    String dt4 = Integer.toString(cal4.get(Calendar.DATE));
                                    String y4 = Integer.toString(cal4.get(Calendar.YEAR));
                                    d4 = m4 +'/'+ dt4 +'/'+ y4;

                                    Calendar cal5 = Calendar.getInstance();
                                    cal5.setTimeInMillis(date5*1000);
                                    String m5 = Integer.toString(cal5.get(Calendar.MONTH)+1);
                                    String dt5 = Integer.toString(cal5.get(Calendar.DATE));
                                    String y5 = Integer.toString(cal5.get(Calendar.YEAR));
                                    d5 = m5 +'/'+ dt5 +'/'+ y5;

                                    Calendar cal6 = Calendar.getInstance();
                                    cal6.setTimeInMillis(date6*1000);
                                    String m6 = Integer.toString(cal6.get(Calendar.MONTH)+1);
                                    String dt6 = Integer.toString(cal6.get(Calendar.DATE));
                                    String y6 = Integer.toString(cal6.get(Calendar.YEAR));
                                    d6 = m6 +'/'+ dt6 +'/'+ y6;

                                    Calendar cal7 = Calendar.getInstance();
                                    cal7.setTimeInMillis(date7*1000);
                                    String m7 = Integer.toString(cal7.get(Calendar.MONTH)+1);
                                    String dt7 = Integer.toString(cal7.get(Calendar.DATE));
                                    String y7 = Integer.toString(cal7.get(Calendar.YEAR));
                                    d7 = m7 +'/'+ dt7 +'/'+ y7;

                                    Calendar cal8 = Calendar.getInstance();
                                    cal8.setTimeInMillis(date8*1000);
                                    String m8 = Integer.toString(cal8.get(Calendar.MONTH)+1);
                                    String dt8 = Integer.toString(cal8.get(Calendar.DATE));
                                    String y8 = Integer.toString(cal8.get(Calendar.YEAR));
                                    d8 = m8 +'/'+ dt8 +'/'+ y8;

                                }
                                catch(JSONException e){
                                    e.printStackTrace();
                                }
                                if(icon_big.equals("clear-night")){
                                    myImgView.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon_big.equals("clear-day")){
                                    myImgView.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon_big.equals("rain")){
                                    myImgView.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon_big.equals("sleet")){
                                    myImgView.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon_big.equals("snow")){
                                    myImgView.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon_big.equals("wind")){
                                    myImgView.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon_big.equals("fog")){
                                    myImgView.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon_big.equals("cloudy")){
                                    myImgView.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon_big.equals("partly-cloudy-night")){
                                    myImgView.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon_big.equals("partly-cloudy-day")){
                                    myImgView.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }
                                tempView.setText("" + t);
                                summaryView.setText(summary);
                                cityView.setText(card_city);
                                humidView.setText(""+h);
                                windView.setText(""+wspeed);
                                visView.setText(""+vis);
                                pressView.setText(""+press);
                                minTempView1.setText(""+mint1);
                                minTempView2.setText(""+mint2);
                                minTempView3.setText(""+mint3);
                                minTempView4.setText(""+mint4);
                                minTempView5.setText(""+mint5);
                                minTempView6.setText(""+mint6);
                                minTempView7.setText(""+mint7);
                                minTempView8.setText(""+mint8);
                                maxTempView1.setText(""+maxt1);
                                maxTempView2.setText(""+maxt2);
                                maxTempView3.setText(""+maxt3);
                                maxTempView4.setText(""+maxt4);
                                maxTempView5.setText(""+maxt5);
                                maxTempView6.setText(""+maxt6);
                                maxTempView7.setText(""+maxt7);
                                maxTempView8.setText(""+maxt8);
                                dateView1.setText(d);
                                dateView2.setText(d2);
                                dateView3.setText(d3);
                                dateView4.setText(d4);
                                dateView5.setText(d5);
                                dateView6.setText(d6);
                                dateView7.setText(d7);
                                dateView8.setText(d8);
                                if(icon1.equals("clear-night")){
                                    iconView1.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon1.equals("clear-day")){
                                    iconView1.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon1.equals("rain")){
                                    iconView1.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon1.equals("sleet")){
                                    iconView1.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon1.equals("snow")){
                                    iconView1.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon1.equals("wind")){
                                    iconView1.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon1.equals("fog")){
                                    iconView1.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon1.equals("cloudy")){
                                    iconView1.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon1.equals("partly-cloudy-night")){
                                    iconView1.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon1.equals("partly-cloudy-day")){
                                    iconView1.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon2.equals("clear-night")){
                                    iconView2.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon2.equals("clear-day")){
                                    iconView2.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon2.equals("rain")){
                                    iconView2.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon2.equals("sleet")){
                                    iconView2.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon2.equals("snow")){
                                    iconView2.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon2.equals("wind")){
                                    iconView2.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon2.equals("fog")){
                                    iconView2.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon2.equals("cloudy")){
                                    iconView2.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon2.equals("partly-cloudy-night")){
                                    iconView2.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon2.equals("partly-cloudy-day")){
                                    iconView2.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon3.equals("clear-night")){
                                    iconView3.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon3.equals("clear-day")){
                                    iconView3.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon3.equals("rain")){
                                    iconView3.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon3.equals("sleet")){
                                    iconView3.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon3.equals("snow")){
                                    iconView3.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon3.equals("wind")){
                                    iconView3.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon3.equals("fog")){
                                    iconView3.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon3.equals("cloudy")){
                                    iconView3.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon3.equals("partly-cloudy-night")){
                                    iconView3.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon3.equals("partly-cloudy-day")){
                                    iconView3.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon4.equals("clear-night")){
                                    iconView4.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon4.equals("clear-day")){
                                    iconView4.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon4.equals("rain")){
                                    iconView4.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon4.equals("sleet")){
                                    iconView4.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon4.equals("snow")){
                                    iconView4.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon4.equals("wind")){
                                    iconView4.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon4.equals("fog")){
                                    iconView4.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon4.equals("cloudy")){
                                    iconView4.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon4.equals("partly-cloudy-night")){
                                    iconView4.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon4.equals("partly-cloudy-day")){
                                    iconView4.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon5.equals("clear-night")){
                                    iconView5.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon5.equals("clear-day")){
                                    iconView5.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon5.equals("rain")){
                                    iconView5.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon5.equals("sleet")){
                                    iconView5.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon5.equals("snow")){
                                    iconView5.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon5.equals("wind")){
                                    iconView5.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon5.equals("fog")){
                                    iconView5.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon5.equals("cloudy")){
                                    iconView5.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon5.equals("partly-cloudy-night")){
                                    iconView5.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon5.equals("partly-cloudy-day")){
                                    iconView5.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon6.equals("clear-night")){
                                    iconView6.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon6.equals("clear-day")){
                                    iconView6.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon6.equals("rain")){
                                    iconView6.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon6.equals("sleet")){
                                    iconView6.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon6.equals("snow")){
                                    iconView6.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon6.equals("wind")){
                                    iconView6.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon6.equals("fog")){
                                    iconView6.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon6.equals("cloudy")){
                                    iconView6.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon6.equals("partly-cloudy-night")){
                                    iconView6.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon6.equals("partly-cloudy-day")){
                                    iconView6.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon7.equals("clear-night")){
                                    iconView7.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon7.equals("clear-day")){
                                    iconView7.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon7.equals("rain")){
                                    iconView7.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon7.equals("sleet")){
                                    iconView7.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon7.equals("snow")){
                                    iconView7.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon7.equals("wind")){
                                    iconView7.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon7.equals("fog")){
                                    iconView7.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon7.equals("cloudy")){
                                    iconView7.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon7.equals("partly-cloudy-night")){
                                    iconView7.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon7.equals("partly-cloudy-day")){
                                    iconView7.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                                if(icon8.equals("clear-night")){
                                    iconView8.setImageResource(R.drawable.weather_night_big);
                                }
                                else if(icon8.equals("clear-day")){
                                    iconView8.setImageResource(R.drawable.weather_sunny_big);
                                }
                                else if(icon8.equals("rain")){
                                    iconView8.setImageResource(R.drawable.weather_rainy_big);
                                }
                                else if(icon8.equals("sleet")){
                                    iconView8.setImageResource(R.drawable.weather_snowy_rainy_big);
                                }
                                else if(icon8.equals("snow")){
                                    iconView8.setImageResource(R.drawable.weather_snowy_big);
                                }
                                else if(icon8.equals("wind")){
                                    iconView8.setImageResource(R.drawable.weather_windy_variant_big);
                                }
                                else if(icon8.equals("fog")){
                                    iconView8.setImageResource(R.drawable.weather_fog_big);
                                }
                                else if(icon8.equals("cloudy")){
                                    iconView8.setImageResource(R.drawable.weather_cloudy_big);
                                }
                                else if(icon8.equals("partly-cloudy-night")){
                                    iconView8.setImageResource(R.drawable.weather_night_partly_cloudy_big);
                                }
                                else if(icon8.equals("partly-cloudy-day")){
                                    iconView8.setImageResource(R.drawable.weather_partly_cloudy_big);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Response2", error.toString());
                            }
                        }
                );
                 requestQueue3.add(objectRequest3);

                Log.e("AutoComplete: ",s);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SplashScreen.this, DetailActivity.class);
               intent.putExtra("desc", card_city);
               intent.putExtra("wind", wspeed);
               intent.putExtra("temp", t);
               intent.putExtra("press", press);
               intent.putExtra("hum", h);
               intent.putExtra("visi", vis);
               intent.putExtra("cloud", cloud);
               intent.putExtra("ozone", ozone);
               intent.putExtra("icon", icon_big);
               intent.putExtra("precip", precipitation);
               intent.putExtra("di", dicon);
               intent.putExtra("ds", dsumm);

               startActivity(intent);
            }
        });

    }

    public void searchLocation(View view){
        desc.setVisibility(view.INVISIBLE);
        auto.setVisibility(view.VISIBLE);
        backView.setVisibility(view.VISIBLE);
    }
    public void goBack(View view){
        Intent intent = new Intent(SplashScreen.this, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
