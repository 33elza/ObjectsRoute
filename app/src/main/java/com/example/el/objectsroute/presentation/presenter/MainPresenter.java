package com.example.el.objectsroute.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.presentation.view.MainView;
import com.example.el.objectsroute.repository.ISettingsRepository;
import com.example.el.objectsroute.repository.SharedPreferencesRepository;
import com.example.el.objectsroute.router.Router;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements Router {

    private ISettingsRepository settingsRepository = SharedPreferencesRepository.getInstance();

    public void onCreate() {
        App.setRouter(this);

        if (settingsRepository.hasAccessToken()) {
            App.getRouter().goToObjectList();
        } else {
            App.getRouter().goToAuthorization();
        }
    }

    @Override
    public void goToMap() {
        getViewState().goToMap();
    }

    @Override
    public void goBack() {
        getViewState().goBack();
    }

    @Override
    public void goToAuthorization() {
        getViewState().goToAuthorization();
    }

    @Override
    public void goToObjectList() {
        getViewState().goToObjectList();
    }
}
