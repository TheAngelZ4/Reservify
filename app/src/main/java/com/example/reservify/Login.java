package com.example.reservify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

     EditText edtcorreo;
     EditText edtcontra;
     TextView resetPassword;
     Button btnIngresar;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.btnInicar);
        edtcorreo = findViewById(R.id.edtcorreo);
        edtcontra = findViewById(R.id.edtcontra);
        resetPassword = findViewById(R.id.resetPassword);

        sessionManager = new SessionManager(this);

        if (sessionManager.getUserId() != -1){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            finish();
        }

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), olvidarPassword.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Iniciar(View view){
        /* Leer los datos de los et */
        String correo = edtcorreo.getText().toString();
        String password = edtcontra.getText().toString();

        /* Consumo de Api para recuperar Id del Usuario */
        Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
        Call<UsuarioResponse> call = apiInterface.login(correo, password);

        /* TRAE LOS DATOS DE LA API  */
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                if (response.isSuccessful()) {

                    UsuarioResponse usuario_response = response.body();
                    Usuario usuario = usuario_response.getUsuario();

                    Integer id_usuario = usuario.getIdUsuario();

                    if (id_usuario != null){
                        sessionManager.setKeyUserId(id_usuario);
                        Context context = getApplicationContext();
                        Intent intent = new Intent(context, MainScreen.class);
                        startActivity(intent);
                        Toast.makeText(view.getContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecta",
                                Toast.LENGTH_SHORT).show();
                    }

                    System.out.println("Si jalo pa");

                }else{
                    System.out.println("No jalo pa");
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                String errorMessage = t.getMessage();
                System.out.println("Error: " + errorMessage);
                System.out.println("No jalo el llamado pa ");
            }

        });
    }


}