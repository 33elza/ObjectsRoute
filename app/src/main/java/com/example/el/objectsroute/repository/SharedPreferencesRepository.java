package com.example.el.objectsroute.repository;

/**
 * Created by el on 19.02.2018.
 */

public class SharedPreferencesRepository implements ISettingsRepository {

    private static SharedPreferencesRepository instance;

    private SharedPreferencesRepository() {
    }

    public static SharedPreferencesRepository getInstance() {
        if (instance == null) instance = new SharedPreferencesRepository();
        return instance;
    }

    @Override
    public void saveAccessToken(String accessToken) {
    }

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public boolean hasAccessToken() {
        return false;
    }
}
