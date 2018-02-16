package com.example.el.objectsroute.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.objects.ObjectListPresenter;
import com.example.el.objectsroute.presentation.view.objects.ObjectListView;

/**
 * Created by el on 12.02.2018.
 */

public class ObjectListFragment extends BaseFragment implements ObjectListView {

    @InjectPresenter
    ObjectListPresenter presenter;

    public static ObjectListFragment getInstance() {
        return new ObjectListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objectlist, null);
    }

}

