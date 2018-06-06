package com.example.osuna.inmobiliaria;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher.ViewFactory;

import com.google.android.gms.vision.text.Line;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class inmuebledatos extends AppCompatActivity {


    String[] sampleImages = {"https://scontent.fhmo2-1.fna.fbcdn.net/v/t1.0-9/33501314_193377468144875_3080545162544283648_n.jpg?_nc_cat=0&_nc_eui2=AeGJMvGtThLitQHuM_DHa2xe37IiUmBiIGOcxVTMsY41oNgCVZUlN6wwHvjBiTjFHtHdyJ7V1WsI6t2m2NXuKKbvuxLD8FS9f_svBNEBRDjetw&oh=8b0f9a09bb9269c4a99855f4a9e0a382&oe=5B88113E","https://i.ebayimg.com/00/s/OTYwWDEyODA=/z/y6gAAOSwxwRaoFpd/$_20.JPG","https://i.ebayimg.com/00/s/ODQyWDE1MDA=/z/bAUAAOSwSIJaoFpe/$_20.JPG","https://i.ebayimg.com/00/s/ODQyWDE1MDA=/z/pQwAAOSwzwxaoFpg/$_20.JPG"}; //AQUI VAN LAS IMAGENES A MOSTRAR
    Integer x=0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmuebledatos);
        final CarouselView carouselView;
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        SampleCarouselViewActivity listen = new SampleCarouselViewActivity();
        carouselView.setImageListener(listen.imageListener);
        ImageButton regresar;
        regresar=(ImageButton)findViewById(R.id.cmdregresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscarcasa=new Intent(inmuebledatos.this, buscandocasa.class);
                startActivity(buscarcasa);
                finish();
            }
        });
        final ImageButton Mostrar;
        final ImageButton NoMostrar;
        Mostrar=(ImageButton)findViewById(R.id.maximizar);
        NoMostrar=(ImageButton)findViewById(R.id.minimizar);
        Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout layout;
                layout=(RelativeLayout)findViewById(R.id.lantiguedad);
                layout.setVisibility(view.VISIBLE);
                layout=(RelativeLayout)findViewById(R.id.lrecamaras);
                layout.setVisibility(view.VISIBLE);
                layout=(RelativeLayout)findViewById(R.id.lestacionamiento);
                layout.setVisibility(view.VISIBLE);
                layout=(RelativeLayout)findViewById(R.id.lbanos);
                layout.setVisibility(view.VISIBLE);
                layout=(RelativeLayout)findViewById(R.id.lmetros);
                layout.setVisibility(view.VISIBLE);
                Mostrar.setVisibility(view.INVISIBLE);
                NoMostrar.setVisibility(view.VISIBLE);
            }
        });

        NoMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout layout;
                layout=(RelativeLayout)findViewById(R.id.lantiguedad);
                layout.setVisibility(view.GONE);
                layout=(RelativeLayout)findViewById(R.id.lrecamaras);
                layout.setVisibility(view.GONE);
                layout=(RelativeLayout)findViewById(R.id.lestacionamiento);
                layout.setVisibility(view.GONE);
                layout=(RelativeLayout)findViewById(R.id.lbanos);
                layout.setVisibility(view.GONE);
                layout=(RelativeLayout)findViewById(R.id.lmetros);
                layout.setVisibility(view.GONE);
                Mostrar.setVisibility(view.VISIBLE);
                NoMostrar.setVisibility(view.INVISIBLE);
            }
        });

        final CarouselView AgrandaCarrusel=(CarouselView) findViewById(R.id.carouselView);
        AgrandaCarrusel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AgrandaCarrusel.setMinimumHeight(250);

            }
        });

    }

    public class SampleCarouselViewActivity extends AppCompatActivity {


        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Picasso.with(imageView.getContext()).load(sampleImages[x]).fit().into(imageView);
                x+=1;

            }
        };

    }


}
