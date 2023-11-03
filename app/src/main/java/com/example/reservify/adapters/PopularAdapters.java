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
import com.example.reservify.PopularModel;
import com.example.reservify.R;


import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder>{

    private Context context;
    private List <PopularModel> popularModelList;
    ProgressBar progressBar;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    public void addBussines(PopularModel popularModel){
        popularModelList.add(popularModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularModel popularModel = popularModelList.get(position);
        holder.nombre.setText(popularModel.getNombre());
        holder.valor.setText(popularModel.getValoracion());
        holder.descripcion.setText(popularModel.getDescripcion());
        Glide.with(context).load(popularModel.getImagen()).into(holder.popImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Citas.class);
                intent.putExtra("model", popularModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView nombre, descripcion, valor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            nombre = itemView.findViewById(R.id.pop_name);
            descripcion = itemView.findViewById(R.id.pop_des);
            valor = itemView.findViewById(R.id.valor);
        }

        public void onDataChanged(){
            if(progressBar!=null){
                progressBar.setVisibility(View.GONE);
            }
        }

    }
}
