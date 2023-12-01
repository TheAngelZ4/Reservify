package com.example.reservify.models;

public class AgendarCitas {
    public String id;
    public String fecha;
    public String hora;
    public Integer id_negocio;
    public Integer id_usuario;

    public AgendarCitas(String id, String fecha, String hora, Integer id_negocio, Integer id_usuario){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.id_negocio = id_negocio;
        this.id_usuario = id_usuario;
    }


}
