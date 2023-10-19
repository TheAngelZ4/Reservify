package com.example.reservify;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registrar extends AppCompatActivity {


     FirebaseAuth mAuth;
     Button btnRegistrar;
     EditText edtNombre;
     EditText edtCorreo;
     EditText edtContra;
     EditText edtContraConfi;

     String nombre = " ", correo = " ", contrasena = " ", confirmarcontra = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();
        edtNombre = findViewById(R.id.edtNombre);
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
        correo = edtCorreo.getText().toString();
        contrasena = edtContra.getText().toString();
        confirmarcontra = edtContraConfi.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            Toast.makeText(this, "Ingresar nombre", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingrese un correo valido", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(contrasena)){
            Toast.makeText(this, "Ingresar contraseña", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmarcontra)) {
            Toast.makeText(this, "Confirme la contraseña", Toast.LENGTH_SHORT).show();
        } else if (!contrasena.equals(confirmarcontra)) {
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {

            crearCuenta();
        }
    }

    private void crearCuenta() {
        //Crear la cuenta de usuario.
        mAuth.createUserWithEmailAndPassword(correo, contrasena).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                guardarInformacion();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registrar.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarInformacion() {
        String uid = mAuth.getUid();
        HashMap<String, String> datos = new HashMap<>();
        datos.put("iud", uid);
        datos.put("nombre", nombre);
        datos.put("correo", correo);
        datos.put("contrasena", contrasena);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        databaseReference.child(uid)
                .setValue(datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Registrar.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registrar.this, MainScreen.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registrar.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
