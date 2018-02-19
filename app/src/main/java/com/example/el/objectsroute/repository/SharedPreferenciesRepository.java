package com.example.el.objectsroute.repository;

/**
 * Created by el on 19.02.2018.
 */

public class SharedPreferenciesRepository implements ISettingsRepository {

    private static SharedPreferenciesRepository instance;

    private SharedPreferenciesRepository() {
    }

    public static SharedPreferenciesRepository getInstance() {
        if (instance == null) instance = new SharedPreferenciesRepository();
        return instance;
    }


    @Override
    public void saveAccessToken(String accessToken) {
    }

    @Override
    public String getAccessToken() {
        return null;
    }
}
