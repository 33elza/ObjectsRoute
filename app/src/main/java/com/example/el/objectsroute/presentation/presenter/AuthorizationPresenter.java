package com.example.el.objectsroute.presentation.presenter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.LoginInteractor;
import com.example.el.objectsroute.presentation.view.AuthorizationView;
import com.example.el.objectsroute.utils.validator.BaseValidator;
import com.example.el.objectsroute.utils.validator.EmailValidator;
import com.example.el.objectsroute.utils.validator.PasswordValidator;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {

    private Disposable loginDisposable;
    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;

    private String email;
    private String password;

    public void onCreate(Bundle arguments) {
        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator();
    }

    public void onCreateView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loginDisposable != null && !loginDisposable.isDisposed()) {
            loginDisposable.dispose();
            loginDisposable = null;
        }
    }

    public View.OnClickListener getLoginButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean areAuthFieldsValid = (emailValidator.validate(email, new BaseValidator.Callback() {
                    @Override
                    public void onError(int error) {
                        getViewState().showError(error);
                    }
                })) && (passwordValidator.validate(password, new BaseValidator.Callback() {
                    @Override
                    public void onError(int error) {
                        getViewState().showError(error);
                    }
                }));

                if (areAuthFieldsValid) {
                    login();
                }
            }
        };
    }

    private void login() {
        if (loginDisposable != null && !loginDisposable.isDisposed()) {
            loginDisposable.dispose();
        }
        loginDisposable = new LoginInteractor()
                .login(email, password)
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        if (response.hasError()) {
                            // TODO: 19.02.2018 Обработать ошибку
                        } else {
                            App.getRouter().goToObjectList();
                        }
                    }
                });
    }


    public TextWatcher getEmailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public TextWatcher getPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }
}
