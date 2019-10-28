package com.umg.iot.panico.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.models.Panico;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanicoListAdapter extends RecyclerView.Adapter<PanicoListAdapter.ViewHolder> {

    private OnItemPanicoClickListener listener;
    private List<Panico> panicoList;

    public PanicoListAdapter(List<Panico> panicoList, OnItemPanicoClickListener listener) {
        this.listener = listener;
        this.panicoList = panicoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_panic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Panico panico = panicoList.get(position);
        holder.setClickListener(panico,listener);

        View estado = holder.indicatorStatus;

        switch (panico.getEstado()){
            case 0:
                estado.setBackgroundResource(R.color.activeStatus);
                holder.status.setText("Estado: Activa");
                break;
            case 1:
                estado.setBackgroundResource(R.color.inactiveStatus);
                holder.status.setText("Estado: Procesada");
                break;
        }

        holder.txtTitle.setText(panico.getTitulo());
        holder.txtDescription.setText(panico.getMensaje());
        holder.txtDate.setText(panico.getFecha());
        holder.hora.setText(panico.getHora());
    }

    public void add(Panico panico){
        if(!panicoList.contains(panico)){
            panicoList.add(0,panico);
            notifyDataSetChanged();
        }
    }

    public void update(Panico panico){
        if(panicoList.contains(panico)){
            int index = panicoList.indexOf(panico);
            panicoList.set(index, panico);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        if(panicoList.size() > 0){
            panicoList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return panicoList.size();
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
        @BindView(R.id.txtDescription)
        TextView txtDescription;
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

        private void setClickListener(Panico panico,OnItemPanicoClickListener listener)
        {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(panico);
                    return false;
                }
            });
        }
    }
}
