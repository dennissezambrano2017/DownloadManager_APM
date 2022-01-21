package com.example.downloadmanager_apm;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Permisos_Download extends AppCompatActivity {
    private ArrayList<String> permiso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_item);

        permiso = new ArrayList<String>();
        permiso.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permiso.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        getPermission(permiso);
    }

    public void getPermission(ArrayList<String> permisosSolicitados) {
        try {
            ArrayList<String> listPermisosNOAprob = getPermisosNoAprobados(permisosSolicitados);
            if (listPermisosNOAprob.size() > 0)
                if (Build.VERSION.SDK_INT >= 23)
                    requestPermissions(listPermisosNOAprob.toArray(new String[listPermisosNOAprob.size()]), 1);

        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }

    }


    public ArrayList<String> getPermisosNoAprobados(ArrayList<String> listaPermisos) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (String permiso : listaPermisos) {
                if (Build.VERSION.SDK_INT >= 23)
                    if (checkSelfPermission(permiso) != PackageManager.PERMISSION_GRANTED)
                        list.add(permiso);

            }
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        return list;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            String s = "";
            if (requestCode == 1) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                        s = s + "OK " + permissions[i] + "\n";
                    else
                        s = s + "NO  " + permissions[i] + "\n";
                }
                Toast.makeText(this.getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
    }

    public void DownDoc(View view) {
        String url = "https://cs.uns.edu.ar/~ldm/mypage/data/oc/info/guia_para_la_documentacion_de_proyectos_de_software.pdf";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("PDF");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Pdf");
        request.setVisibleInDownloadsUi(true);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        //request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //}
        request.setDestinationInExternalFilesDir(this.getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "file.pdf");
        //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filedownload.pdf");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        try {

            manager.enqueue(request);
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void Doc(View view) {
        Log.d("Error: ", view.toString());
    }

}
