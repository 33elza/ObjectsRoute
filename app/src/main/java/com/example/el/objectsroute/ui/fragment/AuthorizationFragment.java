package com.example.el.objectsroute.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.authorization.AuthorizationPresenter;
import com.example.el.objectsroute.presentation.view.authorization.AuthorizationView;

/**
 * Created by el on 12.02.2018.
 */

public class AuthorizationFragment extends BaseFragment implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenter presenter;

    public static AuthorizationFragment getInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_authorization, null);

        presenter.onCreateView(getArguments());

        return rootView;
    }

}
