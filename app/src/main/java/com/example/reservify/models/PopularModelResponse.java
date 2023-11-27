package com.example.reservify.models;

import java.util.List;

public class PopularModelResponse {

    public String mensaje;
    public List<PopularModel> response;

    public PopularModelResponse(String mensaje, List<PopularModel> response){
        this.mensaje = mensaje;
        this.response = response;
    }

    public List<PopularModel> getNegocios(){
        return this.response;
    }

}
