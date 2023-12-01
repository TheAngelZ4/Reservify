package com.example.reservify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.Sessions.SessionManager;
import com.example.reservify.adapters.CitaAdapters;
import com.example.reservify.adapters.PopularAdapters;
import com.example.reservify.models.PopularModel;
import com.example.reservify.models.Usuario;
import com.example.reservify.models.UsuarioResponse;
import com.example.reservify.models.CitaResponse;
import com.example.reservify.models.Cita;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Cita> LISTA_CITAS;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SessionManager sessionManager;

    public CitasFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitasFragment newInstance(String param1, String param2) {
        CitasFragment fragment = new CitasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView citasRec;
    CitaAdapters citaAdapters;
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
        View view = inflater.inflate(R.layout.fragment_citas, container, false);
        citasRec = view.findViewById(R.id.rec_citas);
        citasRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        Context contexto = requireContext();
        sessionManager = new SessionManager(contexto);
        Integer id_usuario = sessionManager.getUserId();

        Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
        Call<List<Cita>> call = apiInterface.citasUsuario(id_usuario);
        call.enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {

                if (response.isSuccessful()) {

                    List<Cita> citas = response.body();
                    LISTA_CITAS = citas;
                    citaAdapters = new CitaAdapters(getActivity(), LISTA_CITAS);
                    citasRec.setAdapter(citaAdapters);

                    System.out.println("Si jalo pa");

                }else{
                    System.out.println("No jalo pa");
                }
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {
                String errorMessage = t.getMessage();
                System.out.println("Error: " + errorMessage);
                System.out.println("No jalo el llamado pa ");
            }

        });

        return view;

    }

}