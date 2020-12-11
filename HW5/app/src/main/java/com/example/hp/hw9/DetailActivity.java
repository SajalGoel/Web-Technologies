package com.example.hp.hw9;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    String d, icon, dailyIcon, dailySumm, url1, url2, url3, url4, url5, url6, url7, url8;
    int temperature, humidity;
    double w, p, v, c, o, pre;

    private ProgressBar spinner;
    private TextView desc;
    private TextView wind;
    private TextView press;
    private TextView rain;
    private TextView temp;
    private ImageView iconView;
    private TextView summ;
    private TextView humid;
    private TextView vis;
    private TextView cloud;
    private TextView ozone;
    private CardView card1;
    private CardView card2;
    private CardView card3;
    private CardView card4;
    private CardView card5;
    private CardView card6;
    private CardView card7;
    private CardView card8;
    private CardView card9;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private CardView photosView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        desc = (TextView)findViewById(R.id.detail_desc);
        wind = (TextView)findViewById(R.id.card1_text);
        press = (TextView)findViewById(R.id.card2_text);
        rain= (TextView)findViewById(R.id.card3_text);
        temp = (TextView)findViewById(R.id.card4_text);
        iconView = (ImageView)findViewById(R.id.card5_icon);
        summ = (TextView)findViewById(R.id.card5_text);
        humid = (TextView)findViewById(R.id.card6_text);
        vis = (TextView)findViewById(R.id.card7_text);
        cloud = (TextView)findViewById(R.id.card8_text);
        ozone = (TextView)findViewById(R.id.card9_text);
        card1 = (CardView)findViewById(R.id.card_view_1);
        card2 = (CardView)findViewById(R.id.card_view_2);
        card3 = (CardView)findViewById(R.id.card_view_3);
        card4 = (CardView)findViewById(R.id.card_view_4);
        card5 = (CardView)findViewById(R.id.card_view_5);
        card6 = (CardView)findViewById(R.id.card_view_6);
        card7 = (CardView)findViewById(R.id.card_view_7);
        card8 = (CardView)findViewById(R.id.card_view_8);
        card9 = (CardView)findViewById(R.id.card_view_9);
        img1 = (ImageView)findViewById(R.id.image1);
        img2 = (ImageView)findViewById(R.id.image2);
        img3 = (ImageView)findViewById(R.id.image3);
        img4 = (ImageView)findViewById(R.id.image4);
        img5 = (ImageView)findViewById(R.id.image5);
        img6 = (ImageView)findViewById(R.id.image6);
        img7 = (ImageView)findViewById(R.id.image7);
        img8 = (ImageView)findViewById(R.id.image8);
        photosView = (CardView)findViewById(R.id.photo_card);

       // spinner.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
         d = intent.getStringExtra("desc");
         w = intent.getDoubleExtra("wind", 0);
         temperature = intent.getIntExtra("temp", 0);
         p = intent.getDoubleExtra("press", 0);
         humidity = intent.getIntExtra("hum", 0);
         v = intent.getDoubleExtra("visi", 0);
         c = intent.getDoubleExtra("cloud", 0);
         o = intent.getDoubleExtra("ozone", 0);
         icon = intent.getStringExtra("icon");
         pre = intent.getDoubleExtra("precip", 0);
         dailyIcon = intent.getStringExtra("di");
         dailySumm = intent.getStringExtra("ds");

        // spinner.setVisibility(View.GONE);
         //todayDetails(null);
        desc.setText(d);
        if(icon.equals("clear-night")){
            iconView.setImageResource(R.drawable.weather_night_big);
            summ.setText("clear night");
        }
        else if(icon.equals("clear-day")){
            iconView.setImageResource(R.drawable.weather_sunny_big);
            summ.setText("  clear day");
        }
        else if(icon.equals("rain")){
            iconView.setImageResource(R.drawable.weather_rainy_big);
            summ.setText("     rain");
        }
        else if(icon.equals("sleet")){
            iconView.setImageResource(R.drawable.weather_snowy_rainy_big);
            summ.setText("    sleet");
        }
        else if(icon.equals("snow")){
            iconView.setImageResource(R.drawable.weather_snowy_big);
            summ.setText("    snow");
        }
        else if(icon.equals("wind")){
            iconView.setImageResource(R.drawable.weather_windy_variant_big);
            summ.setText("    wind");
        }
        else if(icon.equals("fog")){
            iconView.setImageResource(R.drawable.weather_fog_big);
            summ.setText("         fog");
        }
        else if(icon.equals("cloudy")){
            iconView.setImageResource(R.drawable.weather_cloudy_big);
            summ.setText("     cloudy");
        }
        else if(icon.equals("partly-cloudy-night")){
            iconView.setImageResource(R.drawable.weather_night_partly_cloudy_big);
            summ.setText("cloudy night");
        }
        else if(icon.equals("partly-cloudy-day")){
            iconView.setImageResource(R.drawable.weather_partly_cloudy_big);
            summ.setText("cloudy day");
        }
        desc.setText(d);
        wind.setText(""+w);
        temp.setText(""+temperature);
        press.setText(""+p);
        humid.setText(""+humidity);
        vis.setText(""+v);
        cloud.setText(""+c);
        ozone.setText(""+o);
        rain.setText(""+pre);
    }
    public void todayDetails(View view){
        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);
        card4.setVisibility(View.VISIBLE);
        card5.setVisibility(View.VISIBLE);
        card6.setVisibility(View.VISIBLE);
        card7.setVisibility(View.VISIBLE);
        card8.setVisibility(View.VISIBLE);
        card9.setVisibility(View.VISIBLE);
        photosView.setVisibility(View.INVISIBLE);

        if(icon.equals("clear-night")){
            iconView.setImageResource(R.drawable.weather_night_big);
            summ.setText("clear night");
        }
        else if(icon.equals("clear-day")){
            iconView.setImageResource(R.drawable.weather_sunny_big);
            summ.setText("  clear day");
        }
        else if(icon.equals("rain")){
            iconView.setImageResource(R.drawable.weather_rainy_big);
            summ.setText("     rain");
        }
        else if(icon.equals("sleet")){
            iconView.setImageResource(R.drawable.weather_snowy_rainy_big);
            summ.setText("    sleet");
        }
        else if(icon.equals("snow")){
            iconView.setImageResource(R.drawable.weather_snowy_big);
            summ.setText("    snow");
        }
        else if(icon.equals("wind")){
            iconView.setImageResource(R.drawable.weather_windy_variant_big);
            summ.setText("    wind");
        }
        else if(icon.equals("fog")){
            iconView.setImageResource(R.drawable.weather_fog_big);
            summ.setText("     fog");
        }
        else if(icon.equals("cloudy")){
            iconView.setImageResource(R.drawable.weather_cloudy_big);
            summ.setText("     cloudy");
        }
        else if(icon.equals("partly-cloudy-night")){
            iconView.setImageResource(R.drawable.weather_night_partly_cloudy_big);
            summ.setText("cloudy night");
        }
        else if(icon.equals("partly-cloudy-day")){
            iconView.setImageResource(R.drawable.weather_partly_cloudy_big);
            summ.setText("cloudy day");
        }
        desc.setText(d);
        wind.setText(""+w);
        temp.setText(""+temperature);
        press.setText(""+p);
        humid.setText(""+humidity);
        vis.setText(""+v);
        cloud.setText(""+c);
        ozone.setText(""+o);
        rain.setText(""+pre);

    }
    public void weeklyDetails(View view){
        card1.setVisibility(view.INVISIBLE);
        card2.setVisibility(view.INVISIBLE);
        card3.setVisibility(view.INVISIBLE);
        card4.setVisibility(view.INVISIBLE);
        card5.setVisibility(view.INVISIBLE);
        card6.setVisibility(view.INVISIBLE);
        card7.setVisibility(view.INVISIBLE);
        card8.setVisibility(view.INVISIBLE);
        card9.setVisibility(view.INVISIBLE);
        photosView.setVisibility(view.INVISIBLE);
    }
    public void photoDetails(View view){
        card1.setVisibility(view.INVISIBLE);
        card2.setVisibility(view.INVISIBLE);
        card3.setVisibility(view.INVISIBLE);
        card4.setVisibility(view.INVISIBLE);
        card5.setVisibility(view.INVISIBLE);
        card6.setVisibility(view.INVISIBLE);
        card7.setVisibility(view.INVISIBLE);
        card8.setVisibility(view.INVISIBLE);
        card9.setVisibility(view.INVISIBLE);
        photosView.setVisibility(view.VISIBLE);

        String URL = "https://www.googleapis.com/customsearch/v1?q="+d+"&cx=003410437470048956045:gvxfuame1cy&searchType=image&key=AIzaSyBYdmppskZfEB-Fmkeylim0NVFLh9o_TdQ";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue4 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue5 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue6 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue7 = Volley.newRequestQueue(this);
        final RequestQueue requestQueue8 = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        try{
                            JSONObject obj = new JSONObject(response.toString());
                            url1 = obj.getJSONArray("items").getJSONObject(0).getString("link");
                            url2 = obj.getJSONArray("items").getJSONObject(1).getString("link");
                            url3 = obj.getJSONArray("items").getJSONObject(2).getString("link");
                            url4 = obj.getJSONArray("items").getJSONObject(3).getString("link");
                            url5 = obj.getJSONArray("items").getJSONObject(4).getString("link");
                            url6 = obj.getJSONArray("items").getJSONObject(5).getString("link");
                            url7 = obj.getJSONArray("items").getJSONObject(6).getString("link");
                            url8 = obj.getJSONArray("items").getJSONObject(7).getString("link");
                            Log.e("URL Response", url1);

                            ImageRequest request = new ImageRequest(url1,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                        img1.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                        requestQueue1.add(request);

                            ImageRequest request2 = new ImageRequest(url2,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img2.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue2.add(request2);
                            ImageRequest request3 = new ImageRequest(url3,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img3.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue3.add(request3);
                            ImageRequest request4 = new ImageRequest(url4,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img4.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue4.add(request4);
                            ImageRequest request5 = new ImageRequest(url5,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img5.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue5.add(request5);
                            ImageRequest request6 = new ImageRequest(url6,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img6.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue6.add(request6);
                            ImageRequest request7 = new ImageRequest(url7,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img7.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue7.add(request7);
                            ImageRequest request8 = new ImageRequest(url8,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            img8.setImageBitmap(response);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Image Error Response", error.toString());
                                        }
                                    }
                            );
                            requestQueue8.add(request8);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }
    public void goBack(View view){
        Intent intent = new Intent(DetailActivity.this, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
