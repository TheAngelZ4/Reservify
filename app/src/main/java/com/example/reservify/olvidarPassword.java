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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class olvidarPassword extends AppCompatActivity {

    EditText edtCorreo;
    Button btnRestablecer;
    FirebaseAuth mAuth;
    String correo = " ";
    ProgressBar contrasenaProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar_password);

        mAuth = FirebaseAuth.getInstance();
        edtCorreo = findViewById(R.id.edtCorreo);
        btnRestablecer = findViewById(R.id.btnRestablecer);
        contrasenaProgressbar = findViewById(R.id.contrasenaProgressbar);
        btnRestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    correo = edtCorreo.getText().toString();

                if(correo.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Ingrese su correo", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    Toast.makeText(getApplicationContext(), "Formato de correo invalido", Toast.LENGTH_SHORT).show();
                }else {
                        resetPassword();
                    }
                }
        });
    }
    private void resetPassword() {
        mAuth.setLanguageCode("es");
        contrasenaProgressbar.setVisibility(View.VISIBLE);
        btnRestablecer.setVisibility(View.INVISIBLE);
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()){
                 Toast.makeText(getApplicationContext(), "Valida tu bandeja de correo o SPAM", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(getApplicationContext(), MainActivity.class));
                 finish();
             }else{
                 Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                 contrasenaProgressbar.setVisibility(View.INVISIBLE);
                 btnRestablecer.setVisibility(View.VISIBLE);
             }
            }
        });
    }
}