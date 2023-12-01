package com.example.reservify;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param3";
    private static final String ARG_PARAM2 = "param3";

    private SessionManager sessionManager;

    Button  btnActualizar, btnCerrar;
    CircleImageView fotoPerfil;
    TextView txtNombre,txtApellido,txtCorreo, txtrespuesta, txtTelefono;

    Spinner spinnerFQA;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Context context = requireContext();
        sessionManager = new SessionManager(context);
        Integer id_user = sessionManager.getUserId();

        /* Consumo de Api para recuperar Id del Usuario */
        Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
        Call<UsuarioResponse> call = apiInterface.getUsuario(id_user);

        /* TRAE LOS DATOS DE LA API  */
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                if (response.isSuccessful()) {

                    UsuarioResponse usuario_response = response.body();
                    Usuario usuario = usuario_response.getUsuario();

                    Integer id_usuario = usuario.getIdUsuario();
                    txtNombre.setText("Nombre: "+ usuario.getNombre());
                    txtApellido.setText("Apellidos: "+ usuario.getApellidos());
                    txtCorreo.setText("Correo: "+ usuario.getCorreo());
                    txtTelefono.setText("Telefono: "+ usuario.getTelefono());
                    }else{
                        Toast.makeText(context, "No se ha encontrado informacion del usuario",
                                Toast.LENGTH_SHORT).show();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtrespuesta = view.findViewById(R.id.txtrespuesta);
        spinnerFQA = view.findViewById(R.id.spinnerFQA);

        txtNombre = view.findViewById(R.id.txtNombre);
        txtApellido = view.findViewById(R.id.txtApellido);
        txtCorreo = view.findViewById(R.id.txtCorreo);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        fotoPerfil = view.findViewById(R.id.fotoCirculoPerfil);
        btnCerrar = view.findViewById(R.id.btnCerrar);

        String Nombre = txtNombre.getText().toString();
        String Apellido = txtApellido.getText().toString();
        String Correo = txtCorreo.getText().toString();


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(v.getContext());
                sessionManager.logout();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_items, R.layout.my_selected_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerFQA.setAdapter(adapter);

        spinnerFQA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                showResponse(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
        private void showResponse(String selectedItem){
            // Mostrar respuesta dependiendo del elemento seleccionado
            String response;

            switch (selectedItem) {
                case "Selecciona una pregunta:":
                    response = " ";
                    break;
                case "¿Qué es Reservify?":
                    response = "Plataforma que busca centralizar negocios locales, proporcionando a las personas la facilidad de encontrar servicios y a los propietarios de negocios una mayor visibilidad y un sistema de gestión de citas para mejorar la organización.";
                    break;
                case "¿Para quién esta creado Reservify?":
                    response = "Reservify está creado tanto para personas que buscan servicios de cualquier tiopo, como para propietarios de negocios. ";
                    break;
                case "¿Quién creo Reservify?":
                    response = "Un pequeño equipo de estudiantes.";
                    break;
                case "¿Puedo cancelar un cita sin penalización?":
                    response = "Sí. Puedes cancelar un cita en cualquier momento.";
                    break;
                default:
                    response = " ";
                    break;
            }

            txtrespuesta.setText(response);
        }

}



