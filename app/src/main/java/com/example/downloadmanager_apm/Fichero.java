package com.example.downloadmanager_apm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fichero {
    private int id;
    private String descripcion;
    private String fecha;
    private String url;

    public Fichero() {
    }

    public Fichero(int id, String descripcion, String fecha, String url) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public static ArrayList<Fichero> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Fichero> fichero = new ArrayList<>();
        ArrayList <String> lis = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            JSONObject user=  datos.getJSONObject(i);
            Log.d("DATOS", user.toString());
            fichero.add(new Fichero(user.getInt("id"),
                    user.getString("Descripcion"),
                    user.getString("Fecha"),
                    user.getString("url")));
        }
        return fichero;
    }
}
