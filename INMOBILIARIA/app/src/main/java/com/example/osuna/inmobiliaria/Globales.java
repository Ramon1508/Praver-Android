package com.example.osuna.inmobiliaria;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;

public class Globales {
    public static LatLng Seleccionada = null;
    public static GoogleApiClient googleApiClient = null;
    public static GoogleSignInOptions gso = null;
    public static GoogleSignInAccount account = null;
    public static String JsonCasa = null;
    public static Intent ConectionIntent = null;
}
