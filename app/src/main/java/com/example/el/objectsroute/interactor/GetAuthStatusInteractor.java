package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.ISettingsRepository;
import com.example.el.objectsroute.repository.SharedPreferencesRepository;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by el on 20.03.2018.
 */

public class GetAuthStatusInteractor {

    private ISettingsRepository settingsRepository = SharedPreferencesRepository.getInstance();

    public void getAuthStatus() {
        EventBus.getDefault().post(new Response.AuthStatusResponse(settingsRepository.hasAccessToken()));
    }
}
