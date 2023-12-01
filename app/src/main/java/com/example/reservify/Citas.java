package com.example.reservify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.adapters.PopularAdapters;
import com.example.reservify.databinding.ActivityCitasBinding;
import com.example.reservify.models.AgendarCitas;
import com.example.reservify.models.PopularModel;
import com.example.reservify.models.PopularModelResponse;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Citas extends AppCompatActivity implements View.OnClickListener {
    ActivityCitasBinding binding;
    Toolbar toolbar;
    TextView tvIdNegocio;
    Button btnCalendario, btnHora;
    EditText edtCalendario, edtHora;
    int day, month, year, hour, minutes;

    private SessionManager sessionManager;

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
        tvIdNegocio = findViewById(R.id.tv_id_negocio);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        PopularModel popularModel =(PopularModel) intent.getSerializableExtra("model");

        final Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        binding.edtCalendario.setText(day + "/" + (month + 1) + "/" + year);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);

        binding.edtHora.setText(hour + ":" + minutes);
        binding.tvIdNegocio.setText(popularModel.getId());
        binding.Nombre.setText(popularModel.getNombre());
        binding.descripcion.setText(popularModel.getDescripcion());
        binding.detalleRanking.setText(popularModel.getValoracion());
        Glide.with(binding.getRoot()).load(popularModel.getImagen())
                .into(binding.detalleImagen);

    }

    public void onClick(View v) {
        if (v == btnCalendario) {
            final Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                    edtCalendario.setText(dia + "/" + (mes + 1) + "/" + ano);
                }
            }, year, month, day);  // Establecer la fecha predeterminada aquí
            datePickerDialog.show();
        } else if (v == btnHora) {
            final Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minutes = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hora, int minuto) {
                    edtHora.setText(hora + ":" + minuto);
                }
            }, hour, minutes, false);
            timePickerDialog.show();
        }
    }



    public void AgendarCita(View view){

        sessionManager = new SessionManager(this);

        /* Obteniendo datos pa */
        String fecha = edtCalendario.getText().toString();
        String hora = edtHora.getText().toString();
        Integer id_negocio = Integer.valueOf(tvIdNegocio.getText().toString());
        Integer id_usuario = sessionManager.getUserId();

        AgendarCitas cita = new AgendarCitas(null, fecha,hora, id_negocio,id_usuario);

        Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
        Call<AgendarCitas> call = apiInterface.agendar_cita(cita);


        /* TRAE LOS DATOS DE LA API  */
        call.enqueue(new Callback<AgendarCitas>() {
            @Override
            public void onResponse(Call<AgendarCitas> call, Response<AgendarCitas> response) {

                if (response.isSuccessful()) {
                    AgendarCitas cita = response.body();

                    if (cita.id != null || cita.id != ""){
                        Intent intent = new Intent(view.getContext(), MainScreen.class);
                        startActivity(intent);
                        Toast.makeText(view.getContext(),"Cita Agendada Correctamente :D",Toast.LENGTH_SHORT).show();
                        finish();                    }

                    System.out.println("Todo okay");

                }else{
                    System.out.println("No jalo agenadar citas");
                }
            }

            @Override
            public void onFailure(Call<AgendarCitas> call, Throwable t) {
                System.out.println("fallo el agendar citas ");
            }
        });

    }

}

