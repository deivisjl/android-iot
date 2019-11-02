package com.umg.iot.gas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.models.Gas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GasListAdapter extends RecyclerView.Adapter<GasListAdapter.ViewHolder> {

    private List<Gas> gasList;
    private OnItemGasClickListener listener;

    public GasListAdapter(List<Gas> gasList, OnItemGasClickListener listener) {
        this.gasList = gasList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_gas, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gas gas = gasList.get(position);
        holder.setClickListener(gas,listener);

        View estado = holder.indicatorStatus;

        switch (gas.getEstado()){
            case 0:
                estado.setBackgroundResource(R.color.activeStatus);
                holder.status.setText("Estado: Activa");
                break;
            case 1:
                estado.setBackgroundResource(R.color.inactiveStatus);
                holder.status.setText("Estado: Procesada");
                break;
        }

        holder.txtTitle.setText(gas.getMensaje());
        holder.txtDate.setText(gas.getFecha());
        holder.hora.setText(gas.getHora());
    }

    @Override
    public int getItemCount() {
        return gasList.size();
    }

    public void add(Gas gas){
        if(!gasList.contains(gas)){
            gasList.add(0,gas);

            notifyItemInserted(0);
            notifyDataSetChanged();
        }
    }

    public void update(Gas gas){
        if(gasList.contains(gas)){
            int index = gasList.indexOf(gas);
            gasList.set(index, gas);

            notifyItemChanged(index);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        if(gasList.size() > 0){
            gasList.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.indicator_status)
        View indicatorStatus;
        @BindView(R.id.txtDate)
        TextView txtDate;
        @BindView(R.id.vertical_separator)
        View verticalSeparator;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
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

        private void setClickListener(Gas gas, OnItemGasClickListener listener)
        {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(gas);
                    return false;
                }
            });
        }
    }
}
