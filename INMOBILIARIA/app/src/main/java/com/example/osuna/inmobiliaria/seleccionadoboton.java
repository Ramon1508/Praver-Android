package com.example.osuna.inmobiliaria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
<<<<<<< HEAD
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
=======
>>>>>>> parent of 1f29d08... Extracción de urls

public class seleccionadoboton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionadoboton);
        JsonObject jsonObjectHijo = (new JsonParser()).parse(Globales.JsonCasa).getAsJsonObject();
        final String titulo = jsonObjectHijo.get("titulo").toString().replace("\"", "");
        ((TextView) findViewById(R.id.tituloprop3)).setText(titulo);
        String precio;
        if (!jsonObjectHijo.get("precioVenta").toString().replace("\"", "").equals(""))
            precio = "Precio de venta: $" + jsonObjectHijo.get("precioVenta").toString().replace("\"", "");
        else if (!jsonObjectHijo.get("precioRenta").toString().replace("\"", "").equals(""))
            precio = "Precio de renta: $" + jsonObjectHijo.get("precioRenta").toString().replace("\"", "");
        else
            precio = "Precio de traspaso: $" + jsonObjectHijo.get("precioTraspaso").toString().replace("\"", "");
        ((TextView) findViewById(R.id.precio)).setText(jsonObjectHijo.get("titulo").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.precio)).setText(precio);
        ((TextView) findViewById(R.id.vendedor)).setText(jsonObjectHijo.get("datosVendedor").getAsJsonArray().get(0).getAsJsonObject().get("apellidoPaterno").toString().replace("\"", "") + " " + jsonObjectHijo.get("datosVendedor").getAsJsonArray().get(0).getAsJsonObject().get("apellidoMaterno").toString().replace("\"", "") +" " + jsonObjectHijo.get("datosVendedor").getAsJsonArray().get(0).getAsJsonObject().get("nombre").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.metrostotales)).setText(jsonObjectHijo.get("metrosTotales").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.metrosconstruidos)).setText(jsonObjectHijo.get("metrosConstruidos").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.antiguedad)).setText(jsonObjectHijo.get("antiguedad").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.banos)).setText(jsonObjectHijo.get("banos").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.mediosbanos)).setText(jsonObjectHijo.get("mediosBanos").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.entidadfederativa)).setText(jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_estado").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.municipio)).setText(jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("D_mnpio").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.colonia)).setText(jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_asenta").toString().replace("\"", ""));
        ((TextView) findViewById(R.id.detalleprop)).setText(jsonObjectHijo.get("descripcion").toString().replace("\"", ""));
<<<<<<< HEAD
                                //A PARTIR DE AQUI NECESITA ESTAR LOGEADO PARA VER
=======
                                        //A PARTIR DE AQUI NECESITA ESTAR LOGEADO PARA VER
>>>>>>> parent of 1f29d08... Extracción de urls
        Boolean logeado = true;
        if (logeado) {
            final String telefono = jsonObjectHijo.get("datosVendedor").getAsJsonArray().get(0).getAsJsonObject().get("telefono").toString().replace("\"", "");
            ((ImageButton) findViewById(R.id.llamar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + telefono)));
                }
            });
            final String email = "valenzuela_luna@hotmail.com";
            ((ImageButton)findViewById(R.id.mensaje)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", email, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, titulo);
                    startActivity(Intent.createChooser(emailIntent, null));
                }
            });
            ((CardView) findViewById(R.id.contacto)).setVisibility(View.VISIBLE);
            if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("exac")) {
                ((TextView) findViewById(R.id.calle)).setText(jsonObjectHijo.get("calle").toString().replace("\"", ""));
                ((TextView) findViewById(R.id.numint)).setText(jsonObjectHijo.get("numInt").toString().replace("\"", ""));
                ((TextView) findViewById(R.id.numext)).setText(jsonObjectHijo.get("numExt").toString().replace("\"", ""));
                ((TextView) findViewById(R.id.codigopostal)).setText(jsonObjectHijo.get("codigoPostal").toString().replace("\"", ""));
                ImageButton ruta = (ImageButton) findViewById(R.id.ruta);
                ruta.setVisibility(View.VISIBLE);
                final LatLng destino = new LatLng(Double.parseDouble(jsonObjectHijo.get("latitud").toString().replace("\"", "")), Double.parseDouble(jsonObjectHijo.get("longitud").toString().replace("\"", "")));
                ruta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destino.latitude + "," + destino.longitude + "&mode=d");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });
            } else if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("apro")) {
                ImageButton ruta = (ImageButton) findViewById(R.id.ruta);
                ruta.setVisibility(View.VISIBLE);
                final LatLng destino = new LatLng(Double.parseDouble(jsonObjectHijo.get("latitud").toString().replace("\"", "")), Double.parseDouble(jsonObjectHijo.get("longitud").toString().replace("\"", "")));
                ruta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destino.latitude + "," + destino.longitude + "&mode=d");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });
                ((TextView) findViewById(R.id.calle)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.numint)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.numext)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.codigopostal)).setVisibility(View.GONE);
            } else {
                ((ImageButton) findViewById(R.id.ruta)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.calle)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.numint)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.numext)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.codigopostal)).setVisibility(View.GONE);
            }
        }
        else {
            ((CardView) findViewById(R.id.contacto)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.calle)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.numint)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.numext)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.codigopostal)).setVisibility(View.GONE);
        }
<<<<<<< HEAD

=======
>>>>>>> parent of 1f29d08... Extracción de urls
    }
}
