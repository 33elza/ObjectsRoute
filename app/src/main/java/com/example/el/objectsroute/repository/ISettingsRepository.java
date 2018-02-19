package com.example.el.objectsroute.repository;

import android.widget.Button;

import com.example.el.objectsroute.ui.fragment.BaseFragment;

/**
 * Created by el on 19.02.2018.
 */

public interface ISettingsRepository {
    void saveAccessToken(final String accessToken);

    String getAccessToken();

    boolean hasAccessToken();
}
