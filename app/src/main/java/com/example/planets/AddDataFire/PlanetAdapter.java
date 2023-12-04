package com.example.planets.AddDataFire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planets.R;

import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder>
{
    private List<Planet> planetList;
    private Context context;
    private FirebaseServices fbs;

    public PlanetAdapter(Context context, List<Planet> productList) {
        this.planetList = productList;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public PlanetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planet_item,parent,false);
        return new PlanetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planet planet = planetList.get(position);
        holder.tvDistance.setText(planet.getDistanceFromSun());
        holder.tvSize.setText(planet.getSize());
        holder.tvName.setText(planet.getName());
        holder.tvOrbit.setText(planet.getOrbitPeriod());
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvSize, tvDistance, tvOrbit;
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNamePlanetItem);
            tvSize = itemView.findViewById(R.id.tvSizePlanetItem);
            tvDistance = itemView.findViewById(R.id.tvDistancePlanetItem);
            tvOrbit = itemView.findViewById(R.id.tvOrbitPlanetItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //((MainActivity)context).get().showMessageDialog(context, productList.get(position).get());

        }
    }
}