package com.umg.iot.temperature.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.models.Temperature;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperatureListAdapter extends RecyclerView.Adapter<TemperatureListAdapter.ViewHolder> {

    private List<Temperature> temperatureList;
    private OnItemTemperatureClickListener clickListener;

    public TemperatureListAdapter(List<Temperature> temperatureList, OnItemTemperatureClickListener clickListener) {
        this.temperatureList = temperatureList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_temperature, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Temperature temperature = temperatureList.get(position);
        holder.setClickListener(temperature,clickListener);

        View estado = holder.statusView;
        switch (temperature.getEstado())
        {
            case 0:
                estado.setBackgroundResource(R.color.activeStatus);
                holder.status.setText("Estado: Activa");
                break;
            case 1:
                estado.setBackgroundResource(R.color.inactiveStatus);
                holder.status.setText("Estado: Procesada");
                break;
        }
        holder.txtTemperatura.setText("Temperatura: "+temperature.getTemperatura()+"°c");
        holder.txtHumedad.setText("Humedad: "+temperature.getHumedad());
        holder.txtDate.setText(temperature.getFecha());
        holder.hora.setText(temperature.getHora());
    }

    @Override
    public int getItemCount() {
        return temperatureList.size();
    }

    public void clear(){
        if (temperatureList.size() > 0){
            temperatureList.clear();
            notifyDataSetChanged();
        }
    }

    public void add(Temperature temperature){
        temperatureList.add(0,temperature);
        notifyDataSetChanged();
    }

    public void update(Temperature temperature){
        if(temperatureList.contains(temperature)){
            int index = temperatureList.indexOf(temperature);
            temperatureList.set(index, temperature);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.indicator_appointment_status)
        View statusView;
        @BindView(R.id.txtDate)
        TextView txtDate;
        @BindView(R.id.vertical_separator)
        View verticalSeparator;
        @BindView(R.id.txtTemperatura)
        TextView txtTemperatura;
        @BindView(R.id.txtHumedad)
        TextView txtHumedad;
        @BindView(R.id.hora)
        TextView hora;
        @BindView(R.id.status)
        TextView status;

        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.view = itemView;
        }

        private void setClickListener(final Temperature temperature, final OnItemTemperatureClickListener listener){

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(temperature);
                    return false;
                }
            });
        }
    }

}
