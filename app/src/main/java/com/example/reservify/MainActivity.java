package com.example.reservify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reservify.Sessions.SessionManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SessionManager sessionManager = new SessionManager(this);

        if (sessionManager.getUserId() != -1){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            finish();
        }

    }

    public void IrRegistrar(View view){
        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
        finish();
    }

    public void IrIniciar(View view){
        Intent i = new Intent (this, Login.class);
        startActivity(i);
        finish();
    }


}