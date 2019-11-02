package com.umg.iot.gas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.gas.adapter.GasListAdapter;
import com.umg.iot.gas.adapter.OnItemGasClickListener;
import com.umg.iot.models.Gas;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GasFragment extends Fragment implements GasView, OnItemGasClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    CoordinatorLayout container;

    private GasListAdapter adapter;
    private GasPresenter presenter;

    public GasFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this,view);
        setupAdapter();
        setupRecycler();

        this.presenter = new GasPresenterImpl(this);
        this.presenter.onCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subScribe();
    }

    private void setupRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        this.adapter = new GasListAdapter(new ArrayList<Gas>(),this);
    }

    @Override
    public void onGasAdded(Gas gas) {
        this.adapter.add(gas);
    }

    @Override
    public void onGasUpdated(Gas gas) {
        this.adapter.update(gas);
    }

    @Override
    public void onSuccesChanged() {
        Toast.makeText(getActivity(), "Registro notificado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(Gas gas) {
        if(gas.getEstado() == 0){
            this.presenter.updateGas(gas);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onPause() {
        this.adapter.clear();
        super.onPause();
    }
}
