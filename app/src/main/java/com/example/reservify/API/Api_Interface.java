package com.example.reservify.API;

import com.example.reservify.models.PopularModel;
import com.example.reservify.models.PopularModelResponse;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api_Interface {

    @GET("/api/Negocio")
    Call<PopularModelResponse> recuperar_negocios();
    @GET("api/Usuario/acceder/{correo}/{pass}")
    Call<UsuarioResponse> login(
        @Path("correo") String correo,
        @Path("pass") String pass );

    /*
    @GET("http://10.0.2.2:8000/api/articulos_costos")
    Call<List<Articulos>> Articulos();

    @POST("http://www.reservify.somee.com/api/Negocio")
    Call<PopularModel> login(@Body DatosLoginRequest request);
     */

}
