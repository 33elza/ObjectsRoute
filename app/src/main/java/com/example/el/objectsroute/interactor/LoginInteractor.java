package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.ISettingsRepository;
import com.example.el.objectsroute.repository.SharedPreferencesRepository;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 19.02.2018.
 */

public class LoginInteractor {

    private ISettingsRepository settingsRepository = SharedPreferencesRepository.getInstance();

    public Single<Response<String>> login(String login, String password) {

        return Single.just(new Response<String>()).doOnSuccess(new Consumer<Response<String>>() {
            @Override
            public void accept(Response<String> response) throws Exception {
                settingsRepository.saveAccessToken(response.getData());
            }
        });
    }
}
