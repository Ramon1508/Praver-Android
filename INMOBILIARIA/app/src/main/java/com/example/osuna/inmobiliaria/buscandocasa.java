package com.example.osuna.inmobiliaria;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.synnapps.carouselview.ImageListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

public class buscandocasa extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.OnConnectionFailedListener {
    TabHost TbH;
    private GoogleMap mMap;
    PlaceAutocompleteFragment autocompleteFragment;
    protected float ZoomBusqueda = (float) 10.8;
    ArrayList<Marker> arregloM = new ArrayList();
    ArrayList<Circle> arregloC = new ArrayList();
    FloatingActionButton fab;
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Boolean mLocationPermissionsGranted = false;
    public LatLng MiLatLan = new LatLng(29.780566720478227, -108.97191764193184);
    public float MiZoom = (float) 13.0;
    Boolean cargamapa = false;
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    public void BuscarCasas(LatLng Norte, LatLng Sur){
        if (!arregloM.isEmpty()) {
            for (Marker p : arregloM) {
                p.remove();
            }
        }
        if (!arregloC.isEmpty()) {
            for (Circle c : arregloC) {
                c.remove();
            }
        }
        arregloM.clear();
        arregloC.clear();
        String url = "http://159.65.231.12/api/inmuebles/" + Norte.latitude +"/" + Sur.latitude + "/" + Norte.longitude + "/" + Sur.longitude;
        Ion.with(getApplicationContext()).load(url).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                if (result.equals("Error")) return;
                ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
                JsonParser jsonParser = new JsonParser();
                JsonArray resultados = jsonParser.parse(result).getAsJsonArray();
                for (int i = 0; i < resultados.size(); i++)
                {
                    JsonElement jsonElementHijo = resultados.get(i);
                    JsonObject jsonObjectHijo = jsonElementHijo.getAsJsonObject();
                    String LPRECIO="$" + jsonObjectHijo.get("precioVenta").toString().replace("\"", "") + " ";
                    String LIDIMAGEN="http://159.65.231.12/" + jsonObjectHijo.get("imagenes").getAsJsonArray().get(0).getAsJsonObject().get("imagen").toString().replace("\"", "").replace("./","/");
                    String LDESC = "";
                    String LDIR="";
                    String Lat = jsonObjectHijo.get("latitud").toString().replace("\"", "");
                    Double LatD = Double.parseDouble(Lat);
                    String Titulo= jsonObjectHijo.get("titulo").toString().replace("\"", "");
                    LDESC = Titulo;
                    if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("exac"))
                    {
                        LDIR = jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_asenta").toString().replace("\"", "") + ", " + jsonObjectHijo.get("calle").toString().replace("\"", "") + " " + jsonObjectHijo.get("numExt").toString().replace("\"", "") + "\n" + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("D_mnpio").toString().replace("\"", "") + ", " + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_estado").toString().replace("\"", "");
                    }
                    else if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("apro")){
                        LDIR = jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_asenta").toString().replace("\"", "") + "\n" + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("D_mnpio").toString().replace("\"", "") + ", " + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_estado").toString().replace("\"", "");
                    }
                    else {
                        LDIR = jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_asenta").toString().replace("\"", "") + "\n" + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("D_mnpio").toString().replace("\"", "") + ", " + jsonObjectHijo.get("descripciones").getAsJsonArray().get(0).getAsJsonObject().get("d_estado").toString().replace("\"", "");
                    }

                    String Lon = jsonObjectHijo.get("longitud").toString().replace("\"", "");
                    Double LonD = Double.parseDouble(Lon);
                    if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("exac")) {
                        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(LatD, LonD)).title(Titulo));
                        arregloM.add(marker);
                    }
                    else if (jsonObjectHijo.get("mostrarMapa").toString().replace("\"", "").equals("apro")) {
                        Circle circle = mMap.addCircle(new CircleOptions().center(new LatLng(LatD, LonD))
                                .radius(130)
                                .strokeWidth(2)
                                .fillColor(Color.argb(128, 168, 228, 255))
                                .clickable(true)
                                .strokeColor(Color.BLACK)
                        );
                        arregloC.add(circle);
                        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(LatD, LonD)).title(Titulo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                        arregloM.add(marker);
                    }
                    Lista_entrada lis = new Lista_entrada(LIDIMAGEN,LPRECIO,LDESC,LDIR);
                    lis.JSON = jsonObjectHijo.toString();
                    datos.add(lis);
                }
                final ListView lista = (ListView) findViewById(R.id.mi_lista);
                final Lista_adaptador adaptador = new Lista_adaptador(buscandocasa.this, R.layout.casas, datos);
                lista.setAdapter(adaptador);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                        Globales.JsonCasa = ((Lista_entrada) adaptador.getItem(posicion)).JSON;
                        Intent Cargando=new Intent(buscandocasa.this, seleccionadoboton.class);
                        startActivity(Cargando);
                    }
                });
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String url = "http://159.65.231.12/api/inmuebleLatLon/" + marker.getPosition().latitude +"/" + marker.getPosition().longitude;
                        Ion.with(getApplicationContext()).load(url).asString().setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                JsonParser parser = new JsonParser();
                                JsonArray results = parser.parse(result).getAsJsonArray();
                                JsonElement ElementHijo = results.get(0);
                                JsonObject ObjectHijo = ElementHijo.getAsJsonObject();
                                Globales.JsonCasa = ObjectHijo.toString();
                                Intent Cargando=new Intent(buscandocasa.this, seleccionadoboton.class);
                                startActivity(Cargando);
                            }
                        });
                        return false;
                    }
                });
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscandocasa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocationPermission();
        // Construct a FusedLocationProviderClient.
        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                                                         //lo activamos
        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");
        tab1.setIndicator("INMUEBLES");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.ejemplo1); //definimos el id de cada Tab (pestaña)
        tab2.setIndicator("MAPA");
        tab2.setContent(R.id.ejemplo2);
        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
        TbH.setCurrentTab(1);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Globales.account != null) {
                    if (Globales.googleSignInResult.isSuccess())
                    {
                        Auth.GoogleSignInApi.signOut(Globales.googleApiClient).setResultCallback(
                                new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        Toast.makeText(getApplicationContext(),"Te has desconectado con éxito",Toast.LENGTH_SHORT).show();
                                        Globales.Seleccionada = null;
                                        Globales.account = null;
                                        Globales.ConectionIntent = null;
                                        AsignarEvento();
                                    }
                                });
                    }
                    else {
                        Globales.Seleccionada = null;
                        Globales.account = null;
                        int SIGN_IN_CODE = 777;
                        Globales.ConectionIntent = Auth.GoogleSignInApi.getSignInIntent(Globales.googleApiClient);
                        startActivityForResult(Globales.ConectionIntent, SIGN_IN_CODE);
                        AsignarEvento();
                    }
                }
                else {
                    int SIGN_IN_CODE = 777;
                    Globales.ConectionIntent = Auth.GoogleSignInApi.getSignInIntent(Globales.googleApiClient);
                    startActivityForResult(Globales.ConectionIntent, SIGN_IN_CODE);
                    AsignarEvento();
                }
            }
        });
        final ImageButton filtros = (ImageButton) findViewById(R.id.cmdfiltro);
        filtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buscandocasa.this, filtros.class));

            }
        });
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                final LatLng latLngLoc = place.getLatLng();
                BuscarCasas(new LatLng(latLngLoc.latitude + (double) 0.187342700624766,latLngLoc.longitude + (double) 0.14197514587563), new LatLng(latLngLoc.latitude - (double) 0.187574470686339,latLngLoc.longitude - (double) 0.14197456455307));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngLoc, ZoomBusqueda));
            }
            @Override
            public void onError(Status status) {
                Toast.makeText(buscandocasa.this, ""+status.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        if (Globales.gso == null)
            Globales.gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
        if (Globales.googleApiClient == null)
            Globales.googleApiClient=new GoogleApiClient.Builder(buscandocasa.this)
                    .enableAutoManage(buscandocasa.this,buscandocasa.this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API,Globales.gso)
                    .build();
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (!cargamapa)
        {
            cargamapa=true;
            TbH.setCurrentTab(0);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MiLatLan, MiZoom));
        mMap.setMyLocationEnabled(true);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                BuscarCasas(new LatLng(bounds.northeast.latitude, bounds.northeast.longitude),new LatLng(bounds.southwest.latitude, bounds.southwest.longitude));
            }
        });
        getDeviceLocation();
    }
    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            MiLatLan = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                            //Mover cámara
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MiLatLan, MiZoom));
                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(buscandocasa.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }
    @Override
    public void onBackPressed() {
        Globales.Seleccionada = null;
        Globales.gso = null;
        Globales.account = null;
        Intent Inicio=new Intent(buscandocasa.this, MainActivity.class);
        startActivity(Inicio);
        finish();
        super.onBackPressed();
    }
    public static String[] añadirValorArray(String[] a, String e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        AsignarEvento();
    }
    void AsignarEvento(){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(Globales.googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Globales.googleSignInResult = result;
        if (result.isSuccess()) {
            Globales.account = result.getSignInAccount();
            //falta poner imagen de logeado
            Picasso.with(buscandocasa.this).load(R.drawable.usuario).into(fab);
            Picasso.with(buscandocasa.this).load(R.drawable.usuario).into(new Target(){
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    fab.setBackground(new BitmapDrawable(buscandocasa.this.getResources(), bitmap));
                }
                @Override
                public void onBitmapFailed(final Drawable errorDrawable) {
                    Log.d("TAG", "FAILED");
                }
                @Override
                public void onPrepareLoad(final Drawable placeHolderDrawable) { Log.d("TAG", "Prepare Load"); }
            });
            //account.getId() PARA CONSEGUIR ID LOGEADO
        }else{
            Picasso.with(buscandocasa.this).load(R.drawable.usuarionologeado).into(fab);
            Picasso.with(buscandocasa.this).load(R.drawable.usuarionologeado).into(new Target(){
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    fab.setBackground(new BitmapDrawable(buscandocasa.this.getResources(), bitmap));
                }
                @Override
                public void onBitmapFailed(final Drawable errorDrawable) {
                    Log.d("TAG", "FAILED");
                }
                @Override
                public void onPrepareLoad(final Drawable placeHolderDrawable) { Log.d("TAG", "Prepare Load"); }
            });
        }
    }
}
