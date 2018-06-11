package com.example.osuna.inmobiliaria;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Globales {
    public static LatLng Seleccionada = null;
    public static String JsonCasa = null;
    public static GoogleSignInAccount account;
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        // cargamos la imagen de origen
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        // calculamos el escalado de la imagen destino
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // para poder manipular la imagen
        // debemos crear una matriz
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);
        // volvemos a crear la imagen con los nuevos valores
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                width, height, matrix, true);
        // si queremos poder mostrar nuestra imagen tenemos que crear un
        // objeto drawable y así asignarlo a un botón, imageview...
        return resizedBitmap;
    }
    public static void CargarImagen(final Context contexto, final ImageView imageView, final String link){
        Picasso.with(contexto).load(link).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(Globales.resizeImage(bitmap, 250,250));
            }
            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                CargarImagen(contexto, imageView, link);
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
    public static void CargarImagen(final Context contexto, final ImageView imageView, final int IMAGEN){
        Picasso.with(contexto).load(IMAGEN).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(Globales.resizeImage(bitmap, 250,250));
            }
            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                CargarImagen(contexto, imageView, IMAGEN);
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
    public static void CargarImagen(final Context contexto, final FloatingActionButton fab, final int IMAGEN){
        Picasso.with(contexto).load(IMAGEN).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                fab.setImageBitmap(Globales.resizeImage(bitmap, 250,250));
            }
            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                CargarImagen(contexto, fab, IMAGEN);
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
    public static void CargarImagen(final Context contexto, final FloatingActionButton fab, final String link){
        Picasso.with(contexto).load(link).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                fab.setImageBitmap(Globales.resizeImage(bitmap, 250,250));
            }
            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                CargarImagen(contexto, fab, link);
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
}
