package com.example.reservify;
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
import com.example.reservify.models.UsuarioResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;


public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param3";
    private static final String ARG_PARAM2 = "param3";


    Button  btnActualizar, btnCerrar;
    CircleImageView fotoPerfil;
    TextView txtNombre, txtApellido, txtCorreo, txtrespuesta;
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
        fotoPerfil = view.findViewById(R.id.fotoCirculoPerfil);
        btnCerrar = view.findViewById(R.id.btnCerrar);

        String Nombre = txtNombre.getText().toString();
        String Apellido = txtApellido.getText().toString();
        String Correo = txtCorreo.getText().toString();

        String[] opciones = {"¿Qué es Reservify?", "¿Cuál es el objetivo de  Reservify?", "¿Para quién esta creado Reservify?"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opciones);

        spinnerFQA.setAdapter(adapter);

       String select = spinnerFQA.getSelectedItem().toString();

       if (select.equals("¿Qué es Reservify?"))
       {
            txtrespuesta.setText("No sé");
       }else
           if (select.equals(""))
           {

           }
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

        return view;
    }
}