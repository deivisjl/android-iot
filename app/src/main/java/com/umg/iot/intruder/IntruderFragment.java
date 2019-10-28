package com.umg.iot.intruder;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.intruder.adapter.IntruderListAdapter;
import com.umg.iot.intruder.adapter.OnItemIntruderClickListener;
import com.umg.iot.models.Intruder;
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
public class IntruderFragment extends Fragment  implements IntruderView, OnItemIntruderClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    RelativeLayout container;

    private IntruderListAdapter adapter;
    private IntruderPresenter presenter;

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

        this.presenter = new IntruderPresenterImpl(this);
        presenter.onCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subScribe();
    }

    private void setupAdapter() {
        this.adapter = new IntruderListAdapter(new ArrayList<Intruder>(),this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onPause() {
        adapter.clear();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.unSubcribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onIntruderAdd(Intruder intruder) {
        adapter.add(intruder);
    }

    @Override
    public void onIntruderUpdated(Intruder intruder) {
        adapter.update(intruder);
    }

    @Override
    public void onLongClick(Intruder intruder) {
        if(intruder.getEstado() == 0)
        {
            Toast.makeText(getActivity(), "OnLongClick", Toast.LENGTH_SHORT).show();
        }
    }
}
