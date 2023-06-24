package com.meggouriismail.geolocationapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FormActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mStringRequest;

    private Double lat;
    private Double lon;
    private String city;
    private TextView regionTxt;
    private TextView countryTxt;
    private EditText iptxt;
    private Button mapBtn;
    private Button findBtn;
    private String url = "https://ipinfo.io/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_form);

        regionTxt = findViewById(R.id.region);
        countryTxt = findViewById(R.id.country);
        mapBtn = findViewById(R.id.mapBtn);
        findBtn = findViewById(R.id.findBtn);
        iptxt = findViewById(R.id.iptxt);

        getData("8.8.8.8");

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAddress = iptxt.getText().toString();
                getData(ipAddress);
            }
        });


        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat);
                bundle.putDouble("lon", lon);
                bundle.putString("city", city);
                Intent i = new Intent(FormActivity.this, MapsActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }

    private void getData(String ipAddress) {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new JsonObjectRequest(Request.Method.GET, url.concat(ipAddress).concat("/geo"), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String city = response.getString("city");
                    String region = response.getString("region");
                    String country = response.getString("country");
                    lat = Double.valueOf(response.getString("loc").split(",")[0]);
                    lon = Double.valueOf(response.getString("loc").split(",")[1]);
                    city = response.getString("city");

                    // after extracting all the data we are
                    // setting that data to our view.
                    regionTxt.setText(city + ", " + region);
                    countryTxt.setText(country);

                } catch (JSONException e) {
                    // if we do not extract data from json object properly.
                    // below line of code is use to handle json exception
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
