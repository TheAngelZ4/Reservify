package com.example.reservify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText edtNombre, edtApellidos, edtTelefono, edtCorreo, edtContra, edtContraConfi;
    String nombre = " ", apellidos = " " ,telefono = " ",correo = " ", contrasena = " ", confirmarcontra = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edtNombre = findViewById(R.id.edtNombre);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtTelefono = findViewById(R.id.edtNumTel);
        edtCorreo = findViewById(R.id.edtcorreo);
        edtContra = findViewById(R.id.edtcontra);
        edtContraConfi = findViewById(R.id.edtContraConfi);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
            }
        });
    }

    private void validarDatos(){
        nombre = edtNombre.getText().toString();
        apellidos = edtApellidos.getText().toString();
        correo = edtCorreo.getText().toString();
        telefono = edtTelefono.getText().toString();
        contrasena = edtContra.getText().toString();
        confirmarcontra = edtContraConfi.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            Toast.makeText(this, "Ingresar nombre", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(apellidos)) {
            Toast.makeText(this, "Ingrese un apellido", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingrese un correo valido", Toast.LENGTH_SHORT).show();
        } else if (contrasena.length()<6) {
            Toast.makeText(this, "La contraseña debe ser igual o mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(contrasena)){
            Toast.makeText(this, "Ingresar contraseña", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmarcontra)) {
            Toast.makeText(this, "Confirme la contraseña", Toast.LENGTH_SHORT).show();
        } else if (!contrasena.equals(confirmarcontra)) {
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            //Crear la cuenta de usuario.
            /* Consumo de Api para recuperar Id del Usuario */
            Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
            Call<UsuarioResponse> call = apiInterface.registrar(nombre, apellidos, telefono, correo, contrasena);

            call.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    if(response.isSuccessful()){
                        UsuarioResponse usuario_response = response.body();
                        Usuario usuario = usuario_response.getUsuario();

                    }
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                }
            });
        }
    }

}
