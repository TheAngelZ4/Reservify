package com.example.reservify.models;

import com.google.type.DateTime;

public class Cita {
    private Integer id;
    private String fecha;
    private String hora;
    private Integer id_negocio;
    private String nombreNegocio;
    private String direccionNegocio;
    private String categoriaNegocio;
    private Integer id_usuario;

    public Cita(Integer id, String fecha, String hora, Integer id_negocio, String nombreNegocio, String direccionNegocio, String categoriaNegocio, Integer id_usuario){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.id_negocio = id_negocio;
        this.nombreNegocio = nombreNegocio;
        this.direccionNegocio = direccionNegocio;
        this.categoriaNegocio = categoriaNegocio;
        this.id_usuario = id_usuario;
    }
}
