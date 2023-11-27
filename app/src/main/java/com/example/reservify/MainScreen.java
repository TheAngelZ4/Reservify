package com.example.reservify;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.databinding.ActivityMainScreenBinding;

public class MainScreen extends AppCompatActivity {

    ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        remplazarFragment(new InicioFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item->{
            if (item.getItemId() == R.id.agenda) {
                remplazarFragment(new CitasFragment());
            } else if (item.getItemId() == R.id.inicio) {
                remplazarFragment(new InicioFragment());
            } else if (item.getItemId() == R.id.perfil){
                remplazarFragment(new PerfilFragment());
            }
            return true;
        });
    }

    private void remplazarFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void salir(){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setMessage("¿Ya te vas? Te vamos a extrañar:(")
                .setPositiveButton("Volvere Pronto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finish();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
}
}