package com.example.el.objectsroute.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.map.MapPresenter;
import com.example.el.objectsroute.presentation.view.map.MapView;

/**
 * Created by el on 12.02.2018.
 */

public class MapFragment extends BaseFragment implements MapView {

    @InjectPresenter
    MapPresenter presenter;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, null);
    }
}
