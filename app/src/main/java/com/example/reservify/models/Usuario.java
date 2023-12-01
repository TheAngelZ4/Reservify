package com.example.reservify.models;


public class Usuario {
   private Integer idUsuario;
   private Integer idNegocio;
   private String nombre;
   private String apellidos;
   private String correo;
   private String pass;
   private String telefono;


   public Usuario(Integer idUsuario, Integer idNegocio, String nombre, String apellidos, String correo, String pass, String telefono){
   this.idUsuario = idUsuario;
   this.idNegocio = idNegocio;
   this.nombre = nombre;
   this.apellidos = apellidos;
   this.correo = correo;
   this.pass = pass;
   this.telefono = telefono;
   }

   public Integer getIdUsuario(){
      return this.idUsuario;
   }

   public String getNombre(){
      return this.nombre;
   }
   public String getApellidos(){
      return this.apellidos;
   }
   public String getCorreo(){
      return this.correo;
   }

   public String getTelefono(){
      return this.telefono;
   }
}
