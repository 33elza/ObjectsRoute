package com.example.el.objectsroute.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.presentation.presenter.ObjectListPresenter;
import com.example.el.objectsroute.presentation.view.ObjectListView;
import com.example.el.objectsroute.ui.adapter.ObjectListAdapter;

import java.util.List;

/**
 * Created by el on 12.02.2018.
 */

public class ObjectListFragment extends BaseFragment implements ObjectListView {

    @InjectPresenter
    ObjectListPresenter presenter;

    private ObjectListAdapter adapter;

    private List<ObjectVisitation> objects;

    private Button makeRouteButton;

    public static ObjectListFragment getInstance() {
        return new ObjectListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ObjectListAdapter();
        presenter.onCreate(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_object_list, null);

        final RecyclerView recyclerView = rootView.findViewById(R.id.objectListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        makeRouteButton = rootView.findViewById(R.id.makeRouteButton);
        makeRouteButton.setOnClickListener(presenter.getMakeRouteButtonClickListener());

        return rootView;
    }

    @Override
    public void setObjects(List<ObjectVisitation> data) {
        adapter.setObjects(data);
        adapter.notifyDataSetChanged();
    }

}

