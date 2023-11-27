package com.example.reservify;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.reservify.databinding.ActivityCitasBinding;
import com.example.reservify.models.PopularModel;


public class Citas extends AppCompatActivity {
    ActivityCitasBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        PopularModel popularModel =(PopularModel) intent.getSerializableExtra("model");

        binding.Nombre.setText(popularModel.getNombre());
        binding.descripcion.setText(popularModel.getDescripcion());
        binding.detalleRanking.setText(popularModel.getValoracion());
        Glide.with(binding.getRoot()).load(popularModel.getImagen())
                .into(binding.detalleImagen);


        binding.regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(regresar);
                finish();
            }
        });


    }

}
