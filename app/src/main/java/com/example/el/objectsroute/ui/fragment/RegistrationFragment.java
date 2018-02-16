package com.example.el.objectsroute.ui.fragment.registration;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.registration.RegistrationPresenter;
import com.example.el.objectsroute.presentation.view.registration.RegistrationView;
import com.example.el.objectsroute.ui.fragment.BaseFragment;

/**
 * Created by el on 12.02.2018.
 */

public class RegistrationFragment extends BaseFragment implements RegistrationView {

    @InjectPresenter
    RegistrationPresenter registrationPresenter;


    public static RegistrationFragment getInstance(){
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, null);
    }
}
