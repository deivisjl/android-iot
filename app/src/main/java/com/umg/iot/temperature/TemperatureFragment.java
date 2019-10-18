package com.umg.iot.temperature;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.umg.iot.R;
import com.umg.iot.models.Temperature;
import com.umg.iot.temperature.adapter.OnItemTemperatureClickListener;
import com.umg.iot.temperature.adapter.TemperatureListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemperatureFragment extends Fragment implements TemperatureView, OnItemTemperatureClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    private TemperatureListAdapter adapter;
    private TemperaturePresenter presenter;

    public TemperatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        setupRecyclerView();

        this.presenter = new TemperaturePresenterImpl(this);
        presenter.onCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subScribe();
    }

    private void setupAdapter() {
        this.adapter = new TemperatureListAdapter(new ArrayList<Temperature>(),this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onTemperatureAdd(Temperature temperature) {
        adapter.add(temperature);
    }

    @Override
    public void onTemperatureChanged(Temperature temperature) {
        adapter.update(temperature);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        adapter.clear();
        super.onPause();
    }

    @Override
    public void onItemLongClick(Temperature temperature) {
        if(temperature.getEstado() == 0)
        {
            Toast.makeText(getContext(), "ONLONGCLICK", Toast.LENGTH_SHORT).show();
        }
    }
}
