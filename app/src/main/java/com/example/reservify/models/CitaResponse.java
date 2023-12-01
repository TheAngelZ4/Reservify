package com.example.reservify.models;
import java.util.List;
public class CitaResponse {
    public String mensaje;
    public List<Cita> response;

    public CitaResponse(String mensaje, List<Cita> response){
        this.mensaje = mensaje;
        this.response = response;
    }


}
