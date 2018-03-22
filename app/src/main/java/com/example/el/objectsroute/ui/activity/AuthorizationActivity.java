package com.example.el.objectsroute.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.AuthorizationPresenter;
import com.example.el.objectsroute.presentation.view.AuthorizationView;

public class AuthorizationActivity extends BaseActivity implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenter presenter;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(presenter.getLoginButtonClickListener());

        emailEditText.addTextChangedListener(presenter.getEmailTextWatcher());
        passwordEditText.addTextChangedListener(presenter.getPasswordTextWatcher());

        presenter.onCreate();
    }

    @Override
    public void finishActivity() {
        finish();

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
