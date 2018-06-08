package com.example.osuna.inmobiliaria;

public class Lista_entrada {
    private String idImagen;
    private String Precio;
    private String Descripcion;
    private String Direccion;
    public  String JSON;
    public Lista_entrada (String idImagen, String Precio, String Descripcion, String Direccion) {
        this.idImagen = idImagen;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Direccion = Direccion;
    }
    public String get_Precio() {
        return Precio;
    }
    public String get_Descripcion() {
        return Descripcion;
    }
    public String get_Direccion() {
        return Direccion;
    }
    public String get_idImagen() {
        return idImagen;
    }
}