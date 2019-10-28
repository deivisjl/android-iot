package com.umg.iot.panico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umg.iot.R;
import com.umg.iot.models.Panico;
import com.umg.iot.panico.adapter.OnItemPanicoClickListener;
import com.umg.iot.panico.adapter.PanicoListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanicoFragment extends Fragment implements PanicoView, OnItemPanicoClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    RelativeLayout container;

    private PanicoListAdapter adapter;
    private PanicoPresenter presenter;

    public PanicoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this,view);
        setupAdapter();
        setupRecyclerView();

        this.presenter = new PanicoPresenterImpl(this);
        this.presenter.onCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.subScribe();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        this.adapter = new PanicoListAdapter(new ArrayList<Panico>(), this);
    }

    @Override
    public void onPanicoAdded(Panico panico) {
        adapter.add(panico);
    }

    @Override
    public void onPanicoUpdated(Panico panico) {
        adapter.update(panico);
    }

    @Override
    public void onItemLongClick(Panico panico) {
        if(panico.getEstado() == 0){
            Toast.makeText(getActivity(), "OnLongClick", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        this.adapter.clear();
        super.onPause();
    }
}
