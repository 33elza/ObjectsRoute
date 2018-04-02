package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.settings.ISettingsRepository;
import com.example.el.objectsroute.repository.settings.SharedPreferencesRepository;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by el on 19.02.2018.
 */

public class LoginInteractor {

    private ISettingsRepository settingsRepository = SharedPreferencesRepository.getInstance();

    public void login(String login, String password) {

        settingsRepository.saveAccessToken("1111");
        EventBus.getDefault().post(new Response.LoginResponse());
    }
}
