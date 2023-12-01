package com.example.reservify.models;

public class UsuarioResponse {
    public String mensaje;
    public Usuario response;

    public UsuarioResponse(String mensaje, Usuario response){
        this.mensaje = mensaje;
        this.response = response;
    }


    public Usuario getUsuario(){
        return this.response;
    }
    public String getMensaje(){
        return this.mensaje;
    }

}
