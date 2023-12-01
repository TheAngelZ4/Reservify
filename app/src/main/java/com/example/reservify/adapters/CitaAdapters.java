package com.example.reservify.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reservify.Citas;
import com.example.reservify.models.Cita;
import com.example.reservify.R;
import com.example.reservify.models.PopularModel;

import java.util.List;
public class CitaAdapters extends RecyclerView.Adapter<CitaAdapters.ViewHolder>{
    ProgressBar progressBar;
    private Context context;
    private List <Cita> citaList;

    public CitaAdapters(Context context, List<Cita> citaList) {
        this.context = context;
        this.citaList = citaList;
    }

    public void addBussines(Cita cita){
        citaList.add(cita);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CitaAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cita_items, parent, false);
        return new CitaAdapters.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cita cita = citaList.get(position);
        holder.nombreNegocio.setText(cita.getNombreNegocio());
        holder.hora.setText(cita.getHora());
        holder.direccion.setText(cita.getDireccionNegocio());
        holder.categoria.setText(cita.getCategoriaNegocio());
        Glide.with(context).load(cita.getFoto()).into(holder.popImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return citaList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView hora, nombreNegocio, direccion, categoria;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            nombreNegocio = itemView.findViewById(R.id.pop_name);
            hora = itemView.findViewById(R.id.info_cita);
            direccion = itemView.findViewById(R.id.info_cita2);
            categoria = itemView.findViewById(R.id.info_cita3);
        }

        public void onDataChanged(){
            if(progressBar!=null){
                progressBar.setVisibility(View.GONE);
            }
        }


    }
}
