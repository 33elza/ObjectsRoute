package com.example.el.objectsroute.presentation.presenter;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.LoginInteractor;
import com.example.el.objectsroute.presentation.view.AuthorizationView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {

    private Disposable loginDisposable;

    public void onCreate(Bundle arguments) {

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
                login();
            }
        };
    }

    private void login() {
        if (loginDisposable != null && !loginDisposable.isDisposed()) {
            loginDisposable.dispose();
        }
        loginDisposable = new LoginInteractor()
                .login("", "")
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


}
