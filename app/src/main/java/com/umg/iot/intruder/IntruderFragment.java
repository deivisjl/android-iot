package com.umg.iot.intruder;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class IntruderFragment extends Fragment implements OnItemTemperatureClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    private TemperatureListAdapter adapter;

    public IntruderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this,view);
        setupAdapter();
        setupRecyclerView();
        insertDemo();
        return view;
    }

    private void insertDemo() {

    }

    private void setupAdapter() {
        this.adapter = new TemperatureListAdapter(new ArrayList<Temperature>(),this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onItemLongClick(Temperature temperature) {

    }
}
