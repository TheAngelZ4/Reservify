package com.example.reservify;

public class PopularModel {
    String nombre, descripcion, valoracion, tipo, imagen;

    public PopularModel() {

    }

    public PopularModel(String nombre, String descripcion, String valoracion, String tipo, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.tipo = tipo;
        this.imagen = imagen;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
