package com.example.el.objectsroute.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.ObjectTabsPresenter;
import com.example.el.objectsroute.presentation.view.ObjectTabsView;

/**
 * Created by el on 13.02.2018.
 */

public class ObjectTabsFragment extends BaseFragment implements ObjectTabsView {

    @InjectPresenter
    ObjectTabsPresenter presenter;

    private Button button;


    public static ObjectTabsFragment getInstance() {
        ObjectTabsFragment fragment = new ObjectTabsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_objecttabs, null);

        return rootView;
    }
}
