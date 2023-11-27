package com.example.reservify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reservify.API.ApiServicesGenerator;
import com.example.reservify.API.Api_Interface;
import com.example.reservify.adapters.PopularAdapters;
import com.example.reservify.models.PopularModel;
import com.example.reservify.models.PopularModelResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<PopularModel> LISTA_NEGOCIOS;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    //TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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
    RecyclerView  popularRec;
    FirebaseFirestore db;
    List <PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);


        //db = FirebaseFirestore.getInstance();

        popularRec = view.findViewById(R.id.negocios_rec);
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        Api_Interface apiInterface = ApiServicesGenerator.createService(Api_Interface.class);
        Call<PopularModelResponse> call = apiInterface.recuperar_negocios();

        /* TRAE LOS DATOS DE LA API  */
        call.enqueue(new Callback<PopularModelResponse>() {
            @Override
            public void onResponse(Call<PopularModelResponse> call, Response<PopularModelResponse> response) {

                if (response.isSuccessful()) {
                    PopularModelResponse negocios_response = response.body();
                    List<PopularModel> negocios = negocios_response.getNegocios();
                    LISTA_NEGOCIOS = negocios;

                    /* ASIGNA LOS DATOS COM PARAMETRO PARA EL ADAPTER */
                    //popularModelList = new ArrayList<>();
                    popularAdapters = new PopularAdapters(getActivity(), LISTA_NEGOCIOS);
                    popularRec.setAdapter(popularAdapters);

                    System.out.println("Si jalo pa");

                }else{
                    System.out.println("No jalo pa");
                }
            }

            @Override
            public void onFailure(Call<PopularModelResponse> call, Throwable t) {
                System.out.println("No jalo el llamado pa ");
            }
        });



/*
        db.collection("PopularNegocios")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

 */
        return view;
    }
}