package com.example.el.objectsroute.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.AuthorizationPresenter;
import com.example.el.objectsroute.presentation.view.AuthorizationView;

/**
 * Created by el on 12.02.2018.
 */

public class AuthorizationFragment extends BaseFragment implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenter presenter;

    private Button loginButton;
    private EditText emailEditText;

    public static AuthorizationFragment getInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_authorization, null);

        loginButton = rootView.findViewById(R.id.loginButton);
        emailEditText = rootView.findViewById(R.id.emailEditText);

        loginButton.setOnClickListener(presenter.getLoginButtonClickListener());

        emailEditText.addTextChangedListener(presenter.getEmailTextWatcher());

        presenter.onCreateView();

        return rootView;
    }
}
