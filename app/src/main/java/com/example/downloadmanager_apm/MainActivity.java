package com.example.downloadmanager_apm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lstFichero;
    private Fichero fichero;
    private ArrayList<Fichero> list_Fi;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstFichero = (RecyclerView) findViewById(R.id.lst_Ficheros);
        lstFichero.setHasFixedSize(true);
        lstFichero.setLayoutManager(new LinearLayoutManager(this));
        lstFichero.setItemAnimator(new DefaultItemAnimator());


        requestQueue = Volley.newRequestQueue(this);
        stringRequest();

    }
    public void stringRequest() {

        String URL = "https://my-json-server.typicode.com/dennissezambrano2017/demo_json/db";
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("Fichero");

                            list_Fi = Fichero.JsonObjectsBuild(jsonArray);
                            Adapter adapter = new Adapter(getApplicationContext(), list_Fi);

                            lstFichero.setAdapter(adapter);


                        } catch (JSONException e) {
                            Log.d("Error: ", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }



    public void botno(View v){
        Log.d("Error: ","hola");
    }

}

