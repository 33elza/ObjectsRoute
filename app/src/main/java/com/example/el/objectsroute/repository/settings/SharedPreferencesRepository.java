package com.example.el.objectsroute.repository.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.el.objectsroute.App;

/**
 * Created by el on 19.02.2018.
 */

public class SharedPreferencesRepository implements ISettingsRepository {

    private static SharedPreferencesRepository instance;

    private static SharedPreferences settings;

    private static final String APP_PREFERENCES = "APP_PREFERENCES";
    private static final String APP_PREFERENCES_TOKEN = "APP_PREFERENCES_TOKEN";

    private SharedPreferencesRepository() {
        settings = App.getAppContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesRepository getInstance() {
        if (instance == null) instance = new SharedPreferencesRepository();
        return instance;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_PREFERENCES_TOKEN, accessToken);
        editor.apply();
    }

    @Override
    public String getAccessToken() {
        return settings.getString(APP_PREFERENCES_TOKEN, "");
    }

    @Override
    public boolean hasAccessToken() {
        return settings.contains(APP_PREFERENCES_TOKEN);
    }
}
