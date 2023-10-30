package com.example.reservify;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import de.hdodenhof.circleimageview.CircleImageView;


public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param3";
    private static final String ARG_PARAM2 = "param3";

    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference Base_De_Datos;
    Button  btnActualizar, btnCerrar;
    CircleImageView fotoPerfil;
    TextView txtNombre, txtApellido, txtCorreo;

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
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        Base_De_Datos = FirebaseDatabase.getInstance().getReference("Usuarios");
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        txtNombre = view.findViewById(R.id.txtNombre);
        txtApellido = view.findViewById(R.id.txtApellido);
        txtCorreo = view.findViewById(R.id.txtCorreo);
        fotoPerfil = view.findViewById(R.id.fotoCirculoPerfil);
        btnActualizar = view.findViewById(R.id.btnActualizar);
        btnCerrar = view.findViewById(R.id.btnCerrar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });

        //Obtenemos los datos del usuario
        Base_De_Datos.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Validando la exsitencia del usuario
                if (snapshot.exists()){
                    //Se extraen los datos
                    String nombre = ""+snapshot.child("nombre").getValue();
                    String apellido = ""+snapshot.child("apellido").getValue();
                    String correo = ""+snapshot.child("correo").getValue();

                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                    txtCorreo.setText(correo);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData()!=null){
            Uri perfilUri = data.getData();
            fotoPerfil.setImageURI(perfilUri);

            final StorageReference reference = storage.getReference().child("imagenPerfil")
                    .child(FirebaseAuth.getInstance().getUid());

            //reference.putFile(perfilUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
              //  @Override
                //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  //  Uri uri = firebaseAuth.getCurrentUser().getPhotoUrl();
                    //Picasso.get().load(uri).into(fotoPerfil);
                    //Toast.makeText(getContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                //}
            //});
        }else{
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}

