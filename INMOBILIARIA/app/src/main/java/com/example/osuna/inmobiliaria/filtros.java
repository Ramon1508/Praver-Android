package com.example.osuna.inmobiliaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.Console;
import java.io.PrintStream;

public class filtros extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        ImageButton recmax=findViewById(R.id.cmdrecmax);

        //BOTONES AUMENTAR Y DISMINUIR A RECAMARAS
        recmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalrecam=findViewById(R.id.totalrec);
                String valorstr=totalrecam.getText().toString();
                if (valorstr==""){
                    valorstr="0";
                }
                int valorInt = Integer.parseInt(valorstr);
                valorInt=valorInt+1;
                totalrecam.setText(Integer.toString(valorInt));
            }
        });
        ImageButton recmin=findViewById(R.id.cmdrecamin);
        recmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalrecam=findViewById(R.id.totalrec);
                String valorstr=totalrecam.getText().toString();
                if (valorstr==""){
                    totalrecam.setText("");
                }else{
                    int valorInt = Integer.parseInt(valorstr);
                    valorInt=valorInt-1;
                    totalrecam.setText(Integer.toString(valorInt));
                    if (valorInt==0){
                        totalrecam.setText("");
                    }
                }



            }
        });
        //-----------------------
        //BOTONES DE AUMENTAR Y DISMINUIR COCHERAS
        ImageButton cocheramin=findViewById(R.id.cmdcocheramin);
        cocheramin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalcoch=findViewById(R.id.totalcocheras);
                String valorstr=totalcoch.getText().toString();
                if (valorstr==""){
                    totalcoch.setText("");
                }else{
                    int valorInt = Integer.parseInt(valorstr);
                    valorInt=valorInt-1;
                    totalcoch.setText(Integer.toString(valorInt));
                    if (valorInt==0){
                        totalcoch.setText("");
                    }
                }
            }
        });
        ImageButton cocheramax=findViewById(R.id.cmdcocheramax);
        cocheramax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalrecam=findViewById(R.id.totalcocheras);
                String valorstr=totalrecam.getText().toString();
                if (valorstr==""){
                    valorstr="0";
                }
                int valorInt = Integer.parseInt(valorstr);
                valorInt=valorInt+1;
                totalrecam.setText(Integer.toString(valorInt));
            }
        });
        //-------------------------------
        //BOTONES DE AUMENTAR Y DISMINUIR BAÑOS
        ImageButton banosmax=findViewById(R.id.cmdbañomax);
        banosmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalrecam=findViewById(R.id.totalbaños);
                String valorstr=totalrecam.getText().toString();
                if (valorstr==""){
                    valorstr="0";
                }
                int valorInt = Integer.parseInt(valorstr);
                valorInt=valorInt+1;
                totalrecam.setText(Integer.toString(valorInt));
            }
        });
        ImageButton banosmin=findViewById(R.id.cmdbañomin);
        banosmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView totalcoch=findViewById(R.id.totalbaños);
                String valorstr=totalcoch.getText().toString();
                if (valorstr==""){
                    totalcoch.setText("");
                }else{
                    int valorInt = Integer.parseInt(valorstr);
                    valorInt=valorInt-1;
                    totalcoch.setText(Integer.toString(valorInt));
                    if (valorInt==0){
                        totalcoch.setText("");
                    }
                }
            }
        });

        //-------------------------
        //vermas image button
        final ImageButton vermas=findViewById(R.id.cmdvermas);
        vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vermas.setVisibility(View.GONE);
                LinearLayout masfiltros=findViewById(R.id.masfiltros);
                masfiltros.setVisibility(View.VISIBLE);
                ImageButton vermenos=findViewById(R.id.cmdvermenos);
                vermenos.setVisibility(View.VISIBLE);
            }
        });
        //vermenos image button
        final ImageButton vermenos=findViewById(R.id.cmdvermenos);
        vermenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vermenos.setVisibility(View.GONE);
                ImageButton vermas=findViewById(R.id.cmdvermas);
                vermas.setVisibility(View.VISIBLE);
                LinearLayout masfiltros=findViewById(R.id.masfiltros);
                masfiltros.setVisibility(View.GONE);
            }
        });
        //-------------------------
    }

}
