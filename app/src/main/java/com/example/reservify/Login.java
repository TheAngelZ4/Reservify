package com.example.reservify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.adapters.PopularAdapters;
import com.example.reservify.models.PopularModel;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

     FirebaseAuth mAuth;
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
        //mAuth = FirebaseAuth.getInstance();

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

   /* public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    } */

    public void IniciarSesion(View view){
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
                System.out.println("No jalo el llamado pa ");
            }

        });




        /*
        mAuth.signInWithEmailAndPassword(edtcorreo.getText().toString(), edtcontra.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), MainScreen.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Bienvenido",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        }else{
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecta",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
         */
    }


}