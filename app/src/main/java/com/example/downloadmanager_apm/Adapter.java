package com.example.downloadmanager_apm;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import java.net.URL;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context econtext;
    private ArrayList<Fichero> elista;


    public Adapter(Context contex, ArrayList<Fichero> lusuario) {
        econtext = contex;
        this.elista = lusuario;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(econtext);
        View v = inflater.inflate(R.layout.activity_item_main, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fichero actual = elista.get(position);
        int id = actual.getId();
        String Descrip = actual.getDescripcion();
        String Fecha = actual.getFecha();
        String Url = actual.getUrl();

        Log.d("id:",Fecha);
        holder.mDescripcion.setText("Fichero " +id+" :" +Descrip);
        holder.mFecha.setText("Fecha: " + Fecha);
    }

    @Override
    public int getItemCount() {
        return elista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mId, mDescripcion, mFecha;
        public item_main itemMain = new item_main();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mDescripcion = itemView.findViewById(R.id.lblDescripcion);
            mFecha = itemView.findViewById(R.id.lblFecha);
            itemView.findViewById(R.id.btnDownload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        Log.d("Url", "mId.toString()"+position);
                        itemMain.DownDoc(v);
                    }
                }
            });
        }
    }
}
