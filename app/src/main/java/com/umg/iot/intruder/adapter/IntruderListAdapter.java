package com.umg.iot.intruder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.models.Intruder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntruderListAdapter extends RecyclerView.Adapter<IntruderListAdapter.ViewHolder> {

    private List<Intruder> intruderList;
    private OnItemIntruderClickListener listener;

    public IntruderListAdapter(List<Intruder> intruderList, OnItemIntruderClickListener listener) {
        this.intruderList = intruderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_intruder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intruder intruder = intruderList.get(position);
        holder.setClickListener(intruder,listener);

        View estado = holder.statusView;

        switch (intruder.getEstado()){
            case 0:
                estado.setBackgroundResource(R.color.activeStatus);
                holder.status.setText("Estado: Activa");
                break;
            case 1:
                estado.setBackgroundResource(R.color.inactiveStatus);
                holder.status.setText("Estado: Procesada");
                break;
        }

        holder.txtTitle.setText(intruder.getTitulo());
        holder.txtDescription.setText(intruder.getMensaje());
        holder.txtDate.setText(intruder.getFecha());
        holder.hora.setText(intruder.getHora());
    }

    @Override
    public int getItemCount() {
        return intruderList.size();
    }

    public void clear(){
        if (intruderList.size() > 0){
            intruderList.clear();
            notifyDataSetChanged();
        }
    }

    public void add(Intruder intruder){
        if(!intruderList.contains(intruder)){
            intruderList.add(0,intruder);

            notifyItemInserted(0);
            notifyDataSetChanged();
        }
    }

    public void update(Intruder intruder){
        if(intruderList.contains(intruder)){
            int index = intruderList.indexOf(intruder);
            intruderList.set(index, intruder);

            notifyItemChanged(index);
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

        private void setClickListener(Intruder intruder,OnItemIntruderClickListener listener)
        {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(intruder);
                    return false;
                }
            });
        }
    }
}
