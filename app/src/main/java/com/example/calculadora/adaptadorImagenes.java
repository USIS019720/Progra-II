package com.example.calculadora;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorImagenes extends BaseAdapter {
    Context context;
    ArrayList <productos> datosProductosArraryList;
    LayoutInflater layoutInflater;
    productos misProductos;

    public adaptadorImagenes(Context context, ArrayList<productos> datosProductosArraryList) {
        this.context = context;
        this.datosProductosArraryList = datosProductosArraryList;
    }

    @Override
    public int getCount() {
        return datosProductosArraryList.size();
    }

    @Override
    public Object getItem(int position) {
        return datosProductosArraryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(datosProductosArraryList.get(position).idProducto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes, parent, false);
        TextView tempVal = itemView.findViewById(R.id.lbltitulo);
        ImageView imgViewView = itemView.findViewById(R.id.imgPhoto);
        try {
            misProductos = datosProductosArraryList.get(position);
            tempVal.setText(misProductos.getCodigo());
            Bitmap imagenBitmap = BitmapFactory.decodeFile(misProductos.getUrlImg());
            imgViewView.setImageBitmap(imagenBitmap);

        }catch (Exception e){

        }

        return itemView;
    }
}
