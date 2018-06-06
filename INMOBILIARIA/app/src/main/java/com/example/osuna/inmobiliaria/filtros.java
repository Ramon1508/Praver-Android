package com.example.osuna.inmobiliaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

public class filtros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_filtros);


        ImageButton regresar;
        regresar=(ImageButton)findViewById(R.id.cmdregresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscarcasa=new Intent(filtros.this, buscandocasa.class);
                startActivity(buscarcasa);

            }
        });
    }

}
