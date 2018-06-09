package com.example.osuna.inmobiliaria;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class Lista_adaptador extends BaseAdapter {

    public ArrayList<?> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public Lista_adaptador(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
        super();
        this.contexto = contexto;
        this.entradas = entradas;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (entradas.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param entrada La entrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public void onEntrada (final Object entrada, View view)
    {
        TextView Precio = (TextView) view.findViewById(R.id.precioList);
        Precio.setText(((Lista_entrada) entrada).get_Precio());

        TextView Descripcion = (TextView) view.findViewById(R.id.atributosList);
        Descripcion.setText(((Lista_entrada) entrada).get_Descripcion());

        TextView Direccion = (TextView) view.findViewById(R.id.direccionList);
        Direccion.setText(((Lista_entrada) entrada).get_Direccion());

        final ImageView imageview = (ImageView) view.findViewById(R.id.imageViewList);

        Picasso.with(contexto).load(((Lista_entrada) entrada).get_idImagen()).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageview.setImageBitmap(Globales.resizeImage(bitmap, 250,250));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });
        /*ImageView imgview = (ImageView) view.findViewById(R.id.imageView_imagen);
        Picasso.with(contexto).load(((Lista_entrada) entrada).get_idImagen()).fit().into(imgview);*/
    }
}

