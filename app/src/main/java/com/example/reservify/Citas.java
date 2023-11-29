package com.example.reservify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.example.reservify.databinding.ActivityCitasBinding;
import com.example.reservify.models.PopularModel;

import java.util.Calendar;


public class Citas extends AppCompatActivity implements View.OnClickListener {
    ActivityCitasBinding binding;
    Toolbar toolbar;
    Button btnCalendario, btnHora;
    EditText edtCalendario, edtHora;
    int day, month, year, hour, minutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnCalendario = findViewById(R.id.btnCalendario);
        btnHora = findViewById(R.id.btnHora);
        edtCalendario = findViewById(R.id.edtCalendario);
        edtHora = findViewById(R.id.edtHora);
        btnCalendario.setOnClickListener(this);
        btnHora.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        PopularModel popularModel =(PopularModel) intent.getSerializableExtra("model");

        binding.Nombre.setText(popularModel.getNombre());
        binding.descripcion.setText(popularModel.getDescripcion());
        binding.detalleRanking.setText(popularModel.getValoracion());
        Glide.with(binding.getRoot()).load(popularModel.getImagen())
                .into(binding.detalleImagen);

    }

        public void onClick(View v){
        if(v == btnCalendario){
            final Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                    edtCalendario.setText(dia+"/"+(mes+1)+"/"+ano);
                }
            }, day, month, year);
            datePickerDialog.show();
        }
        if(v == btnHora){
            final Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minutes = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hora, int minuto) {
                    edtHora.setText(hora+":"+minuto);
                }
            }, hour, minutes, false);
            timePickerDialog.show();
        }

        }
    }

