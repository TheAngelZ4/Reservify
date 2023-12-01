package com.example.reservify.API;

import com.example.reservify.models.Cita;
import com.example.reservify.models.PopularModelResponse;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api_Interface {

    @GET("/api/Negocio")
    Call<PopularModelResponse> recuperar_negocios();

    @GET("api/Usuario/acceder/{correo}/{pass}")
    Call<UsuarioResponse> login(
        @Path("correo") String correo,
        @Path("pass") String pass );

    @POST("api/Usuario/registrarUsuario")
    Call<UsuarioResponse> registrar(
            @Path("correo") String correo,
            @Path("pass") String pass,
            @Path("nombre") String nombre,
            @Path("apellidos") String apellidos,
            @Path ("telefono") String telefono);

    @GET("api/Citas/GetCitasUsuarioApp/{id}")
    Call<List<Cita>> citasUsuario(
        @Path("id") Integer id);

}
