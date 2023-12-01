package com.example.reservify.models;
import java.io.Serializable;
import java.util.List;

import com.google.type.DateTime;

public class Cita implements Serializable{
    String id, fecha, hora, id_negocio, nombreNegocio, direccionNegocio, categoriaNegocio, id_usuario, foto;
    public Cita(String id, String fecha, String hora, String id_negocio, String nombreNegocio, String direccionNegocio, String categoriaNegocio, String id_usuario, String foto){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.id_negocio = id_negocio;
        this.nombreNegocio = nombreNegocio;
        this.direccionNegocio = direccionNegocio;
        this.categoriaNegocio = categoriaNegocio;
        this.id_usuario = id_usuario;
        this.foto = foto;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public String getHora(){return hora;}
    public void setHora(String hora){this.hora = hora;}
    public String getFecha(){return fecha;}
    public void setFecha(String fecha){this.fecha = fecha;}
    public String getIdNegocio(){return id_negocio;}
    public void setIdNegocio(String id_negocio){this.id_negocio = id_negocio;}
    public String getNombreNegocio(){return nombreNegocio;}
    public void setNombreNegocio(String nombreNegocio){this.nombreNegocio = nombreNegocio;}
    public String getDireccionNegocio(){return direccionNegocio;}
    public void setDireccionNegocio(String direccionNegocio){this.direccionNegocio = direccionNegocio;}
    public String getCategoriaNegocio(){return categoriaNegocio;}
    public void setCategoriaNegocio(String categoriaNegocio){this.categoriaNegocio = categoriaNegocio;}
    public String getId_usuario(){return id_usuario;}
    public void setId_usuario(String id_usuario){this.id_usuario = id_usuario;}
    public String getFoto(){return foto;}
    public void setFoto(String foto){this.foto = foto;}

}
