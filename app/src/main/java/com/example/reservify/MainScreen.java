package com.example.reservify;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import com.example.reservify.databinding.ActivityMainScreenBinding;

public class MainScreen extends AppCompatActivity {

    private

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
}