package com.example.reservify.models;

import java.io.Serializable;

public class PopularModel implements Serializable {
    String idNegocio, nombre, descripcion, valoracion, categoria, foto, direccion, horaApertura, horaCierre;

    public PopularModel() {

    }

    public PopularModel(String idNegocio, String nombre, String descripcion, String valoracion, String categoria, String direccion, String horaApertura
            , String horaCierre, String foto) {
        this.idNegocio = idNegocio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.categoria = categoria;
        this.foto = foto;
        this.direccion = direccion;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public String getId() {
        return idNegocio;
    }

    public void setId(String id) {
        this.idNegocio = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getImagen() {
        return foto;
    }

    public void setImagen(String imagen) {
        this.foto = imagen;
    }
}
