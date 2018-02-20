package com.example.el.objectsroute.repository;

/**
 * Created by el on 19.02.2018.
 */

public interface ISettingsRepository {
    void saveAccessToken(final String accessToken);

    String getAccessToken();

    boolean hasAccessToken();
}
