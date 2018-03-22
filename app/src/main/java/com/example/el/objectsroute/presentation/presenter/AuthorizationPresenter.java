package com.example.el.objectsroute.presentation.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.LoginInteractor;
import com.example.el.objectsroute.presentation.view.AuthorizationView;
import com.example.el.objectsroute.router.AuthorizationRouter;
import com.example.el.objectsroute.utils.handler.HttpErrorHandler;
import com.example.el.objectsroute.utils.validator.BaseValidator;
import com.example.el.objectsroute.utils.validator.EmailValidator;
import com.example.el.objectsroute.utils.validator.PasswordValidator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> implements AuthorizationRouter {

    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;

    private String email;
    private String password;

    public void onCreate() {

        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public View.OnClickListener getLoginButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailValidator.validate(email, new BaseValidator.Callback() {
                    @Override
                    public void onError(int error) {
                        getViewState().showError(error);
                    }
                })) {
                    return;
                }

                if (!passwordValidator.validate(password, new BaseValidator.Callback() {
                    @Override
                    public void onError(int error) {
                        getViewState().showError(error);
                    }
                })) {
                    return;
                }

                login();
            }
        };
    }

    private void login() {
        new LoginInteractor().login(email, password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(Response.LoginResponse response) {
        if (response.hasError()) {
            new HttpErrorHandler(getViewState()).handleError(response.getError());
        } else {
            finishActivity();
        }
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

    @Override
    public void finishActivity() {
        getViewState().finishActivity();
    }
}
